function Logger () {
    var _logStr = "";
    this.debug = function (str) {
        console.log (str);
        _logStr += str+"\n";
    };
    this.flush = function () {
        var result = _logStr;
        _logStr = "";
        return result;
    };
}
var log = new Logger ();

function dp (obj) {
    var res = "";
    for (var i in obj ) {
        res += i+" :: "+obj[i] +"\n";
    }
    return res;
}

Ext.Loader.setConfig({enabled:true});

