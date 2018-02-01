/**
 *
 */
//ユーザー名を入力する判断
function userCheck(object){
	//alert("abv");
	var username = document.getElementById("username");
	username.setCustomValidity("");
	var password = document.getElementById("password");
	password.setCustomValidity("");
	//alert(username.value);
	if(username.value == null || username.value == ""){
		//alert("dfdfgggggggggg");
			 username.setCustomValidity("ユーザー名を入力してください");
			 return false;
	}
	if(password.value == null || password.value == ""){
		password.setCustomValidity("パスワードを入力してください");
		return false;

	}else{
	return true;
	}
}



