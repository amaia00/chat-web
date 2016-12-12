/**
 * @author sofiaa faddi
 * @version 1.0
 * @since 1.0 09/12/2016.
 *
 * TODO mofifier /chat on ne sait pas ou va etre deploye l'app
 */
var App = {};
var DERNIER_ID_MESSAGE;
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
        window.location = "../AjaxHtml/profil.html?username=" + Username;
    }

}

function goChat(salon) {
    var newURL = getPath() + 'chat.html?salon=' + salon;
    location.replace(newURL);
    
}
/**
 * Cette meethode récupere les messages du salon et appel la fonction succesGetMessages (voir plus bas) en cas de succes
 * et la fonction errorGetMessages sinon
 */
function getMessagesBySalon() {

    var params = [];
    var salon = getQueryParams()['salon'];
    Ajax.sendGetRequest('/chat/api/salons/' + salon, params, Ajax.JSON, succesGetMessagesBySalon, errorGetMessagesBySalon, true);
}
/**
 * Cette méthode génére le html pour montrer tous les messages
 *
 * @param data la réponse de la rêquete
 */
function succesGetMessagesBySalon(data) {
    var messages = JSON.parse(data);
    var i;

    $("#scroll_body").empty().append('<br>');

    for (i in messages.messages) {
        if (messages.messages.hasOwnProperty(i)) {
            var html = '<div id="message_' + messages.messages[i].id + '" class="block_message triangle-border left">'
                + '<h4 class="user_user"><i class="glyphicon glyphicon-user"></i>'
                + messages.messages[i].user.pseudo + ' a dit :</h4>'
                + '<div class="date_user">' + messages.messages[i].hourFormatted + '</div>'
                + '<div style="clear:both"></div>'
                + '<a href="javascript:getMessageById(' + messages.messages[i].id + ')" '
                + 'id="link-' + messages.messages[i].id + '">Voir le contenu</a>'
                + '<h5 class="message_user" id="message-content-' + messages.messages[i].id + '"></h5>'
                + '</div>';

            $("#scroll_body").append(html);

            DERNIER_ID_MESSAGE = messages.messages[i].id;
        }

    }

    if (i === 0) {
        var salon = getQueryParams()['salon'];
        $("#scroll_body").append('<h4>Bienvenue au salon ' + salon + ', dorenavant vous avez la possibilité de ' +
            'discuter avec tous les membres du salon </h4>');
    } else {
        $("#message_" + DERNIER_ID_MESSAGE + ' a').first().after('<a href="javascript:editMessage(' + DERNIER_ID_MESSAGE + ')" '
        + '>Modifier message</a>');

    }

    $('#scroll_body').scrollTop(300);
}
/**
 * Cette méthode montre un text-area pour que l'utilisateur puisse modifier le message
 *
 * @param id_message
 */
function editMessage(id_message) {
    var message = $("#message_" + id_message + ' h5').html();
    $("#message_" + id_message + ' h5').remove();
    $("#message_" + id_message + ' a').each(function () {
        $(this).remove();
    });

    $("#message_" + DERNIER_ID_MESSAGE).append('<textarea autofocus class="form-control" id="content_message" rows="2">'
        + message + '</textarea>')
        .append('<button type="button" onclick="updateLastMessage(' + id_message + ')" class=' +
            '"btn btn-primary btn-xs marge">Sauvegarder</button>')
        .append('<button type="button" onclick="succesUpdateLastMessage()" class="btn btn-default btn-xs marge">' +
            'Annuler</button>');
}

/**
 * Si le status line est 404 on ne fait rien sinon, on montre un erreur
 *
 * @param status status line de la réponse à la rêquete
 */
function errorGetMessagesBySalon(status) {
    if (status === Ajax.STATUS_NOT_FOUND){
        var salon = getQueryParams()['salon'];
        $("#scroll_body").append('<h4>Bienvenue au salon ' + salon + ', dorenavant vous avez la possibilité de ' +
            'discuter avec tous les membres du salon </h4>');
    } else {
        /* TODO traitement des erreurs */
    }
}

