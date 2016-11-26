var checked=false;

$( document ).ready(function() {
	var nbreoption = $('.salon .optionnumber').length;
	 $('.salon').hide();
	if(nbreoption<=0){
		$(".clickbt").hide();
	}
	else {
		 $(".clickbt").css("display","inline-block");
		
		 $(".clickbt").click(function(){
			 $('.salon').show();
    	
    	return false;
    
    });
		 $(".salon").change(function(){
		 var valeursalon = $('.salon option:selected').text();
		 $("#basicaddon2input").val(valeursalon);
		 
		 });

	}
	$(".rg input[type=radio]").change(function (e) {
		var selected_value = $("input[name='asdf']:checked").val();
		if (selected_value == "ischecked") {
			checked=true;
		}else{
			checked=false;
		}
        console.log(selected_value+"/"+checked);
	});
    $(".formmessage textarea").keypress(function(e) {
        var keycode;
        if (window.event) keycode = window.event.keyCode;
        else if (e) keycode = e.which;
        else return true;
        console.log(keycode+"/"+checked);

        if (keycode == 13 && checked==true) {
            $(".formmessage").submit();
        }

    });
});