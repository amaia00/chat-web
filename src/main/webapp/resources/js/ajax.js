/**
 *
 * Library for ajax request
 * Methodes includes:
 *
 * POST
 * GET
 * PUT
 * DELETE
 *
 * @author amaia.nazabal
 * @since 12/7/16.
 * @version 1.0
 */
var Ajax  = {
    JSON : 'JSON',
    XML : 'XML',
    STATUS_OK : 200,
    STATUS_CREATED : 201,
    STATUS_NOT_CONTENT : 204,
    STATUS_ACCEPTED : [this.STATUS_OK, this.STATUS_CREATED, this.STATUS_NOT_CONTENT],

    /*
     * Function for post request
     *
     * @param url = l'url qui ont veut appeler'/connect/{id}'
     * @param params = [{"key": "key", "value" : "value"},
     *                  {"key": "key", "value" : "value"}];
     * @param accept : JSON
     * @param callback_succes : le nom du function qui va appeler si le post atteindre
     * @param callback_error : le nom du function qui va appeler si le post rate
     * @param asynchron : true ou false
     *
     * */

    sendPostRequest: function (url, params, accept, callback_succes, callback_error, asynchron) {
        var headers = [{"header": "Content-Type", "value": "application/x-www-form-urlencoded"}];
        headers.push(this.getAcceptHeader(accept));

        this.sendAjaxRequest("POST", params, url, headers, callback_succes, callback_error, asynchron);
    },

    /*
     * Function for get request
     *
     * @param url = l'url qui ont veut appeler'/connect/{id}'
     * @param params = [{"key": "key", "value" : "value"},
     *                  {"key": "key", "value" : "value"}];
     * @param accept : JSON
     * @param callback_succes : le nom du function qui va appeler si le post atteindre
     * @param callback_error : le nom du function qui va appeler si le post rate
     * @param asynchron : true ou false
     *
     * */

    sendGetRequest : function(data, url, callback_succes, callback_error, asynchron) {
        var headers = [];
        headers.push(this.getAcceptHeader(accept));
        this.sendAjaxRequest("POST", params, url, headers, callback_succes, callback_error, asynchron);
    },

    /*
     * Function for PUT request
     *
     * @param url = l'url qui ont veut appeler'/connect/{id}'
     * @param params = [{"key": "key", "value" : "value"},
     *                  {"key": "key", "value" : "value"}];
     * @param accept : JSON
     * @param callback_succes : le nom du function qui va appeler si le post atteindre
     * @param callback_error : le nom du function qui va appeler si le post rate
     * @param asynchron : true ou false
     *
     * */

    sendPutRequest : function(data, url, callback_succes, callback_error, asynchron) {
        var headers = [];
        headers.push(this.getAcceptHeader(accept));

        this.sendAjaxRequest("PUT", params, url, headers, callback_succes, callback_error, asynchron);
    },

    /*
     * Function for DELETE request
     *
     * @param url = l'url qui ont veut appeler'/connect/{id}'
     * @param params = [{"key": "key", "value" : "value"},
     *                  {"key": "key", "value" : "value"}];
     * @param accept : JSON
     * @param callback_succes : le nom du function qui va appeler si le post atteindre
     * @param callback_error : le nom du function qui va appeler si le post rate
     * @param asynchron : true ou false
     *
     * */

    sendDeleteRequest : function (data, url, callback_succes, callback_error, asynchron) {
        var headers = [];
        headers.push(this.getAcceptHeader(accept));

        this.sendAjaxRequest("DELETE", params, url, headers, callback_succes, callback_error, asynchron);
    },

    /*
     * Function AJAX request
     *
     * @param method = POST ou GET ou DELETE ou PUT
     * @param data = les parametres avec ce format:
     *                 [{"key": "key", "value" : "value"},
     *                  {"key": "key", "value" : "value"}];
     * @param headers : les header qu'on veut envoyer
     * @param callback_succes : le nom du function qui va appeler si le post atteindre
     * @param callback_error : le nom du function qui va appeler si le post rate
     * @param asynchron : true ou false
     *
     * */
    sendAjaxRequest : function (method, data, url, headers, callback_succes, callback_error, asynchron) {
        var xmlhttp = new XMLHttpRequest();

        xmlhttp.onreadystatechange = function () {
            if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                var STATUS_ACCEPTED = [Ajax.STATUS_OK, Ajax.STATUS_CREATED, Ajax.STATUS_NOT_CONTENT];
                if (STATUS_ACCEPTED.indexOf(xmlhttp.status) >= 0) {
                    var data = xmlhttp.responseText;
                    callback_succes(data);
                } else {
                    callback_error();
                }
            }
        };

        /* On l'appel de manière asyncrone */
        xmlhttp.open(method, url, asynchron);

        for (var i in headers) {
            xmlhttp.setRequestHeader(headers[i].header, headers[i].value);
        }

        var params = '';
        var first = false;
        for (var j in data) {
            if (first === true)
                params += '&';

            params += data[j].key + "=" + data[j].value;
            first = true;
        }

        xmlhttp.send(encodeURI(params));
    },

    /*
    * Retourne le header pour accepter soit JSON soit XML
    *
    * @param accept: JSON ou XML
    * */
    getAcceptHeader : function (accept) {
        var obj;
        if (accept === JSON) {
            obj = {header: "accept", value: "application/json"};
        } else {
            obj = {header: "accept", value: "application/xml"};
        }

        return obj;
    }
};
