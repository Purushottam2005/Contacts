
Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {
Ext.define('data.Activity', {
    alias : 'data.Activity',
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'long'
    }, {
        name: 'name',
        type: 'string'
    }],
    validations: [
        {type: 'inclusion', field: 'name',   list: ["SPORT", "COMPUTER", "TV", "GAME"]}
    ]
})
})

