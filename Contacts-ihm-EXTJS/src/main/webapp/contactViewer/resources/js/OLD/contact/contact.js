Ext.require([
    '*'
]);

Ext.Loader.onReady(function() {

    Ext.define('data.Contact', {
        alias : 'data.Contact',
        extend: 'Ext.data.Model',

        proxy : 'ContactProxy',

        fields: [  {
                name: 'id',
                type: 'int',
                persist : false ,
                useNull:true
            }, {
                name: 'firstName',
                type: 'string'
            }, {
                name: 'lastName',
                type: 'string'
            }, {
                name: 'birthdate',
                type: 'date',
                convert: function(value){
                    return new Date(value);
                }
            }, {
                name: 'gender',
                type: 'string'
            }, {
                name: 'status',
                type: 'string'
            },
            {name: 'activities',persist: true},
            {name:'emails', persist:true},
            {name:'phones', persist:true},
            {name:'addresses', persist:true}
        ] ,
        hasMany:[
            {model: 'Activity', name: 'activities'},
            {model: 'Email', name: 'emails'},
            {model: 'Address', name: 'addresses'},
            {model: 'Phone', name: 'phones'}
        ],
        validations: [
            {type: 'inclusion', field: 'gender',   list: ["MALE", "FEMALE"]}   ,
            {type: 'inclusion', field: 'status',   list: ["COUPLE", "DIVORCED", "ENGAGED"]}
        ]

    })
})