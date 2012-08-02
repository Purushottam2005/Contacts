Ext.Loader.setConfig ({
	enabled : true
})

Ext.Loader.setPath({
    'cvm' : 'common/model',
    'cvs' : 'common/store'
});

Ext.application({
    requires: ['Ext.container.Viewport'],

    name: 'ContactViewer',

    appFolder: 'contactViewer',

	controllers : ['Contacts', 'Emails', 'Phones', 'Addresses'],

    launch: function() {

        Ext.create('Ext.container.Viewport', {
            layout: 'vbox',
            items: [
				{xtype: 'contactlist', id: 'contactlist'}, // La grid contenant les contacts
                {
                    xtype:'panel',
                    collapsible: false,
                    layout: 'anchor',
                    defaults: {
                        width : '95%'
                    },
                    items :[
                        {
                            xtype: 'contactedit',
                            id : 'contactedit'

                        },
                        {       // contient les 3 grids d'email, de phone et d address
                            xtype:'panel',
                            title: 'Contact Information',
                            collapsible: true,
                            collapsed: false,
                            height : 230,

                            layout: {
                                type: 'hbox',
                                align: 'stretch'
                            },
                            defaults: {
                                width: 450
                            },
                            items :[
                                {xtype: 'emaillist', id: 'emaillist'},
                                {xtype: 'phonelist', id: 'phonelist'},
                                {xtype: 'addresslist', id: 'addresslist'}
                            ]
                        }
                    ]
                },

				{// Window de creation
                    xtype: 'contactcreator',
                    id: 'contactcreator'
                }

			]
		})
    }
});