
Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {
Ext.define('data.Phone', {
    alias : 'data.Phone',
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'long'
    }, {
        name: 'phone',
        type: 'string'
    }, {
        name: 'category',
        type: 'string'
    }]
})
})