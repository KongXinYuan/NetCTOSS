;
(function($) {
	$.fn.extend({
		//rangeLength：
		rangeLength : function(min, max) {
			// 拼一个正则表达式字符串
			var regStr = "/^\\w{" + min + "," + max + "}$/";
			var regExp = eval(regStr);
			var val = $(this).val();
			if (regExp.test(val)) {
				return true;
			} else {
				return false;
			}
		}

	});
	//email：
	$.fn.extend({
		emailCheck : function() {
			var val = $(this).val();
			var reg = /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?$/;
			if (reg.test(val)){
				return true;
			} else {
				return false;
			}
		}
	});
	
	$.fn.extend({
		//中文：
		ChineseCheck : function(min, max) {
			// 拼一个正则表达式字符串
			var regStr = "/^(\\w|[\\u4E00-\\u9FA5]){" + min + "," + max + "}$/";
			var regExp = eval(regStr);
			var val = $(this).val();
			if (regExp.test(val)) {
				return true;
			} else {
				return false;
			}
		}
	});
	
	$.fn.extend({
		//身份证：
		idcardCheck : function() {
			var idcard = $(this).val();
			var ereg = /\./;
			if(idcard.length == 15){
				if ((parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0 || ((parseInt(idcard.substr(6, 2)) + 1900) % 100 == 0 && (parseInt(idcard.substr(6, 2)) + 1900) % 4 == 0)){
					ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;
				} else {
                     ereg = /^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;  
				}				
				if (ereg.test(idcard)) {
					return true;
				} else {
					return false;
			   }
			}
			if(idcard.length == 18){					
					if  (parseInt(idcard.substr(6, 4)) % 4 == 0 || ((parseInt(idcard.substr(6, 4)) % 100 == 0 && parseInt(idcard.substr(6, 4)) % 4 == 0))) {
						ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/; 
					} else {
						ereg = /^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;    
					}					
				   if (ereg.test(idcard)) {
						return true;
					} else {
						return false;
					}
			}
		}
	});	
	
	$.fn.extend({
		//对应值相等
		equalsTo : function(id){
			var str1 = $(this).val();
			var str2 = $(id).val();
			if (str1 == str2){
				return true;
			} else {
				return false;
			}
		}
	
	});
	
	$.fn.extend({
		//电话：
		telephoneCheck : function() {
			var reg = /^(\d{3}-\d{8}|\d{4}-\d{7}|\d{11})$/;
			var val = $(this).val();
			if (reg.test(val)) {
				return true;
			} else {
				return false;
			}
		}
	});
	
	$.fn.extend({
		//出生：
		getBirth : function() {
			var idcard = $(this).val();
			if(idcard.length == 18){
				return idcard.substr(6, 4) + "-" + idcard.substr(10, 2)+ "-" + idcard.substr(12, 2);
			}
			if(idcard.length == 15){
				return "19" + idcard.substr(6, 2) + "-" + idcard.substr(8, 2)+ "-" + idcard.substr(10, 2);
			}
		}
	});
	
	$.fn.extend({
		//腾讯QQ号：
		qqCheck : function() {			
			var reg = /^[1-9]\d{4,12}$/;
			var val = $(this).val();
			if (reg.test(val)) {
				return true;
			} else {
				return false;
			}
		}
	});
	
	$.fn.extend({
		//邮政编码：
		zipcodeCheck : function() {			
			var reg = /^[1-9]\d{5}$/;
			var val = $(this).val();
			if (reg.test(val)) {
				return true;
			} else {
				return false;
			}
		}
	});
	
	$.fn.extend({
		//邮政编码：
		IPCheck : function() {			
			var reg = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/;
			var val = $(this).val();
			if (reg.test(val)) {
				return true;
			} else {
				return false;
			}
		}
	});
	
	
})(jQuery);