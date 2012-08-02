
Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {
    Ext.define('data.Email', {
        alias : 'data.Email',
        extend: 'Ext.data.Model',

        fields: [{
            name: 'id',
            type: 'long',
            useNull:true
        }, {
            name: 'email',
            type: 'string'
        }, {
            name: 'category',
            type: 'string'
        }],
        validations: [
            {type: 'format', field: 'email', matcher: /[a-z]@[a-z].com/}
        ]
    })
})