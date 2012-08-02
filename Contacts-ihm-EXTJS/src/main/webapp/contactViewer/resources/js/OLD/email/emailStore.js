Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('store.EmailStore', {
        alias : 'store.EmailStore',
        extend: 'Ext.data.Store',
        storeId:'emailStore' ,
        model : 'data.Email',
        autoLoad: true,
        autoSync: true,

        proxy :'memory',

        fields: [ 'id',  'category', 'email'],

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

    Ext.ClassManager.instantiate('store.EmailStore');

})