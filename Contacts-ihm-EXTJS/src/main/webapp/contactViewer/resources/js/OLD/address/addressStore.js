Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('store.AddressStore', {
        alias : 'store.AddressStore',
        extend: 'Ext.data.Store',
        storeId:'addressStore' ,
        model : 'data.Address',
        autoLoad: true,
        autoSync: true,

        proxy :'memory',

        fields: [ 'id',  'category', 'street', 'city', 'zipCode', 'country' ],

        listeners: {
            exception: function(proxy, response, operation){
                Ext.MessageBox.show({
                    title: 'REMOTE EXCEPTION',
                    msg: operation.getError(),
                    icon: Ext.MessageBox.ERROR,
                    buttons: Ext.Msg.OK
                });
            }
        }
    });

    Ext.ClassManager.instantiate('store.AddressStore');
})