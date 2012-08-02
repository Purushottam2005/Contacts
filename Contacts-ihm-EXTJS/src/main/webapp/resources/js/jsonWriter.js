
Ext.require([
    '*'
]);


// Obligatoire pour qu'il commit les phones, addresses et emails avec la personne !!
Ext.define('custom.writer.Json', {
    extend: 'Ext.data.writer.Json',
    writeAllFields: true,
    getRecordData: function(record) {
        var me = this, i, association, childStore, data = {};

        data = record.data;

        for (i = 0; record.associations && i < record.associations.length; i++) {
            association = record.associations.get(i);
            if (association.type == 'hasMany')  {
                childStore =record.get(association.name);
                data[association.name] = [];

                Ext.each(childStore,
                    function(childRecord, index){
                        if (childRecord.internalId){
                            var childData = me.getRecordData.call(me, childRecord);
                            if (childData != null){
                                data[association.name].push(childData);
                            }
                        }
                        else {
                            data[association.name].push(childRecord);
                        }
                    }
                );
            }
        }
        return data;
    }
});
var jsonWriter = Ext.create('custom.writer.Json');

