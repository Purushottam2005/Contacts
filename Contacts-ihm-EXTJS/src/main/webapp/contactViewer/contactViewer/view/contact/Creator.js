Ext.define('ContactViewer.view.contact.Creator' ,{

	extend: 'Ext.Window',
    alias : 'widget.contactcreator',

    width: 300,
    height: 230,
    modal : true,

    layout: 'fit',
    title: 'Create Contact',
    items: [
        {
            xtype:'fieldset',
            layout: 'anchor',
            width : '100%',
            items : [
                {
                    fieldLabel: 'First',
                    itemId : 'firstn',
                    id : 'firstn',
                    name: 'firstName',
                    xtype : 'textfield'
                },
                {
                    fieldLabel: 'Last',
                    itemId : 'lastn',
                    id : 'lastn',
                    name: 'lastName',
                    xtype : 'textfield'/*,
                    listeners: {
                        change: onTextChange
                    }*/
                },
                {
                    xtype: 'radiogroup',
                    fieldLabel: 'Gender',
                    itemId : 'gender',
                    name: 'gender',
                    columns: 2,
                    vertical: true,
                    items: [
                        { boxLabel: 'Male', name: 'gender', inputValue: 'MALE', checked: true },
                        { boxLabel: 'Female', name: 'gender', inputValue: 'FEMALE'}

                    ]
                },
                {
                    fieldLabel: 'Status',
                    itemId : 'status',
                    name: 'status',
                    xtype: 'combo',             // select
                    mode:           'local',
                    value:          'SINGLE',
                    forceSelection: true,
                    displayField:   'name',
                    valueField:     'value',
                    queryMode: 'local',         // on cree un store local qui contient les options du select
                    store: Ext.create('Ext.data.Store', {
                        fields : ['name', 'value'],
                        data   : [
                            {name : 'Single',   value: 'SINGLE'},
                            {name : 'Couple',  value: 'COUPLE'},
                            {name : 'Divorced',  value: 'DIVORCED'},
                            {name : 'Engaged', value: 'ENGAGED'}
                        ]
                    })
                },
                {
                    fieldLabel: 'BirthDate',
                    itemId : 'birthdate',
                    name: 'birthdate',
                    xtype: 'datefield',
                    allowBlank: true
                },
                {
                    text:'Create',
                    itemId : 'createB',
                    id : 'createB',
                    disabled : true,
                    xtype : 'button'
                },
                {
                    text:'Annuler',
                    xtype : 'button'
                }

            ]
        }

    ]
});