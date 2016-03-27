var React = require('react');
var CMActions = require('../actions/CMActions');


var Navbar = React.createClass({
	render:function() {
		return(
			<nav>
	    <div class="nav-wrapper">
					<li className="collection-header">
						<span className="title flow-text">Gerência de Contatos</span>
						<a onClick={this._openAddModal} className="teal darken-1 waves-effect waves-circle waves-light btn-floating secondary-content">
							<i className="mdi-content-add"></i>
						</a>
					</li>

    </div>
  </nav>
		);
	},


	_openAddModal: function() {
		$('#contact_modal').openModal();

		$('#contact_modal').find('#contact_name').focus();
	}
});

module.exports = Navbar;
