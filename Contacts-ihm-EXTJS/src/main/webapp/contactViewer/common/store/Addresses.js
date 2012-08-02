Ext.define('cvs.Addresses', {
    extend: 'Ext.data.Store',
    model: 'cvm.Address',

	proxy :'memory',

	fields: [ 'id',  'category', 'street', 'city', 'zipCode', 'country' ],

	listeners: {
		exception: function(proxy, response, operation){
			Ext.MessageBox.show({
				title: 'REMOTE EXCEPTION',
				msg: operation.getError(),
				icon: Ext.MessageBox.ERROR,
				buttons: Ext.Msg.OK
			});
		}
	}
});

