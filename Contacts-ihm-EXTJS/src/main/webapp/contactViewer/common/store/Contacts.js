Ext.define('cvs.Contacts', {
    extend: 'Ext.data.Store',
    model: 'cvm.Contact',

	autoLoad: true,

	proxy : {

		type: 'rest',
		url : 'http://localhost:8080/contacts-presentation/rest/contact',

		reader: new Ext.data.JsonReader({
			totalProperty: 'count'
		}),

		writer: jsonWriter,

		buildUrl: function(request) {
			/**
			 * La surcharge de cette m�&thode est obligatoire pour g�rer la composite key firstName/lastName
			 */

			var operation = request.operation,
				records = operation.records || [],
				record = records[0],
				format = this.format,
				url = request.url || this.url,

				id = record ? (operation.action=="update" || operation.action=="destroy") ? record.get("firstName")+"/"+record.get("lastName"): "" : operation.id;

            console.log ("operation.action :: "+operation.action+", "+record+", "+id)

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

})