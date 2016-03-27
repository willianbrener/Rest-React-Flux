
var AppDispatcher = require('../dispatcher/AppDispatcher');
var CMConstants = require('../constants/CMConstants');

var CMActions = {


   _listAll: function() {
    $.ajax({
      url: 'http://localhost:8080/JavaServlet/list',
      dataType: 'json',
      type: 'GET',
      success: function(data) {

        data.forEach(function(obj) {

          var avatar = 'img/faces/love_java.jpg';
            obj.avatar = avatar;
             AppDispatcher.dispatch({
                   actionType: CMConstants.CM_CREATE,
                   id: obj.id,
                   name: obj.name,
                   phone: obj.phone,
                   email: obj.email,
                   avatar: obj.avatar
                 });
        });
      }.bind(this),
      error: function(xhr, status, err) {
        console.error('http://localhost:8080/JavaServlet/list', status, err.toString());
      }.bind(this)
    });

   },

  create: function(newContact) {

    console.log('CREATE');
    console.log(newContact);
  	var avatar = 'img/faces/love_java.jpg';
  	newContact.avatar = avatar;
      $.ajax({
        url: 'http://localhost:8080/JavaServlet/insert',
        dataType: 'text',
        type: 'POST',
        data: newContact,
        success: function(data) {
          console.log(data);
          AppDispatcher.dispatch({
                actionType: CMConstants.CM_CREATE,
                id : data,
                name: newContact.name,
                phone: newContact.phone,
                email: newContact.email,
                avatar: newContact.avatar
              });
        }.bind(this),
        error: function(xhr, status, err) {
          console.error('http://localhost:8080/JavaServlet/insert', status, err.toString());
        }.bind(this)
      });
  },


  edit: function(contact) {
    AppDispatcher.dispatch({
      actionType: CMConstants.CM_EDIT,
      id: contact.id,
      name: contact.name,
      phone: contact.phone,
      email: contact.email,
      avatar: contact.avatar
    });
  },


  save: function(contact) {

    var avatar = 'img/faces/love_java.jpg';
    contact.avatar = avatar;
      $.ajax({
        url: 'http://localhost:8080/JavaServlet/update',
        dataType: 'text',
        type: 'POST',
        data: contact,
        success: function(data) {
          console.log(contact);
          AppDispatcher.dispatch({
              actionType: CMConstants.CM_SAVE,
              id: contact.id,
              name: contact.name,
              phone: contact.phone,
              email: contact.email,
              avatar: contact.avatar
        });
        }.bind(this),
        error: function(xhr, status, err) {
          console.error('http://localhost:8080/JavaServlet/update', status, err.toString());
        }.bind(this)
      });
  },



  _remove: function(removeId) {
    AppDispatcher.dispatch({
      actionType: CMConstants.CM_REMOVE,
      id: removeId
    });
  }

};

module.exports = CMActions;
