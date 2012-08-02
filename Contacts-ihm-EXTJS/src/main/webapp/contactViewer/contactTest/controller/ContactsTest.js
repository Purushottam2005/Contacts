Ext.define('ContactModelTest.controller.ContactsTest', {

    extend:'Ext.app.Controller',

    stores:['cvs.Contacts'],

    models:['cvm.Contact'],

    init:function () {
        this.control({

            '#update':{
                click:function () {
                    var contactStore = Ext.getStore("cvs.Contacts");
                    var seb = contactStore.getAt(0);

                    seb.set("birthdate", new Date(1976, 4, 23));

                    seb.set("status", "COUPLE");

                    var emails = seb.get("emails");
                    var phones = seb.get("phones");
                    var addresses = seb.get("addresses");

                    emails[0]["email"] = "sebastien.revel@gmail.com";
                    phones[0]["phone"] = "0616910906";
                    addresses[0]["street"] = "7 rue d'en haut";

                    seb.set("emails", emails);
                    seb.set("phones", phones);
                    seb.set("addresses", addresses);

                    contactStore.update (seb);
                }
            },

            '#findAll':{
                click:function () {
                    // load (GET)    => Find All
                    var contactStore = Ext.getStore("cvs.Contacts");
                    contactStore.load();
                }
            },

            '#find':{
                click:function () {
                    var contactStore = Ext.getStore("cvs.Contacts");
                    contactStore.load ({id  : 'Sebastien/REVEL'})
                }
            },

            '#delete':{
                click:function () {
                    var contactStore = Ext.getStore("cvs.Contacts");
                    var first = contactStore.getAt(0);
                    contactStore.remove (first);
                }
            },

            '#findByCity':{
                click:function () {
                    var contactStore = Ext.getStore("cvs.Contacts");
                    contactStore.load ({id  : 'findByCity/Puteaux'})
                }
            }
        })
    }
}
);