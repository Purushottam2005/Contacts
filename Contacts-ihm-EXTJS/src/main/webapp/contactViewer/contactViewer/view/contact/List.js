Ext.define('ContactViewer.view.contact.List' ,{

	extend: 'Ext.grid.Panel',
    alias : 'widget.contactlist',

    title : 'All Contacts',

	flex: 1,
	height : 200,
	store: 'cvs.Contacts',

    initComponent: function(){
        this.editing = Ext.create('Ext.grid.plugin.CellEditing');

        Ext.apply(this, {
            iconCls: 'icon-grid',
            frame: true,
            plugins: [this.editing],
            dockedItems: [{
                xtype: 'toolbar',
                items: [{
                    iconCls: 'icon-add',
                    text: 'Add',
                    scope: this
                }, {
                    iconCls: 'icon-delete',
                    text: 'Delete',
                    disabled: true,
                    id: 'deleteContact',
                    scope: this
                }]
            }],
            columns: [
                {
                    header: 'FirstName',
                    width: 120,
                    sortable: true,
                    dataIndex: 'firstName'
                },
                {
                    header: 'LastName',
                    width: 120,
                    sortable: true,
                    dataIndex: 'lastName'
                },
                {
                    header: 'Gender',
                    width: 120,
                    sortable: true,
                    dataIndex: 'gender',
                    editor: {
                        xtype: 'combobox',
                        typeAhead: true,
                        triggerAction: 'all',
                        selectOnTab: true,
                        store: [
                            ['MALE','MALE'],
                            ['FEMALE','FEMALE']
                        ],
                        lazyRender: true,
                        listClass: 'x-combo-list-small'
                    }
                },
                {
                    header: 'Status',
                    width: 120,
                    sortable: true,
                    dataIndex: 'status',
                    editor: {
                        xtype: 'combobox',
                        typeAhead: true,
                        triggerAction: 'all',
                        selectOnTab: true,
                        store: [
                            ['SINGLE','SINGLE'],
                            ['ENGAGED','ENGAGED'],
                            ['COUPLE','COUPLE']
                        ],
                        lazyRender: true,
                        listClass: 'x-combo-list-small'
                    }
                },
                {
                    header: 'BirthDate',
                    width: 350,
                    sortable: true,
                    dataIndex: 'birthdate',
                    editor: 'datefield',

                    renderer: function(value) {
                        return Ext.Date.format(value, 'Y-m-d');
                    }
                }
            ]
        });
        this.callParent();
    }
});