/**
 * @author sofiaa faddi
 * @version 1.0
 * @since 1.0 09/12/2016.
 */
var App = {};
var Username = "";
function succes(data) {
    if (data.search("Inscrivez vous en remplissons ce formulaire") != -1) {
        var inscription_ajax = confirm("Vous allez etre redirigé vers la page d'inscription , cliquer sur confirmer");
        if (inscription_ajax != false) {
            window.location = "../inscription.jsp";
        }
        else {
            $('.cn_pr').append("Cliquer sur le lien pour vous inscrire <a href='../inscription.jsp' class='btn btn-primary but' >Inscription</a> ")
        }
    } else {
        Username = document.getElementById("name").value;
        console.log(Username);
        window.location = "../AjaxHtml/profil.html?username=" + Username;
    }

}
function succesGetUser(data) {
    var user = JSON.parse(data);

    /* Profile information */
    $('h3[name=pseudo]').append(user.pseudo);
    $("p[name=donnees]").html(user.prenom + ' ' + user.nom + '<br/>' + user.mail);

    /* liste de salons */
    for (var i in user.salons) {
        if (user.salons.hasOwnProperty(i))
            $("div[name=liste_salons]").append('<a href="#" class="list-group-item">' + user.salons[i].name + '</a>');
    }
}

function succesGetMessages(data) {
    var messages = JSON.parse(data);

    for (var i in messages.messages) {
        console.log("Message " + i + " : " + messages.messages[i].contenu);
        console.log("Message " + i + " : " + messages.messages[i].hourFormatted);
        console.log("Message " + i + " : " + messages.messages[i].user.pseudo);
    }
}

function succesAddMessage(data) {
    var result = data;
    console.log(data);
}

function succesModifyUsername(data) {
    var user = JSON.parse(data);
    $("#changeUsername").hide();

    var newURL = window.location.protocol + "//" + window.location.host + "/" + window.location.pathname
        + '?username=' + user.pseudo;
    location.replace(newURL);
}

function succesDelete(data) {
    console.log("delete : " + data);
}

function error() {
    console.log("error");
}

function loadXMLDoc() {

    // function sendPostRequest(url, params, accept, callback_succes, callback_error, asynchron)
    var params = [];
    params.push({key: "username", value: document.getElementById("name").value});
    params.push({key: "channel", value: document.getElementById("name_salon").value});
    //sendPostRequest: function (url, params, accept, callback_succes, callback_error, asynchron, type) {
    Ajax.sendPostRequest('/chat/back-office/login', params, Ajax.FORM_URL_ENCODE, succes, error, true, Ajax.FORM_URL_ENCODE);
}

function getUser() {
    var params = [];
    var username = window.location.search.substring(1).split("=");
    var pseudo = username[1]
    Ajax.sendGetRequest('/chat/api/users/' + pseudo, params, Ajax.JSON, succesGetUser, error, true);
}

function getMessagesBySalon() {
    var params = [];
    var salon = document.getElementById("name_salon").value;
    Ajax.sendGetRequest('/chat/api/salons/' + salon, params, Ajax.JSON, succesGetMessages, error, true);
}

function modifyUsername() {
    var new_username = $("#username").val();
    /* ici le nouveau pseudo*/
    var params = {
        pseudo: $('h3[name=pseudo]').html()
    }

    Ajax.sendPutRequest('/chat/api/users/' + new_username, params, Ajax.JSON, succesModifyUsername, error, true, Ajax.JSON);
}

function deleteChannel() {
    var params = [];
    var salon = document.getElementById("name_salon").value;
    Ajax.sendDeleteRequest('/chat/api/salons/' + salon, params, Ajax.JSON, succesDelete, error, true);
}

function addMessage() {
    var salon = document.getElementById("name_salon").value;
    var params = {
        id: 3,
        contenu: "nouveau-message",
        user: {
            pseudo: "amaianazabal"
        },
        date: 1480958743859
    };
    Ajax.sendPostRequest('/chat/api/salons/' + salon, params, Ajax.JSON, succesAddMessage, error, true, Ajax.JSON);


}

function newChannel() {
    var salon = $("#name_salon").val();
    console.log("nouveau salon " + salon);
}

console.log("-----------");
console.log(Username);