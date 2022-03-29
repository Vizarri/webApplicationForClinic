//var pass1 = document.getElementsById("password");
//var pass2 = document.getElementsById("passwordRepeat");
//var checksym = document.getElementsById("checksym");
//var checksymText = document.getElementsById("checksym-text");

$(document).ready(function(){

	$(".button-submit").click(function(){
		if($("#password").val().length >= 8) {
			$("#checksym-text").removeClass("checkbox-tip-invalid");
			$("#checksym-text").addClass("checkbox-tip-valid");
		};
	});

	$("#password").keyup(function(){
		if($("#password").val().length >= 8) {
			$("#checksym-text").removeClass("checkbox-tip-invalid");
			$("#checksym-text").addClass("checkbox-tip-valid");
			$('#checksym').prop('checked', true);
		}
		else {
			$("#checksym-text").removeClass("checkbox-tip-valid");
			$("#checksym-text").addClass("checkbox-tip-invalid");
			$('#checksym').prop('checked', false);
		}
		var s = /(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}/g;
		if($("#password").val().match(s)){
			$("#specsymb-text").removeClass("checkbox-tip-invalid");
			$("#specsymb-text").addClass("checkbox-tip-valid");
			$('#specsymb').prop('checked', true);
		}
		else{
			$("#specsymb-text").removeClass("checkbox-tip-valid");
			$("#specsymb-text").addClass("checkbox-tip-invalid");
			$('#specsymb').prop('checked', false);
		}
	});

	$("#passwordRepeat").keyup(function(){
		if($("#password").val() != $("#passwordRepeat").val()){
			$("#equals").addClass("equals");
		}
		else{
			$("#equals").removeClass("equals");
		}
	});

	$("#password").keyup(function(){
		if($("#password").val() != $("#passwordRepeat").val()){
			$("#equals").addClass("equals");
		}
		else{
			$("#equals").removeClass("equals");
		}
	})


});
