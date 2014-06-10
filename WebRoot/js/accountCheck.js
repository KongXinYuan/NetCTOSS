$(function(){
	//真实姓名
	var realNameFlag = false;	
	$('#realName').blur(function(){
		var $msg = $(this).next().next();
		if($(this).ChineseCheck(1,20)){
			$msg.removeClass("error_msg");
			realNameFlag = true;
		}else{
			$msg.addClass("error_msg");
			realNameFlag = false;
		}		
	});
	//身份证
	var idcardNoFlag = false;
	$('#idcardNo').blur(function(){
		var $msg = $(this).next().next();
		if($(this).idcardCheck()){
			$msg.removeClass("error_msg");
			var birth = $(this).getBirth();
			$('#birthdate').val(birth);
		}else{
			$msg.addClass("error_msg");
			$('#birthdate').val("");
			idcardNoFlag = false;
			return;
		}
		$.post(
				"account_idcardNoCheck",
				{"recommenderIdcardNo":$(this).val()},
				function(date){
					if(date == "0"){
						$msg.removeClass("error_msg").html("身份证可用！");
						idcardNoFlag = true;
					} else {						
						$msg.addClass("error_msg").html("此身份证已注册！");
						idcardNoFlag = false;
					}
				}
			);
	});
	
	//用户名
	var loginNameFlag = false;
	$('#loginName').blur(function(){
		var $msg = $(this).next().next();
		if($(this).rangeLength(1,30)){
			$msg.removeClass("error_msg");
			loginNameFlag = true;
		}else{
			$msg.addClass("error_msg");
			loginNameFlag = false;
		}	
	});
	
	//登录密码
	var loginPasswdFlag = false;
	$('#loginPasswd').blur(function(){
		var $msg = $(this).next().next();
		if($(this).rangeLength(1,30)){
			$msg.removeClass("error_msg");
			loginPasswdFlag = true;
		}else{
			$msg.addClass("error_msg");
			loginPasswdFlag = false;
		}					
	});
	
	var oldFlag = false;
	$('#oldPasswd').blur(function(){
		var $msg = $(this).next().next();
		if($(this).rangeLength(1,30)){
			$msg.removeClass("error_msg");
			oldFlag = true;
		}else{
			$msg.addClass("error_msg");
			oldFlag = false;
		}					
	});
	
	//确认密码
	var repeatPwdFlag = false;
	$('#repeatPwd').blur(function(){
		var $msg = $(this).next().next();					
		if($(this).equalsTo('#loginPasswd')){
			$msg.removeClass("error_msg");
			repeatPwdFlag = true;
		}else{
			$msg.addClass("error_msg");
			repeatPwdFlag = false;
		}					
	});
	
	//电话
	var telephoneFlag = false;
	$('#telephone').blur(function(){
		var $msg = $(this).next().next();
		if($(this).telephoneCheck()){
			$msg.removeClass("error_msg");
			telephoneFlag = true;
		}else{
			$msg.addClass("error_msg");
			telephoneFlag = false;
		}					
	});	
	
	//email
	var emailFlag = true;
	$('#email').blur(function(){
		var $msg = $(this).next();
		if($(this).val() == ""||$(this).emailCheck()){
			$msg.removeClass("error_msg");
			emailFlag = true;
		}else{
			$msg.addClass("error_msg");
			emailFlag = false;
		}					
	});
	
	
	//地址
	var mailaddressFlag = true;
	$('#mailaddress').blur(function(){
		var $msg = $(this).next();
		if($(this).val() == ""||$(this).ChineseCheck(1,50)){
			$msg.removeClass("error_msg");
			mailaddressFlag = true;
		}else{
			$msg.addClass("error_msg");
			mailaddressFlag = false;
		}					
	});
	
	//邮编
	var zipcodeFlag = true;
	$('#zipcode').blur(function(){
		var $msg = $(this).next();
		if($(this).val() == ""||$(this).zipcodeCheck()){
			$msg.removeClass("error_msg");
			zipcodeFlag = true;
		}else{
			$msg.addClass("error_msg");
			zipcodeFlag = false;
		}					
	});
	
	//qq
	var qqFlag = true;
	$('#qq').blur(function(){
		var $msg = $(this).next();
		if($(this).val() == ""||$(this).qqCheck()){
			$msg.removeClass("error_msg");
			qqFlag = true;
		}else{
			$msg.addClass("error_msg");
			qqFlag = false;
		}					
	});
	//推荐人
	var recommenderFlag = true;
	$('#reIdcardNo').blur(function(){
		var $msg = $(this).next().next();
		var $id = $(this).next();
		
		if($(this).val() == ""){
			recommenderFlag = true;
			$id.val("");
			$msg.removeClass("error_msg").html("正确的身份证号码格式");
			return;
		}
		$msg.html("验证中...");
		if($(this).idcardCheck()){
			$msg.removeClass("error_msg");
			$id.val("");
		}else{
			$msg.addClass("error_msg").html("请输入正确的身份证号！");
			$id.val("");
			recommenderFlag = false;
			return;
		}
		$.post(
			"account_idcardNoCheck",
			{"recommenderIdcardNo":$(this).val()},
			function(date){
				if(date == "0"){
					$msg.addClass("error_msg").html("查无此人！");
					$id.val("");
					recommenderFlag = false;
				} else {
					$msg.removeClass("error_msg").html("通过验证！");
					$id.val(date);
					recommenderFlag = true;
				}
			}
		);
		
	});
	
	$('#save_btn').click(function(){
		var flag = realNameFlag && idcardNoFlag && loginNameFlag && loginPasswdFlag && repeatPwdFlag && telephoneFlag && emailFlag && mailaddressFlag && zipcodeFlag && qqFlag && recommenderFlag;
		if(flag){
			$('#accountForm').submit();
		}else{
			alert("信息未通过验证");
		}
	});	
	
	$('#modi_btn').click(function(){
		if($(':checked').val() != 1){
			loginPasswdFlag = true;
			repeatPwdFlag = true;
			oldFlag = true;
		}
//		alert(realNameFlag  +","+ loginPasswdFlag +","+ repeatPwdFlag +","+ telephoneFlag +","+ emailFlag +","+ mailaddressFlag +","+ zipcodeFlag +","+ qqFlag +","+ recommenderFlag);
		var flag = realNameFlag  && loginPasswdFlag && repeatPwdFlag && telephoneFlag && emailFlag && mailaddressFlag && zipcodeFlag && qqFlag && recommenderFlag && oldFlag;
		if(flag){
			$('#accountForm').submit();
		}else{
			alert("信息未通过验证");
		}
	});	
	
	if($('#realName').val() != ""){
		realNameFlag = true;
		telephoneFlag = true;
	}

});

