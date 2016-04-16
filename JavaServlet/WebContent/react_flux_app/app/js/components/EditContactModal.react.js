var React = require('react');
var CMActions = require('../actions/CMActions');

var EditContactModal = React.createClass({

	render:function() {
		return(
			<div id="edit_contact_modal" className="modal">
				<form id="edit_contact_form" onSubmit={this._saveContact}>
					<div className="modal-content">
						<h4>Editar Contato</h4>
						<div className="input-field">
							<i className="mdi-action-account-circle prefix"></i>
							<input id="contact_name" type="text" className="validate" />

						</div>
						<div className="input-field">
							<i className="mdi-communication-phone prefix"></i>
							<input id="contact_phone" type="tel" className="validate"/>

						</div>
						<div className="input-field">
							<i className="mdi-communication-email prefix"></i>
							<input id="contact_email" type="email" className="validate"/>
						</div>
					</div>
					<input id="contact_id" type="hidden" />
					<input id="contact_avatar" type="hidden" />
					<input type="submit" className="hidden-btn"/>
				</form>

				<div className="modal-footer">
					<a onClick={this._saveContact} className="modal-action modal-close waves-effect waves-green btn-flat">Aperte enter ou clique aqui</a>
					<a onClick={this._removeContact} className="red lighten-4 modal-action modal-close waves-effect waves-red btn-flat">Deletar Contato</a>
				</div>
			</div>
		);

	},


	_saveContact: function(e) {
		e.preventDefault();

		var contact = {};
		var form = $('#edit_contact_form');


		contact.id = form.find('#contact_id').val();
		contact.avatar = form.find('#contact_avatar').val();
		contact.name = form.find('#contact_name').val();
		contact.phone = form.find('#contact_phone').val();
		contact.email = form.find('#contact_email').val();



		CMActions.save(contact);

		this._clearContactForm();
	},

	_removeContact: function(e) {
		e.preventDefault();
		var contact = {};

		contact.id = $('#edit_contact_form').find('#contact_id').val();


		CMActions._remove(contact);

		this._clearContactForm();
	},


	_clearContactForm: function() {
		var form = $('#edit_contact_form');

		form.find('#contact_name').val('');
		form.find('#contact_phone').val('');
		form.find('#contact_email').val('');
		$('#edit_contact_modal').closeModal();
	}
});

module.exports = EditContactModal;
