Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('data.User', {
        alias : 'data.User',
        extend: 'Ext.data.Model',

        proxy : 'UserProxy',

        idProperty : "login",

        fields: [  {
                name: 'login',
                type: 'string',
                useNull:false
            }, {
                name: 'password',
                type: 'string'
            }, {
                name: 'firstName',
                type: 'string'
            }, {
                name: 'lastName',
                type: 'string'
            }
        ]
    })
})