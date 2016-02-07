/*
 * @author Ismael Taboada
 * Modified by: David Soler <aensoler@gmail.com>
 */

/* global UserDAO */

"use strict";

var ErrorHandler = {
  Messages: {
    id: 'Letras(máyus,minus), números, guión bajo. Mín. 3, máx. 20 Ej: pepe1_2',
    password: 'Contraseña inválida',
    dni: '8 dígitos, espacio ó guión, letra(máyus,minus) Ej: 12345678 A',
    nia: 'Invalid',
    firstName: 'Letras(máyus,minus), espacios. Mín. 3, máx. 20 Ej: José Manuel',
    lastName: 'Letras(máyus,minus), espacios. Mín. 3, máx. 20 Ej: Fernandez Sanchez',
    email: 'email@dominio.extensión Ej: pepe@gmail.com',
    phone: 'Ej: 612 123456 ó 612-123456',
    state: 'Invalid',
    idate: 'yyyy-mm-dd Ej: 1986-04-14',
    password2: 'Las contraseñas no coinciden'
  },
  showError: function (formElement) {
    var banner = notEmpty(formElement.value) ? 'Formato inválido:<br>' + this.Messages[formElement.name] : 'Campo vacío';
    banner = '<p class=\'bannerError\'>' + banner + '</p>';
    formElement.style.borderColor = 'red';
    formElement.insertAdjacentHTML('afterEnd', banner);
  },
  clearErrors: function (form) {
    for (var i = 0; i < form.length; i++)
      if (form.elements[i].style.borderColor === 'red')
        form.elements[i].style.borderColor = '#CED5D7';
    $("p.bannerError").each(function () {
      $(this).remove();
    });
  }
};


$(document).ready(function () {

  //Asignamos manejadores de eventos
  document.formulario.submit.addEventListener('click', takeData, false);

  //Asignamos fecha al registro
  setDate();

  /*if ($.browser.mozilla || $.browser.ie) {
   document.formulario.idate.innerHTML = "yyyy-mm-dd";
   }
   if ($.browser.opera) {
   document.formulario.idate.style.textAlign = "left";
   document.formulario.ndate.style.textAlign = "left";
   }*/
});

function refreshIntOther() {
  var enother = document.getElementById('interesen').checked;
  console.log(document.getElementById('interesen').checked);
  document.formulario.otherinteres.disabled = !enother;
  document.formulario.otherinteres.required = enother;
}

function setDate() {
  var today = new Date();
  var dd = today.getDate();
  var mm = today.getMonth() + 1; //January is 0!
  var yyyy = today.getFullYear();
  if (dd < 10) {
    dd = '0' + dd;
  }
  if (mm < 10) {
    mm = '0' + mm;
  }
  today = yyyy + '-' + mm + '-' + dd;
  document.formulario.idate.setAttribute('value', today);
}

function notEmpty(string) {
  return string || false;
}

function takeData(event) {
  var form = document.formulario;
  var send = false;
  var user;

  event.preventDefault();
  ErrorHandler.clearErrors(form);

  user = new User({
    id: form.id.value,
    password: form.password.value,
    dni: form.dni.value,
    nia: form.nia.value,
    firstName: form.firstName.value,
    lastName: form.lastName.value,
    email: form.email.value,
    phone: form.phone.value,
    state: form.state.value
  });

  send = user.isValid();
  console.log("Errors: ", user.getErrors());

  var password2 = form.password2.value;
  if (!notEmpty(password2) || user.password !== password2) {
    ErrorHandler.showError(form.password2);
    send = false;
  }

  for (var error in user.getErrors())
    if (user.getErrors().hasOwnProperty(error))
      ErrorHandler.showError(form[error]);

//  var idate = document.formulario.idate.value;

//  var grado = document.formulario.grado.value;

  if (send) {
    UserDAO.create(user);
    if (window.confirm('Formulario enviado satisfactoriamente.\nPulse aquí para volver al registro.') === true) {
      window.location.href = "#";
    } else {
      disableForm(document.formulario);
    }
  } else {
    window.alert('Asegurese de rellenar los campos obligatorios.');
  }
}

function disableForm(theform) {
  if (document.all || document.getElementById) {
    for (i = 0; i < theform.length; i++) {
      var formElement = theform.elements[i];
      if (true) {
        formElement.disabled = true;
      }
    }
  }
}
