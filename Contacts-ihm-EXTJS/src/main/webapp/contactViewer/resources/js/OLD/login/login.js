Ext.define('Ext.sebrevel.Login', {
    extend: 'Ext.FormPanel', // subclass Ext.Component
    alias: 'widget.login', // this component will have an xtype of 'managedimage'

    title : 'Authentification',


    // configuiration par defaut du composant. Ces proprietes peuvent etre surchargé par
    // la map de ocnfig lors du Ext.create
    autoEl: {

        loginLabel : 'Identifiant',
        passwordLabel : 'Mot de passe',

        connectLabel : 'Connection',

        loginVType : 'alpha',
        passwordVType : 'alphanum',

        loginId : 'id',
        passwordId: 'password',

        url: 'save-form.php',

        success : function (f,a) {console.debug ("Login call Success !! ")},
        failure : function (f,a) {Ext.Msg.alert("Login call failed !! ")}
    },

    layout: 'form',
    frame: true,
    bodyPadding: '5 5 0',
    width: 350,

    fieldDefaults: {
        msgTarget: 'side'
    },

    initComponent: function() {
        me = this,

        this.autoEl = Ext.apply({}, this.initialConfig, this.autoEl);

        this.addEvents('beforeLogin', 'login');

        var required = '<span style="color:#ff0000;font-weight:bold" data-qtip="Required">*</span>';

        this.items = [
            {
                xtype : 'textfield',
                fieldLabel: this.autoEl.loginLabel,
                afterLabelTextTpl: required,
                name: this.autoEl.loginId,
                id: this.autoEl.loginId,
                itemId: this.autoEl.loginId,
                allowBlank:false,
                vtype:this.loginVType,
                listeners: {
                    change: this.onTextChange
                }
            },{
                xtype : 'textfield',
                inputType: 'password',
                fieldLabel: this.autoEl.passwordLabel,
                afterLabelTextTpl: required,
                name: this.autoEl.passwordId,
                id: this.autoEl.passwordId,
                itemId: this.autoEl.passwordId,
                allowBlank:false,
                vtype:this.passwordVType,
                listeners: {
                    change:  this.onTextChange
                }
            }
        ];

        this.dockedItems = [{
            xtype: 'toolbar',
            dock: 'bottom',
            ui: 'footer',
            items: ['->', {
                xtype : 'button',
                text: this.autoEl.connectLabel,
                itemId:'connectButton',
                disabled : true,
                handler : function () {
                    var form = this.up('form').getForm();

                    me.fireEvent ("beforeLogin");

                    if (form.isValid()) {
/*
                        // Submit the Ajax request and handle the response
                        form.submit({
                            success: function (f,a) {
                                me.fireEvent ("login");

                                me.success.call (this, arguments);
                            },
                            failure : function (f,a) {
                                me.failure.call (this, arguments);
                            }
                        });
*/
                    }
                }
            }]
        }]

        this.callParent (arguments);
    },

    getLogin : function () {
        return Ext.getCmp (this.autoEl.loginId).value
    },

    getPassword : function () {
        return Ext.getCmp (this.autoEl.passwordId).value
    },

    onTextChange : function (){
        var login = this.value;
        var password = this.ownerCt.items.items[1].value;// TODO faire rech par nom
        var connectButton = this.ownerCt.dockedItems.items[1].items.items[1]; // TODO faire rech par nom

        if (! Ext.isEmpty (login) && ! Ext.isEmpty (password)) {
            connectButton.enable();
        } else {
            connectButton.disable();
        }
    }
});
