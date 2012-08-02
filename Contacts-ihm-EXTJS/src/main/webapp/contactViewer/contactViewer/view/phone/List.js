Ext.define('ContactViewer.view.phone.List' ,{

	extend: 'Ext.grid.Panel',
    alias : 'widget.phonelist',

    title : 'All Phones',

	height : 200,

	store: 'cvs.Phones',

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
			dockedItems: [{ // barre des boutons ajout / suppression situ� au dessus de la grid
				xtype: 'toolbar',
				items: [{
					iconCls: 'icon-add',
					text: 'Add',
					scope: this
				}, {
					iconCls: 'icon-delete',
					text: 'Delete',
					disabled: true,
					id: 'deletePhone',
					scope: this
				}]
			}],
			columns: [
				{
					hidden: true, // Colonne cach�e qui contiendra l'id unique de la ligne
					dataIndex: 'id' // binding
				},
				{
					header: 'Category',
					width: 100,
					sortable: true,
					dataIndex: 'category', // binding
					editor: {// edition de la cellule par une select
						xtype: 'combobox',
						typeAhead: true,
						triggerAction: 'all',
						selectOnTab: true,
						store: [ // on cree un store local
                            ['WORK','WORK'],
                            ['MOBILE','MOBILE'],
                            ['HOME','HOME']
						],
						lazyRender: true,
						listClass: 'x-combo-list-small'
					}
				},
				{
					header: 'Phone',
					width: 300,
					sortable: true,
					dataIndex: 'phone', // binding
					editor: {// edition de la cellule par un champ text
						xtype: 'textfield',
						allowBlank: false
					}
				}
			]
		});
		this.callParent(); // appelle la methode parente que l'on surcharge
	}
});