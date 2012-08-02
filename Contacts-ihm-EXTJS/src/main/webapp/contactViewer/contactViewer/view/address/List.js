Ext.define('ContactViewer.view.address.List' ,{

	extend: 'Ext.grid.Panel',
    alias : 'widget.addresslist',

    title : 'All Addresses',

	height : 200,

	store: 'cvs.Addresses',

	requires: [
		'Ext.toolbar.TextItem'
	],

	initComponent: function(){
        this.editing = Ext.create('Ext.grid.plugin.CellEditing'); // permet l edition des cellules de la grid

        Ext.apply(this, {
            iconCls: 'icon-grid',
            frame: true,
            height : 200,
            plugins: [this.editing], // ajoute le plugin d'edition des cellules de la grid
            dockedItems: [{ // barre des boutons ajout / suppression situé au dessus de la grid
                xtype: 'toolbar',
                items: [{
                    iconCls: 'icon-add',
                    text: 'Add'
                }, {
                    iconCls: 'icon-delete',
                    text: 'Delete',
                    id: 'deleteAddress',
                    disabled: true
                }]
            }],
            columns: [
                {
                    hidden: true, // Colonne cachée qui contiendra l'id unique de la ligne
                    dataIndex: 'id' // binding
                },
                {
                    header: 'Category',
                    width: 80,
                    sortable: true,
                    dataIndex: 'category', // binding
                    editor: { // edition de la cellule par une select
                        xtype: 'combobox',
                        typeAhead: true,
                        triggerAction: 'all',
                        selectOnTab: true,
                        store: [ // on cree un store local
                            ['WORK','WORK'],
                            ['HOME','HOME']
                        ],
                        lazyRender: true,
                        listClass: 'x-combo-list-small'
                    }
                },
                {
                    header: 'Street',
                    width: 120,
                    sortable: true,
                    dataIndex: 'street', // binding
                    editor: { // edition de la cellule par un champ text
                        xtype: 'textfield',
                        allowBlank: false
                    }
                },
                {
                    header: 'City',
                    width: 80,
                    sortable: true,
                    dataIndex: 'city', // binding
                    editor: { // edition de la cellule par un champ text
                        xtype: 'textfield',
                        allowBlank: false
                    }
                },
                {
                    header: 'Zip Code',
                    width: 80,
                    sortable: true,
                    dataIndex: 'zipCode', // binding
                    editor: { // edition de la cellule par un champ text
                        xtype: 'textfield',
                        allowBlank: false
                    }
                },
                {
                    header: 'Country',
                    width: 80,
                    sortable: true,
                    dataIndex: 'country', // binding
                    editor: { // edition de la cellule par un champ text
                        xtype: 'textfield',
                        allowBlank: false
                    }
                }
            ]
        });

        this.callParent(); // appelle la methode parente que l'on surcharge
	}
});