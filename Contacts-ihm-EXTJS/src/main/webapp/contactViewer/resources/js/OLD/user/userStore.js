Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('store.UserStore', {
        alias : 'store.UserStore',
        extend: 'Ext.data.Store',
        storeId:'userStore' ,
        model : 'data.User',
        autoLoad: true,
        autoSync: true,

        proxy : 'UserProxy'
    });


    Ext.ClassManager.instantiate('store.UserStore');

})