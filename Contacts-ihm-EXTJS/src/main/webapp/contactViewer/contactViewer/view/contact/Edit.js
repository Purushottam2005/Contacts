Ext.define('ContactViewer.view.contact.Edit', {

    extend:'Ext.FormPanel',
    alias:'widget.contactedit',

    frame:true,
    title:'Contact Details',

    height:300,
    width:'90%',

    items:[
        {
            fieldLabel:'First',
            xtype:'textfield',
            name:'firstName',
            disabled : true
        },
        {
            fieldLabel:'Last',
            xtype:'textfield',
            name:'lastName',
            disabled : true
        },
        {
            xtype:'radiofield',
            name:'gender',
            value:'MALE',
            fieldLabel:'Gender',
            boxLabel:'Male',
            inputValue:'MALE',
            disabled : true
        },
        {
            xtype:'radiofield',
            name:'gender',
            value:'FEMALE',
            fieldLabel:'',
            labelSeparator:'',
            hideEmptyLabel:false,
            boxLabel:'Female',
            inputValue:'FEMALE',
            disabled : true
        },
        {
            fieldLabel:'Status',
            name:'status',
            xtype:'combo', // select
            mode:'local',
            value:'SINGLE',
            forceSelection:true,
            displayField:'name',
            valueField:'value',
            queryMode:'local', // on cree un store local qui contient les options du select
            store:Ext.create('Ext.data.Store', {
                fields:['name', 'value'],
                data:[
                    {name:'Single', value:'SINGLE'},
                    {name:'Couple', value:'COUPLE'},
                    {name:'Divorced', value:'DIVORCED'},
                    {name:'Engaged', value:'ENGAGED'}
                ]
            }),
            disabled : true
        },
        {
            fieldLabel:'BirthDate',
            name:'birthdate',
            xtype:'datefield',
            disabled : true
        }/*,
        {
            xtype:'checkboxgroup', // Groupe de checkbox
            fieldLabel:'Activities',
            defaults:{
                name:'activities',
                getValue:getValueCheck,
                setValue:setValueCheck,
                disabled:false
            },
            items:[
                {boxLabel:'Tv', inputValue:'TV', itemId:'TV', id:'TV'},
                {boxLabel:'Games', inputValue:'GAME'},
                {boxLabel:'Sport', inputValue:'SPORT'},
                {boxLabel:'Computer', inputValue:'COMPUTER'}
            ]
        }*/
    ],
    dockedItems:[
        {
            xtype:'toolbar',
            dock:'bottom',
            ui:'footer',
            items:['->', {
                itemId:'save',
                text:'Save',
                disabled:true
            }]
        }
    ]
});


function getValueCheck () {
    if (this.checked) {
        console.log ("name :: "+this.inputValue);
        return  {name : this.inputValue}
    }
    else return {};
}

function setValueCheck (checked) {
    var me = this, boxes, len, box;

    if (Ext.isArray(checked)) {
        boxes = me.getManager().getByName(me.name, me.getFormId()).items;
        len = boxes.length;
        var vals = [];

        Ext.each(checked, function (val, index){
            if (val) {vals[vals.length] = val.name;}

        })

        for (i = 0; i < len; ++i) {
            box = boxes[i];
            box.setValue(Ext.Array.contains(vals, box.inputValue));
        }
    }
    else {
        me.checked = checked;
        me.setRawValue (me.checked);

        me.fireEvent("change", me, me.checked);
    }

}
