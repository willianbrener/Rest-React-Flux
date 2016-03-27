/**
*
* browserify para lidar com os requisitos do módulo
*/
var React = require('react');

var CMApp = require('./components/CMApp.react');


React.render(
  <CMApp />,
  document.getElementById('cm-holder')
);
