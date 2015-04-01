//var mol = { calculateMOL: function () { return 42; } };
//module.exports = mol;

var cordova = require('cordova');
var appintent = {
    getIntent: function (successCallback, errorCallback)
    { cordova.exec(successCallback, errorCallback, 'intentPlugin', 'getIntent', []); },
    
};
module.exports = appintent;