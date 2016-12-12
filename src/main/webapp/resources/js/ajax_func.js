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

/**
 * Se deplace a la page chat dans le salon indiqué
 *
 * @param salon le nom du salon
 */
function goChat(salon) {
    var newURL = getPath() + 'chat.html?salon=' + salon;
    location.replace(newURL);
}

/**
 * Il initialise le timer pour s'executer chaque 5 seconds.
 */
function initTimer(){
    setInterval(getLastMessagesAfterId, 5000);
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

function deleteChannel() {
    var params = [];
    var salon = document.getElementById("name_salon").value;
    Ajax.sendDeleteRequest('/chat/api/salons/' + salon, params, Ajax.JSON, succesDelete, error, true);
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

function scrollWindows() {
    var contents = $('#scroll_body').height();
    $(".bgelement").scrollTop(contents);
}