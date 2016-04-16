
var AppDispatcher = require('../dispatcher/AppDispatcher');
var CMConstants = require('../constants/CMConstants');

var CMActions = {


   _listAll: function() {
    $.ajax({
      url: 'http://localhost:8080/JavaServlet/list',
      dataType: 'json',
      type: 'GET',
      success: function(data) {
        console.log(data.length);
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

  create: function(newContact, then) {
  	var avatar = 'img/faces/love_java.jpg';
    var message = '';
  	newContact.avatar = avatar;
      $.ajax({
        url: 'http://localhost:8080/JavaServlet/insert',
        dataType: 'text',
        type: 'POST',
        data: newContact,
        success: function(data) {
          console.log(data);
            if(data.length < 10){
              AppDispatcher.dispatch({
                    actionType: CMConstants.CM_CREATE,
                    id : data,
                    name: newContact.name,
                    phone: newContact.phone,
                    email: newContact.email,
                    avatar: newContact.avatar
                  });
            }else{
              console.log(data);
            }
        }.bind(this),
        error: function(xhr, status, err) {
          console.error('http://localhost:8080/JavaServlet/insert', status, err.toString());
        }.bind(this)
      });
      return message;
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
          if(data.length < 10){
            AppDispatcher.dispatch({
                actionType: CMConstants.CM_SAVE,
                id: contact.id,
                name: contact.name,
                phone: contact.phone,
                email: contact.email,
                avatar: contact.avatar
          });
        }else{
          console.log(data);
        }

        }.bind(this),
        error: function(xhr, status, err) {
          console.error('http://localhost:8080/JavaServlet/update', status, err.toString());
        }.bind(this)
      });
  },



  _remove: function(contact) {
    $.ajax({
      url: 'http://localhost:8080/JavaServlet/delete',
      dataType: 'text',
      type: 'POST',
      data: contact,
      success: function(data) {
        AppDispatcher.dispatch({
          actionType: CMConstants.CM_REMOVE,
          id: contact.id
        });

      }.bind(this),
      error: function(xhr, status, err) {
        console.error('http://localhost:8080/JavaServlet/delete', status, err.toString());
      }.bind(this)
    });
  },

   _whatIsIt: function(object) {
    var stringConstructor = "test".constructor;
    var arrayConstructor = [].constructor;
    var objectConstructor = {}.constructor;
      if (object === null) {
          return "null";
      }else if (object === undefined) {
          return "undefined";
      }else if (object.constructor === stringConstructor) {
          return "String";
      }else if (object.constructor === arrayConstructor) {
          return "Array";
      }else if (object.constructor === objectConstructor) {
          return "Object";
      }else {
          return "Integer";
      }
  }

};

module.exports = CMActions;
