Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('proxy.UserProxy', {
            alias: 'proxy.UserProxy',
            extend: 'Ext.data.proxy.Rest',
            type: 'rest',
            url : 'http://localhost:8080/contacts-presentation-JAXRS/rest/user'
        }
    );

})