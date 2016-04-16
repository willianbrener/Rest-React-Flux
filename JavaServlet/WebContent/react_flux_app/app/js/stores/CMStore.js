
var AppDispatcher = require('../dispatcher/AppDispatcher');
var EventEmitter = require('events').EventEmitter;
var CMConstants = require('../constants/CMConstants');
var assign = require('object-assign');

var CHANGE_EVENT = 'change';

var _contacts = [];
var _editContact = {};


var currentId = 0;


function create(newContact) {
  _contacts[newContact.id] = {
    id: newContact.id,
    name: newContact.name,
    phone: newContact.phone,
    email: newContact.email,
    avatar: newContact.avatar
  };
  currentId+=1;
}


function edit(contact) {
  _editContact = {
    id: contact.id,
    name: contact.name,
    phone: contact.phone,
    email: contact.email,
    avatar: contact.avatar
  };
}


function save(contact) {
  _contacts[contact.id] = {
    id: contact.id,
    name: contact.name,
    phone: contact.phone,
    email: contact.email,
    avatar: contact.avatar
  };
}


function remove(removeId) {
  if (_contacts.hasOwnProperty(removeId)) {
    delete _contacts[removeId];
  }
}


var CMStore = assign({}, EventEmitter.prototype, {

  getEditContact: function() {
    return _editContact;
  },
  getAll: function() {
    return _contacts;
  },



  emitChange: function() {
    this.emit(CHANGE_EVENT);
  },


  addChangeListener: function(callback) {
    this.on(CHANGE_EVENT, callback);
  },


  removeChangeListener: function(callback) {
    this.removeListener(CHANGE_EVENT, callback);
  }
});


AppDispatcher.register(function(action) {
  var text;

  switch(action.actionType) {
    case CMConstants.CM_CREATE:
      text = action.name.trim();
      if (text !== '') {
        create(action);
        CMStore.emitChange();
      }
      break;

    case CMConstants.CM_EDIT:
      edit(action);
      CMStore.emitChange();
      break;

    case CMConstants.CM_SAVE:
      save(action);
      CMStore.emitChange();
      break;

    case CMConstants.CM_REMOVE:
      remove(action.id);
      CMStore.emitChange();
      break;
    case CMConstants.CM_OPEN_MODAL:
      CMStore.emitChange();
      break;

    default:
      // no op
  }
});

module.exports = CMStore;
