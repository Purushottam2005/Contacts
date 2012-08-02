Ext.define('ContactViewer.controller.Addresses', {
    extend: 'Ext.app.Controller',

    stores: ['cvs.Addresses'],

    models: ['cvm.Address'],

    views: [
        'address.List'
    ],

    init: function() {
        this.control({
            'addresslist': {
                selectionchange: this.selectionChange,
                edit : this.onEdit
            },

            'addresslist button[text=Add]': {
                click : this.onAddClick
            },
            'addresslist button[text=Delete]': {
                click : this.onDeleteClick
            }
        });
    },

    // on ajoute un handler sur l'edition de cellule
    onEdit : function(editor, e) {
        // on recupere le contact actif
        var contacts = this.getController('Contacts');
        var selection = contacts.getSelection ();

        var addresses = selection.get("addresses");
        addresses[e.rowIdx][e.field] = e.value;
        selection.set ("addresses", addresses);

        contacts.isModify(true);
    },

    // sur le changement de selection de ligne, on recupere le bouton delete et on le grise si aucune ligne n'est selectionnee
    selectionChange: function(selModel, selections){
        Ext.getCmp('deleteAddress').setDisabled(selections.length === 0);
    },

    onDeleteClick: function(){
        // on recupere la ligne selectionee
        var list = Ext.getCmp ('addresslist');
        var rec = list.getSelectionModel().getSelection()[0];
        if (rec) {
            // recupere le contact actif dans le formulaire
            var contacts = this.getController('Contacts');
            var selection = contacts.getSelection ();
            var addresses = selection.get("addresses");

            // supprime l address de la liste d address du contact actif
            Ext.each(addresses, function (address, index) {
                if (address && address.id == rec.data.id) {
                    Ext.Array.remove (addresses, address);
                }
            })

            selection.set ("addresses", addresses);

            // supprime du store memory (local) et donc de la vue grid addresses
            var store = Ext.getStore("cvs.Addresses");
            store.remove(rec);

            contacts.isModify(true);
        }
    },

    onAddClick: function(){
        var me = this;
        // On cree en local le nouveau address
        var rec = new cvm.Address({
            category: 'HOME',
            street: '',
            city: '',
            zipCode: '',
            country: 'France'
        });

        var list = Ext.getCmp ("addresslist");
        var edit = list.editing;

        // recupere le contact actif dans le formulaire
        var contacts = this.getController('Contacts');
        var selection = contacts.getSelection ();

        // On ajoute le nouvel address dans le contact actif
        var addresses = selection.get("addresses");
        Ext.Array.insert(addresses, 0, [rec.data]);

        // on insert l address dans le proxy memory (local) ce qui met a jour la grid d'adrresses
        var store = Ext.getStore("cvs.Addresses");
        store.insert(0, rec);

        // On debute l edition sur la premiere cellule
        edit.startEditByPosition({
            row: 0,
            column: 0
        });

        contacts.isModify(true);

        selection.set ("addresses", addresses);
    }
});