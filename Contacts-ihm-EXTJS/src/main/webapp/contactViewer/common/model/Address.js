
Ext.define('cvm.Address', {
    extend: 'Ext.data.Model',
    fields: [{
        name: 'id',
        type: 'long'
    }, {
        name: 'street',
        type: 'string'
    }, {
        name: 'city',
        type: 'string'
    }, {
        name: 'zipCode',
        type: 'string'
    }, {
        name: 'country',
        type: 'string'
    }, {
        name: 'category',
        type: 'string'
    }]
})
