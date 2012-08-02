Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('store.ContactStore', {
        alias : 'store.ContactStore',
        extend: 'Ext.data.Store',
        storeId:'contactStore' ,
        model : 'data.Contact',
        autoLoad: true,
        autoSync: true,

        proxy : 'ContactProxy'

    });


    Ext.ClassManager.instantiate('store.ContactStore');

})