function succesAddMessage(data) {
    window.location.reload();
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

function login() {
    var newURL = getPath() + 'profil.html?username=' + document.getElementById("name").value;
    location.replace(newURL);
}

function logout() {
    var newURL = getPath();
    /* On supprime la cookie de l'utilisateur*/
    setCookie('username', '', -5);
    location.replace(newURL);
}

/**
 *
 */
function getUser() {
    var params = [];
    var pseudo = getQueryParams()["username"];

    if (typeof pseudo == 'undefined') {
        pseudo = getCookie("username");
    } else {
        /*TODO traitement d'erreur ou aller au index*/

    }

    Ajax.sendGetRequest('/chat/api/users/' + pseudo, params, Ajax.JSON, succesGetUser, error, true);
}
/**
 *
 * @param data
 */
function succesGetUser(data) {
    var user = JSON.parse(data);

    /*On cree une cookie avec le pseudo de l'utilisateur*/
    setCookie('username', user.pseudo, 5);

    /* Profile information */
    $('h3[name=pseudo]').append(user.pseudo);
    $("p[name=donnees]").html(user.prenom + ' ' + user.nom + '<br/>' + user.mail);

    /* liste de salons */
    for (var i in user.salons) {
        if (user.salons.hasOwnProperty(i))
            $("div[name=liste_salons]").append('<a href="javascript:goChat(\'' + user.salons[i].name + '\')" class="list-group-item">'
                + user.salons[i].name + '</a>');
    }
}

function modifyUsername() {
    var new_username = $("#username").val();

    /* ici le nouveau pseudo*/
    var params = {
        pseudo: $('h3[name=pseudo]').html()
    };

    Ajax.sendPutRequest('/chat/api/users/' + new_username, params, Ajax.JSON, succesModifyUsername, error, true, Ajax.JSON);
}


function updateLastMessage(id_message) {
    var salon = getQueryParams()["salon"];
    var new_username = $("#username").val();
    var content = $("#content_message").val();

    var params = {
        id: id_message,
        contenu: content,
        user: {
            pseudo : new_username
        }
    };

    Ajax.sendPutRequest('/chat/api/messages/' + salon + '/' + id_message,
        params, Ajax.JSON, succesUpdateLastMessage, error, true, Ajax.JSON);
}


function succesUpdateLastMessage() {
    location.reload();
}

function getMessageById(id){
    var params = {};
    Ajax.sendGetRequest('/chat/api/messages/' + id, params, Ajax.JSON, succesGetMessageById, error, true, Ajax.JSON);
}

function succesGetMessageById(data) {
    var message = JSON.parse(data);
    $('#message-content-' + message.id).html(message.contenu);
    $('#link-' + message.id).remove();
}

function deleteChannel() {
    var params = [];
    var salon = document.getElementById("name_salon").value;
    Ajax.sendDeleteRequest('/chat/api/salons/' + salon, params, Ajax.JSON, succesDelete, error, true);
}

/**
 *
 */
function addMessage() {
    var salon = getQueryParams()['salon'];
    var params = {
        id: 3,
        contenu: $('#message-content').val(),
        user: {
            pseudo: getCookie("username")
        }
    };
    Ajax.sendPostRequest('/chat/api/salons/' + salon, params, Ajax.JSON, succesAddMessage, error, true, Ajax.JSON);
}

/**
 *
 */
function newChannel() {
    var params = [];
    params.push({key : "salon", value: $("#name_salon").val()});
    params.push({key : "pseudo", value: getCookie("username")});

    Ajax.sendPostRequest('/chat/api/salons', params, Ajax.JSON, successNewChannel, error, true, Ajax.FORM_URL_ENCODE);
}

/**
 *
 */
function successNewChannel(){
    var salon = $("#name_salon").val();
    goChat(salon);
}

function setURL() {
    var path = getPath() + 'affichage.html?salon=';
    var param = getQueryParams();
    $('#messages').attr('src', path + param['salon'])
}

/**
 *
 * Cette methode nettoye l'url
 *
 * @param url l'url de l'application
 * @returns {string} enlève les données des pages et laisse uniquement l'addresse relative
 */
function cleanURL(url) {
    return(url.replace(/\?.*$/, "").replace(/\/[^\/]*$/, "") + "/");
}

/**
 * Cette methode retourne l'addresse de l'application, l'addresse relative
 *
 * @returns {string}
 */
function getPath() {
    return cleanURL(window.location.protocol + "//" + window.location.host + window.location.pathname);
}

function scrollWindows() {
    var contents = $('#scroll_body').height();
    $(".bgelement").scrollTop(contents);
}

/**
 *
 * Fonction pour récuperer le valeur d'un parametre du link.
 *
 * Par exemple:
 * http:/localhost/chat?salon=name&message=content retourne:
 *
 * ['salon'] = name
 * ['message'] = content
 *
 * Source: http://stackoverflow.com/questions/979975/how-to-get-the-value-from-the-get-parameters#1099670
 * @returns {{}} un objet avec tous les parametres du link
 */
function getQueryParams() {
    var qs = window.location.search;
    qs = qs.split('+').join(' ');

    var params = {},
        tokens,
        re = /[?&]?([^=]+)=([^&]*)/g;

    while (tokens = re.exec(qs)) {
        params[decodeURIComponent(tokens[1])] = decodeURIComponent(tokens[2]);
    }

    return params;
}

/**
 * Fonction pour récuperer une cookie
 * Source: http://www.w3schools.com/js/js_cookies.asp
 *
 * @param cookie_name le nom de la cookie
 * @returns {*} le valeur de la cookie
 */
function getCookie(cookie_name) {
    var name = cookie_name + "=";
    var ca = document.cookie.split(';');
    for(var i = 0; i <ca.length; i++) {
        var c = ca[i];
        while (c.charAt(0)==' ') {
            c = c.substring(1);
        }
        if (c.indexOf(name) == 0) {
            return c.substring(name.length,c.length);
        }
    }
    return "";
}

/**
 * Fonction pour générer une cookie
 * Source: http://www.w3schools.com/js/js_cookies.asp
 *
 * @param cookie_name le nom de la cookie
 * @param cookie_value le valeur de la cookie
 * @param minutes les minutes avant d'expirer
 */
function setCookie(cookie_name, cookie_value, minutes) {
    var d = new Date();
    d.setTime(d.getTime() + (minutes*60*1000));
    var expires = "expires="+ d.toUTCString();
    document.cookie = cookie_name + "=" + cookie_value + ";" + expires + ";path=/";
}