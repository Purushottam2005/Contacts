Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('store.PhoneStore', {
        alias : 'store.PhoneStore',
        extend: 'Ext.data.Store',
        storeId:'phoneStore' ,
        model : 'data.Phone',
        autoLoad: true,
        autoSync: true,

        proxy :'memory',

        fields: [ 'id', 'category', 'phone'],

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

    Ext.ClassManager.instantiate('store.PhoneStore');
})