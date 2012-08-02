Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('proxy.ContactProxy', {
            alias: 'proxy.ContactProxy',
            extend: 'Ext.data.proxy.Rest',
            type: 'rest',
            url : 'http://localhost:8080/contacts-presentation-JAXRS/rest/contact',

            reader: new Ext.data.JsonReader({
                totalProperty: 'count'
            }),

            writer: jsonWriter,

            buildUrl: function(request) {
                /**
                 * La surcharge de cette mé&thode est obligatoire pour gérer la composite key firstName/lastName
                 */

                var operation = request.operation,
                    records = operation.records || [],
                    record = records[0],
                    format = this.format,
                    url = request.url || this.url,
 //                   id = (operation.action=="update" || operation.action=="destroy") ? record.get("firstName")+"/"+record.get("lastName") : record ? record.getId() : operation.id;
//                id = record ? id : operation.id;
                id = record ? (operation.action=="update" || operation.action=="destroy") ? record.get("firstName")+"/"+record.get("lastName"): "" : operation.id;

                if (this.appendId && id) {
                    if (!url.match(/\/$/)) {
                        url += '/';
                    }
                    url += id;
                }
                if (format) {
                    if (!url.match(/\.$/)) {
                        url += '.';
                    }
                    url += format;
                }
                request.url = url;
                /* Deliberately skip the superclass implementation. */
                return Ext.data.RestProxy.superclass.buildUrl.apply(this, arguments);
            }
        }
    );

})