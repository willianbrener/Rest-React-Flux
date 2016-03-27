

var React = require('react');
var Navbar = require('./Navbar.react');
var ContactModal = require('./ContactModal.react');
var EditContactModal = require('./EditContactModal.react');
var ContactList = require('./ContactList.react');
var CMStore = require('../stores/CMStore');
var CMActions = require('../actions/CMActions');


function getContactsState() {
  return {
    allContacts: CMStore.getAll(),
    editContact: CMStore.getEditContact()
  };
}

var CMApp = React.createClass({
  getInitialState: function() {

    this._initializeContacts();
    return getContactsState();
  },
  componentDidMount: function() {
		CMStore.addChangeListener(this._onChange);
  },
  componentWillUnmount: function() {
    CMStore.removeChangeListener(this._onChange);
  },
	render: function() {

    var editId = this.state.editContact.id;
    var editContact = this.state.editContact;
    if (editId !== undefined) {
      $('#edit_contact_modal').openModal();


      $('#edit_contact_form').find('#contact_id').val(editContact.id);
      $('#edit_contact_form').find('#contact_name').val(editContact.name);
      $('#edit_contact_form').find('#contact_phone').val(editContact.phone);
      $('#edit_contact_form').find('#contact_email').val(editContact.email);
      $('#edit_contact_form').find('#contact_avatar').val(editContact.avatar);


      setTimeout(function() {
        $('#edit_contact_form').find('#contact_name').focus();
      },50);



      this.state.editContact.id = undefined;
    }

    return(
      <ul className="collection">
        <Navbar/>
        <ContactList data={this.state.allContacts}/>
        <ContactModal />
        <EditContactModal editContact={this.state.editContact} />
      </ul>

    );
  },

  _onChange: function() {
    this.setState(getContactsState());

  },
  _initializeContacts: function() {

        CMActions._listAll();

  }

});

module.exports = CMApp;
