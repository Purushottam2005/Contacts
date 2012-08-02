Ext.Loader.setConfig ({
    enabled : true
})

Ext.Loader.setPath ({
    'cvm' : 'common/model',
    'cvs' : 'common/store'
});

Ext.application({
    requires:['Ext.container.Viewport'],

    name:'ContactModelTest',

    appFolder:'contactTest',

    controllers:['ContactsTest'],

    launch:function () {

        Ext.create('Ext.container.Viewport', {
            layout:'hbox',
            defaults : {
                xtype : 'button'
            },
            items:[
                {text:'Update Seb', itemId:'update'},
                {text:'Find All', itemId:'findAll'},
                {text:'Find Seb', itemId:'find'},
                {text:'Delete Seb', itemId:'delete'},
                {text:'Find By City (Puteaux)', itemId:'findByCity'}
            ]
        })
    }
});