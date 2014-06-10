$(function(){
	//验证资费名 
	var nameFlag = false;
	$('#name').blur(function() {		
		var $msg = $(this).next().next();
		var name = $(this).val();
		$msg.removeClass("error_msg").html("验证中..");
		if(!$(this).ChineseCheck(1,50)){
			$msg.addClass("error_msg").html("50长度的字母、数字、汉字和下划线的组合");
			nameFlag = false;
			return;
		}
		$.post(
		"fee_nameCheck", 
		{"name" : name},
		function(data) {
			if (data) {
				$msg.removeClass("error_msg").html("资费名称可用");
				nameFlag = true;
			} else {
				$msg.addClass("error_msg").html("资费名称重复");
				nameFlag = false;
			}
		});
	});
	//切换资费类型		
	$('#costType0').click(function() {
		$('#baseDuration').attr("readonly", true).addClass("readonly").val("").nextAll('.required').html("");
		$('#baseDuration').next().next().removeClass("error_msg");
		$('#baseCost').attr("readonly", false).removeClass("readonly").nextAll('.required').html("*");
		$('#unitCost').attr("readonly", true).addClass("readonly").val("").nextAll('.required').html("");
		$('#unitCost').next().next().removeClass("error_msg");
	});
	$('#costType1').click(function() {
		$('#baseDuration').attr("readonly", false).removeClass("readonly").nextAll('.required').html("*");
		$('#baseCost').attr("readonly", false).removeClass("readonly").nextAll('.required').html("*");
		$('#unitCost').attr("readonly", false).removeClass("readonly").nextAll('.required').html("*");
	});
	$('#costType2').click(function() {
		$('#baseDuration').attr("readonly", true).addClass("readonly").val("").nextAll('.required').html("");
		$('#baseDuration').next().next().removeClass("error_msg");
		$('#baseCost').attr("readonly", true).addClass("readonly").val("").nextAll('.required').html("");
		$('#baseCost').next().next().removeClass("error_msg");
		$('#unitCost').attr("readonly", false).removeClass("readonly").nextAll('.required').html("*");
	});
	//时长验证
	var durationFlag = false;
	$('#baseDuration').blur(function(){
		var $msg = $(this).next().next().next();
		var n = $(this).val();
		if(n>=0 && n<=600){
			$msg.removeClass("error_msg");
			durationFlag = true;
		}else{
			$msg.addClass("error_msg");
			durationFlag = false;
		}
	});
	//费用验证
	var baseCostFlag = false;	
	$('#baseCost').blur(function(){
		var $msg = $(this).next().next().next();
		var n = $(this).val();
		if(n>=0 && n<=99999.99){
			$msg.removeClass("error_msg");
			baseCostFlag = true;
		}else{
			$msg.addClass("error_msg");
			baseCostFlag = false;
		}
	});
	
	var unitCostFlag = false;
	$('#unitCost').blur(function(){
		var $msg = $(this).next().next().next();
		var n = $(this).val();
		if(n>=0 && n<=999.9999){
			$msg.removeClass("error_msg");
			unitCostFlag = true;
		}else{
			$msg.addClass("error_msg");
			unitCostFlag = false;
		}
	});
	
	//资费信息
	var descrFlag = true;
	$('#descr').blur(function(){
		var $msg = $(this).next();
		if($(this).ChineseCheck(0,100)){
			$msg.removeClass("error_msg");
			descrFlag = true;
		}else{
			$msg.addClass("error_msg");
			descrFlag = false;
		}
	});
	
	$('#save_btn').click(function(){
		if($('#baseDuration').next().next().html() == ""){
			durationFlag = true;
		}
		if($('#baseCost').next().next().html() == ""){
			baseCostFlag = true;
		}
		if($('#unitCost').next().next().html() == ""){
			unitCostFlag = true;
		}
		var flag = nameFlag && durationFlag && baseCostFlag && unitCostFlag && descrFlag;
		if(flag){
			$('#costForm').submit();
		}else{
			alert("信息未通过验证，请检查！");
		}
	});
	if($('#id').val() != null){
		nameFlag =true;
		durationFlag = true;
		baseCostFlag = true;
		unitCostFlag = true;
		descrFlag = true;		
	}
});