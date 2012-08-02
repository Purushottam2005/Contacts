Ext.define('cvs.Phones', {
    extend: 'Ext.data.Store',
    model: 'cvm.Phone',

	proxy :'memory',

	fields: [ 'id', 'category', 'phone'],

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
})