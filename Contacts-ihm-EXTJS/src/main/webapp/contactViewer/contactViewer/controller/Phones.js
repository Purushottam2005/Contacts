Ext.define('ContactViewer.controller.Phones', {
    extend: 'Ext.app.Controller',
	
    stores: ['cvs.Phones'],
    
	models: ['cvm.Phone'],
	
	views: [
        'phone.List'
    ],

    init: function() {
        this.control({
            'phonelist': {
                selectionchange: this.selectionChange,
                edit : this.onEdit
            },

            'phonelist button[text=Add]': {
                click : this.onAddClick
            },
            'phonelist button[text=Delete]': {
                click : this.onDeleteClick
            }
        });
    },

    // on ajoute un handler sur l'edition de cellule
    onEdit : function(editor, e) {
        // on recupere le contact actif
        var contacts = this.getController('Contacts');
        var selection = contacts.getSelection ();

        var phones = selection.get("phones");
        phones[e.rowIdx][e.field] = e.value;
        selection.set ("phones", phones);

        contacts.isModify(true);
    },

    // sur le changement de selection de ligne, on recupere le bouton delete et on le grise si aucune ligne n'est selectionnee
    selectionChange: function(selModel, selections){
        Ext.getCmp('deletePhone').setDisabled(selections.length === 0);
    },

    onDeleteClick: function(){
        // on recupere la ligne selectionee
        var list = Ext.getCmp ('phonelist');
        var rec = list.getSelectionModel().getSelection()[0];
        if (rec) {
            // recupere le contact actif dans le formulaire
            var contacts = this.getController('Contacts');
            var selection = contacts.getSelection ();
            var phones = selection.get("phones");

            // supprime l email de la liste d email du contact actif
            Ext.each(phones, function (phone, index) {
                if (phone && phone.id == rec.data.id) {
                    Ext.Array.remove (phones, phone);
                }
            })

            selection.set ("phones", phones);

            // supprime du store memory (local) et donc de la vue grid emails
            var store = Ext.getStore("cvs.Phones");
            store.remove(rec);

            contacts.isModify(true);
        }
    },

    onAddClick: function(){
        var me = this;
        // On cree en local le nouveau phone
        var rec = new cvm.Phone({
            category: 'HOME',
            phone: ''
        });

        var list = Ext.getCmp ("phonelist");
        var edit = list.editing;

        // recupere le contact actif dans le formulaire
        var contacts = this.getController('Contacts');
        var selection = contacts.getSelection ();

        // On ajoute le nouvel email dans le contact actif
        var phones = selection.get("phones");
        Ext.Array.insert(phones, 0, [rec.data]);

        // on insert l address dans le proxy memory (local) ce qui met a jour la grid d'adrresses
        var store = Ext.getStore("cvs.Phones");
        store.insert(0, rec);

        // On debute l edition sur la premiere cellule
        edit.startEditByPosition({
            row: 0,
            column: 0
        });

        contacts.isModify(true);

        selection.set ("phones", phones);
    }
});