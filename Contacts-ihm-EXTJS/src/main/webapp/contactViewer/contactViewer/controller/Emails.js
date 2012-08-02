Ext.define('ContactViewer.controller.Emails', {
    extend: 'Ext.app.Controller',

    stores: ['cvs.Emails'],

    models: ['cvm.Email'],

    views: [
        'email.List'
    ],

    init: function() {
        this.control({
            'emaillist': {
                selectionchange: this.selectionChange,
                edit : this.onEdit
            },

            'emaillist button[text=Add]': {
                click : this.onAddClick
            },
            'emaillist button[text=Delete]': {
                click : this.onDeleteClick
            }
        });
    },

    // on ajoute un handler sur l'edition de cellule
    onEdit : function(editor, e) {
        // on recupere le contact actif
        var contacts = this.getController('Contacts');
        var selection = contacts.getSelection ();

        var emails = selection.get("emails");
        emails[e.rowIdx][e.field] = e.value;
        selection.set ("emails", emails);

        contacts.isModify(true);
    },

    // sur le changement de selection de ligne, on recupere le bouton delete et on le grise si aucune ligne n'est selectionnee
    selectionChange: function(selModel, selections){
        Ext.getCmp('deleteEmail').setDisabled(selections.length === 0);
    },

    onDeleteClick: function(){
        // on recupere la ligne selectionee
        var list = Ext.getCmp ('emaillist');
        var rec = list.getSelectionModel().getSelection()[0];
        if (rec) {
            // recupere le contact actif dans le formulaire
            var contacts = this.getController('Contacts');
            var selection = contacts.getSelection ();
            var emails = selection.get("emails");

            // supprime l email de la liste d email du contact actif
            Ext.each(emails, function (email, index) {
                if (email && email.id == rec.data.id) {
                    Ext.Array.remove (emails, email);
                }
            })

            selection.set ("emails", emails);

            // supprime du store memory (local) et donc de la vue grid emails
            var store = Ext.getStore("cvs.Emails");
            store.remove(rec);

            contacts.isModify(true);
        }
    },

    onAddClick: function(){
        var me = this;
        // On cree en local le nouveau email
        var rec = new cvm.Email({
            category: 'HOME',
            email: ''
        });

        var list = Ext.getCmp ("emaillist");
        var edit = list.editing;

        // recupere le contact actif dans le formulaire
        var contacts = this.getController('Contacts');
        var selection = contacts.getSelection ();

        // On ajoute le nouvel email dans le contact actif
        var emails = selection.get("emails");
        Ext.Array.insert(emails, 0, [rec.data]);

        // on insert l address dans le proxy memory (local) ce qui met a jour la grid d'adrresses
        var store = Ext.getStore("cvs.Emails");
        store.insert(0, rec);

        // On debute l edition sur la premiere cellule
        edit.startEditByPosition({
            row: 0,
            column: 0
        });

        contacts.isModify(true);

        selection.set ("emails", emails);
    }
});