#!/bin/bash

# @author David Soler <aensoler@gmail.com>
# @version 0.1

SERVER_PATH=shadowServer

GLASSFISH_URL=http://download.oracle.com/glassfish/4.1/release/glassfish-4.1.zip
GLASSFISH_SHA1SUM=704a90899ec5e3b5007d310b13a6001575827293

MYSQL_URL=https://dev.mysql.com/get/Downloads/Connector-J/mysql-connector-java-5.1.38.zip
MYSQL_MD5SUM=1e9cf9455686bcbf767c763e15218955

ASADMIN_BIN=$SERVER_PATH/bin/asadmin

ORIGINAL_PWD=`pwd`


DEST=$1
if [ -z "$DEST" ]; then
	read -p "Install directory [./]: " DEST
	if [ -z "$DEST" ]; then
		DEST=./
	fi
fi

DEPLOY_MODE=$2
if [ -z "$DEPLOY_MODE" ]; then
	read -p "Deploy mode [pro/dev]: " DEPLOY_MODE
	if [ -z "$DEPLOY_MODE" ]; then
		echo "Error! You have to provide a valid deploy mode."
		exit 1
	fi
fi

DATASOURCE_PASS=$3
if [ -z "$DATASOURCE_PASS" ]; then
	read -s -p "Datasource password: " DATASOURCE_PASS
	if [ -z "$DATASOURCE_PASS" ]; then
		echo "Error! You have to provide the datasource password."
		exit 2
	fi
fi

mkdir -p $DEST
cd $DEST

echo "Installing Glassfish Server v4.1 ..."
wget $GLASSFISH_URL -O glassfish.zip
if [ "`sha1sum glassfish.zip | cut -d' ' -f1`" != "$GLASSFISH_SHA1SUM" ]; then
	echo "Error! Invalid sha1sum of downloaded Glassfish file."
	exit 3
fi
unzip -q glassfish.zip
mv glassfish4 $SERVER_PATH
rm glassfish.zip

echo "Installing MySQL Connector ..."
wget $MYSQL_URL -O mysql.zip
if [ "`md5sum mysql.zip | cut -d' ' -f1`" != "$MYSQL_MD5SUM" ]; then
	echo "Error! Invalid md5sum of downloaded MySQL Connector file."
	exit 4
fi
unzip -q mysql.zip
cp mysql-connector-java-5.1.38/mysql-connector-java-5.1.38-bin.jar $SERVER_PATH/glassfish/lib
rm -fR mysql-connector*
rm mysql.zip

echo "Setup datasources ..."
echo "  Starting Glassfish ..."
$ASADMIN_BIN start-domain domain1

if [ "$DEPLOY_MODE" == "pro" ]; then
	$ASADMIN_BIN create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property "user=shadow-production:password=$DATASOURCE_PASS:url=jdbc\\:mysql\\://localhost\\:33061/shadow_production?zeroDateTimeBehavior\\=convertToNull" ShadowProductionPool
	$ASADMIN_BIN ping-connection-pool ShadowProductionPool
	$ASADMIN_BIN create-jdbc-resource --connectionpoolid ShadowProductionPool jdbc/shadowProduction
elif [ "$DEPLOY_MODE" == "dev" ]; then
	$ASADMIN_BIN create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property "user=shadow-dev:password=$DATASOURCE_PASS:url=jdbc\\:mysql\\://localhost\\:33061/shadow_dev?zeroDateTimeBehavior\\=convertToNull" ShadowDevPool
	$ASADMIN_BIN ping-connection-pool ShadowDevPool
	$ASADMIN_BIN create-jdbc-resource --connectionpoolid ShadowDevPool jdbc/shadowDev

	$ASADMIN_BIN create-jdbc-connection-pool --restype javax.sql.DataSource --datasourceclassname com.mysql.jdbc.jdbc2.optional.MysqlDataSource --property "user=shadow-dev:password=$DATASOURCE_PASS:url=jdbc\\:mysql\\://localhost\\:33061/shadow_test?zeroDateTimeBehavior\\=convertToNull" ShadowTestPool
	$ASADMIN_BIN ping-connection-pool ShadowTestPool
	$ASADMIN_BIN create-jdbc-resource --connectionpoolid ShadowTestPool jdbc/shadowTest
else
	exit 5
fi

echo "  Stopping Glassfish ..."
$ASADMIN_BIN stop-domain domain1


cd $ORIGINAL_PWD
echo "Everything OK"
