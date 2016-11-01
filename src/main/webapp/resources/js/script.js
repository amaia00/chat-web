
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
});