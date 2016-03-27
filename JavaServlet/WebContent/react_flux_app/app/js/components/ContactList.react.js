var React = require('react');
var Contact = require('./Contact.react');

var ContactList = React.createClass({
	render:function() {
		var contacts=[];


		for (contact in this.props.data) {
			contacts.push(<Contact contact={this.props.data[contact]} />);
		}
		return(<ul>{contacts}</ul>);
	}
});

module.exports = ContactList;
