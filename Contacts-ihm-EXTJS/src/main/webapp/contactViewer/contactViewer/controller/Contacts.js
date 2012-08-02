Ext.define('ContactViewer.controller.Contacts', {

	extend: 'Ext.app.Controller',
	
    stores: ['cvs.Contacts'],
    
	models: ['cvm.Contact'],

	views: [
        'contact.List',
		'contact.Edit',
        'contact.Creator'
    ],

	init: function() {

        this.control({
            'contactlist': {
                selectionchange: this.selectionChange,
                edit : this.onListChange
            },

            'contactlist button[text=Add]': {
                click : function () {
                    Ext.getCmp('contactcreator').show ();
                }
            },

            'contactlist button[text=Delete]': {
                click : function () {
                    var list = Ext.getCmp ('contactlist');
                    var selection = list.getSelectionModel().getSelection()[0];
                    if (selection) {
                        var store = Ext.getStore("cvs.Contacts");
                        store.remove(selection);

                        store.sync();
                    }
                }
            },

            'contactedit textfield, contactedit checkbox': {
				change : this.onFormChange
			},

            'contactedit button[text=Save]': {
                click : this.onSave
            },

            'contactcreator #firstn, contactcreator #lastn': {
                change : this.onTextChange
            },

            'contactcreator button[text=Create]': {
                click : function () {

                    var win =  Ext.getCmp("contactcreator");
                    var rec = new cvm.Contact({
                        firstName: win.query("#firstn")[0].value,
                        lastName: win.query("#lastn")[0].value,
                        birthdate: win.query("#birthdate")[0].value,
                        gender : win.query("#gender")[0].lastValue.gender,
                        status : win.query("#status")[0].value,
                        activities : [],
                        emails : [],
                        phones : [],
                        addresses : []
                    });

                    var list = Ext.getCmp ("contactlist");
                    edit = list.editing;

                    edit.cancelEdit();
                    var store = Ext.getStore("cvs.Contacts");
                    store.insert(0, rec);

                    this.saveSelection();

                    edit.startEditByPosition({
                        row: 0,
                        column: 2
                    });

                    win.close();
                }
            }
        });
    },

    onTextChange : function (){
        var win =  Ext.getCmp("contactcreator");

        var firstText = win.query("#firstn")[0];
        var lastText = win.query ('#lastn')[0];
        var createButton = win.query ('#createB')[0];

        if (! Ext.isEmpty (firstText.value) && ! Ext.isEmpty (lastText.value)) {
            createButton.enable();
        } else {
            createButton.disable();
        }
    },

    onListChange : function(editor, e) {

        var selection = this.getSelection();

        selection.set (e.field, e.value);

        this.setActiveRecord (selection);

        this.saveSelection();
    },

    saveSelection: function(){
        var selection = this.getSelection();

        selection.setDirty (true);

        var store = Ext.getStore("cvs.Contacts");
        store.sync ();

        this.isModify(false);
    },

    onFormChange : function(f,n,o){

        var selection = this.getSelection();
        selection.set (f.name, f.inputValue?f.inputValue:f.value);

        this.saveSelection();
	},

	// modifie le contact actif affich� ds le formulaire
	isModify: function(modify){
        var view = Ext.getCmp('contactedit')

		if (modify) {
		   view.down('#save').enable();
		} else {
			view.down('#save').disable();
		}
	},


	// modifie le contact actif affich� ds le formulaire
	setActiveRecord: function(record){
        var view =  Ext.getCmp('contactedit');

		if (record) {
			view.getForm().loadRecord(record); // charge les donn�es du contact dans le formulaire
            view.down('#save').enable();
            this.isModify (false);
		} else {
			view.down('#save').disable();
			view.getForm().reset();     // reset le formulaire
		}
	},

    getSelection: function(){
        var list = Ext.getCmp ('contactlist');
        var selection = list.getSelectionModel().getSelection()[0];
        return selection;
    },

    // Sauve le contact actif en base
	onSave: function(){
	    var view =  Ext.getCmp('contactedit');
		var form = view.getForm();

		if (form.isValid()) { // on teste les validations des champs du formulaire

            this.saveSelection();
        }
	},

	selectionChange: function(selModel, selected) {
        Ext.getCmp('deleteContact').setDisabled(selected.length === 0);
/* TODO
        if (this.activeRecord && this.activeRecord.isModify) {
			Ext.Msg.confirm( "Sauvegarde", "Voulez-vous sauver vos modifications avant de quitter ?",
				function (btn)   {
					if(btn == 'yes'){
						this.onSave ();
					}
				}, this
			)
		}

 */
        if (! selected || ! selected[0]) {return}

        var emailStore = Ext.getStore ('cvs.Emails');
		var emails = selected[0].data.emails;
		emailStore.removeAll();
		Ext.each (emails, function (email, index) {
			emailStore.add(email);
		})

        var phoneStore = Ext.getStore ('cvs.Phones');
        var phones = selected[0].data.phones;
		phoneStore.removeAll();
		Ext.each (phones, function (phone, index) {
			phoneStore.add(phone);
		})

        var addressStore = Ext.getStore ('cvs.Addresses');
        var addresses = selected[0].data.addresses;
		addressStore.removeAll();
		Ext.each (addresses, function (address, index) {
			addressStore.add(address);
		})

		// on met a jour le formulaire avec les donnees du nouveau contact actif
        this.setActiveRecord(selected[0] || null);
	}
});