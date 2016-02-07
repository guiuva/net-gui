/*
 * @author Ismael Taboada
 * Modified by: David Soler <aensoler@gmail.com>
 */

"use strict";

var ROOT_PATH = location.protocol + "//" + location.host + "/NetGUI-api";
var API_PATH = ROOT_PATH + "/rest";

function User(attributes) {
  this.id = attributes.id;
  this.password = attributes.password;
  this.dni = attributes.dni;
  this.nia = attributes.nia;
  this.firstName = attributes.firstName;
  this.lastName = attributes.lastName;
  this.email = attributes.email;
  this.phone = attributes.phone;
  this.state = attributes.state;

  this.validate();
}

User.prototype = {
  _valid: false,
  _errors: {},
  validators: {
    id: /^[a-z0-9_]{3,20}$/,
//    password: //,
    dni: /^\d{8}([ -][A-Za-z])|[A-Za-z]/,
//    nia: //,
    firstName: /^[A-Za-z0-9áéíóúÁÉÍÓÚüÜñÑ ]{3,20}$/,
    lastName: /^[A-Za-z0-9áéíóúÁÉÍÓÚüÜñÑ ]{3,20}$/,
    email: /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i,
    phone: /^(\d{9}|(\d{3})[ -](\d{6}))/
//    state: //,
//    date: /^(19|20)\d\d-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/
  },
  validate: function () {
    this._errors = {};

    if (!this.validators.id.test(this.id))
      this._errors.id = true;
//    if (!this.validators.password.test(this.password))
//      this._errors.password = true;
    if (!this.validators.dni.test(this.dni))
      this._errors.dni = true;
//    if (!this.validators.nia.test(this.nia))
//      this._errors.nia = true;
    if (!this.validators.firstName.test(this.firstName))
      this._errors.firstName = true;
    if (!this.validators.lastName.test(this.lastName))
      this._errors.lastName = true;
    if (!this.validators.email.test(this.email))
      this._errors.email = true;
    if (!this.validators.phone.test(this.phone))
      this._errors.phone = true;
//    if (!this.validators.state.test(this.state))
//      this._errors.state = true;

    this._valid = (Object.keys(this._errors).length === 0);
  },
  isValid: function () {
    return this._valid;
  },
  getErrors: function () {
    return this._errors;
  }
};

var UserDAO = {
  create: function (user, onSuccess) {
    $.ajax({
      type: "POST",
      url: API_PATH + "/users",
      contentType: 'application/json; charset=utf-8',
      dataType: 'json',
      data: JSON.stringify(user),
      success: onSuccess
    });
  }
};
