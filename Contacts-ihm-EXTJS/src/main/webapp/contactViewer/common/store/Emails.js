Ext.define('cvs.Emails', {
    extend: 'Ext.data.Store',
    model: 'cvm.Email',

	proxy :'memory',

	fields: [ 'id',  'category', 'email'],

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

