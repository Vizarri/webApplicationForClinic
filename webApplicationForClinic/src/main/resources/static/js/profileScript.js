var passwordIsValid1 = false;
var passwordIsValid2 = false;
var passwordIsValid3 = false;
var emailIsValid = false;
$(document).ready(function(){

	$(".password-control-prof").click(function(){
		if($(".password-control-prof-img").attr('src') == 'img/password-control.png'){
			$(".password-control-prof-img").attr('src','img/password-control-hide.png');
			$("#pass").attr('type','text');
			$("#passRepeat").attr('type','text');
		}
		else{
			$(".password-control-prof-img").attr('src','img/password-control.png');
			$("#pass").attr('type','password');
			$("#passRepeat").attr('type','password');
		}
	});

	$("#passRepeat").keyup(function(){
		if($("#pass").val() != $("#passRepeat").val()){
			$("#profile-equals").addClass("equals");
			passwordIsValid3 = false;
		}
		else{
			$("#profile-equals").removeClass("equals");
			passwordIsValid3 = true;
			if(emailIsValid == true && passwordIsValid1 == true && passwordIsValid2 == true && passwordIsValid3 == true){
				$(".form-button-save").prop('disabled', false);
			}
			else{
				$(".form-button-save").prop('disabled', true);
			}
		}

	});

	$("#pass").keyup(function(){
		if($("#pass").val() != $("#passRepeat").val()){
			$("#profile-equals").addClass("equals");
			passwordIsValid3 = false;
		}
		else{
			$("#profile-equals").removeClass("equals");
			passwordIsValid3 = true;
			if(emailIsValid == true && passwordIsValid1 == true && passwordIsValid2 == true && passwordIsValid3 == true){
				$(".form-button-save").prop('disabled', false);
			}
			else{
				$(".form-button-save").prop('disabled', true);
			}
		}
	});

	$("#pass").keyup(function(){
		var s = /(?=.*[0-9])(?=.*[!@#$%^&*])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z!@#$%^&*]{6,}/g;
		if($("#pass").val().length >= 8) {
			$("#checksym-text").removeClass("checkbox-tip-invalid");
			$("#checksym-text").addClass("checkbox-tip-valid");
			$('#checksym').prop('checked', true);
			passwordIsValid1 = true;
		}
		else {
			$("#checksym-text").removeClass("checkbox-tip-valid");
			$("#checksym-text").addClass("checkbox-tip-invalid");
			$('#checksym').prop('checked', false);
			passwordIsValid1 = false;
		}
		if($("#pass").val().match(s)){
			$("#specsymb-text").removeClass("checkbox-tip-invalid");
			$("#specsymb-text").addClass("checkbox-tip-valid");
			$('#specsymb').prop('checked', true);
			passwordIsValid2 = true;
		}
		else{
			$("#specsymb-text").removeClass("checkbox-tip-valid");
			$("#specsymb-text").addClass("checkbox-tip-invalid");
			$('#specsymb').prop('checked', false);
			passwordIsValid2 = false;
		}
	});

	$("#email").blur(function(){
		var emailSym = /^([A-Za-z0-9_\-\.])+\@([A-Za-z0-9_\-\.])+\.([A-Za-z]{2,4})$/;
		var validation = emailSym.test($("#email").val());

		if(validation == false){
			$("#email-invalid").addClass("email-invalid");
			emailIsValid = false;
		}
		else{
			$("#email-invalid").removeClass("email-invalid");
			emailIsValid = true;
		}
	});

	$(".form-button-save").click(function(){
		if(emailIsValid == false || passwordIsValid1 == false || passwordIsValid2 == false || passwordIsValid3 == false){
			alert("Не все поля заполнены корректно");
		}

	});
});
