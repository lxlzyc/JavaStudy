/**
 * format 
 * @author lxl
 * @version 2016.7.22
 * 
 */

(function( $, undefined ) {
	if(!Function.bind) {

		Function.prototype.update = function(array, args) {
			var arrayLength = array.length, length = args.length;
		    while (length--) 
		    	array[arrayLength + length] = args[length];
		    return array;
		}
		Function.prototype.merge = function(array, args) {
		    array = Array.prototype.slice.call(array, 0);
		    return Function.update(array, args);
		}
		
		Function.prototype.bind = function(context) {
			if (arguments.length < 2 && typeof arguments[0] === "undefined") return this;
			var __method = this, args = Array.prototype.slice.call(arguments, 1);
			return function() {
				var a = Function.merge(args, arguments);
				return __method.apply(context, a);
			}
		}
		
	}
	/**
	 * 连续count个当前字符串连接
	 * @param {int} count
	 * @returns {string} 
	 */
	String.prototype.times = function(count) {
    	return count < 1 ? '' : new Array(count + 1).join(this);
  	}
	
	/**
	 * 将日期对象根据指定的格式格式化为字符串
	 * @param {string} format 日期格式
	 * @returns {string}
	 */
	Date.prototype.format = function( format ){
		if ( !format ){
			format = $.ddgs.format.DATE_FORMAT;
		}
		return format.replace(
			$.ddgs.format.REGEXP_DATE,
			function(str){
				switch ( str.toLowerCase() ){
					case 'yyyy': return this.getFullYear();
					case 'mm': return (this.getMonth() + 1).toString().leftPadZero(2);
					case 'dd':
						console.info("dd--"+this);
						console.info("dd--"+this.getDate());
						return this.getDate().toString().leftPadZero(2);
					case 'hh': return this.getHours().toString().leftPadZero(2);
					case 'mi': return this.getMinutes().toString().leftPadZero(2);
					case 'ss': return this.getSeconds().toString().leftPadZero(2);
					case 'ms': return this.getMilliseconds().toString().leftPadZero(3);
				}
			}.bind(this)
		);
	};
	
	/**
	 * 比较日期是否为同一天
	 * @param {Date} compareDate 要比较的日期
	 * @returns {boolean} 
	 */
	Date.prototype.isSameDay = function( compareDate ) {
		return ( this.getFullYear() === compareDate.getFullYear() && this.getMonth() === compareDate.getMonth() && this.getDate() === compareDate.getDate() );
	};
	/**
	 * 字符串左补0到指定位数
	 * @param {int} width
	 * @returns {string} 
	 */
	String.prototype.leftPadZero = function( width ) {
		var pad = width - this.length;
		if ( pad > 0 ){
			return ("0".times(pad) + this); 
		}else{
			return this;	
		}
	};
	$.ddgs.format = $.ddgs.format || {};
	
	$.extend( $.ddgs.format, {
		
		MONEY_POSTFIX : "整",
		DATETIME_FORMAT : "yyyymmddhhmiss",
		TIME_FORMAT : "hhmiss",
		TIME_FORMAT_DISPLAY : "hh:mi:ss",
		DATE_FORMAT : "yyyymmdd",
		DATE_FORMAT_DISPLAY : "yyyy年mm月dd日",
		DATE_FORMAT_SHORT : "yyyy-mm-dd",
		
		/**
		* 正则表达式定义，注意：以下正则皆为公用，可添加，勿随意删除（修改前请确认）
		*/
		REGEXP_INTEGER : new RegExp(/^[0-9]+$/),
		REGEXP_ISINTEGERNUMBER : new RegExp(/^-?[0-9]\d*$/),
		REGEXP_FLOAT : new RegExp(/^([0-9]+(\.+))[0-9]+$/),
		REGEXP_DECIMAL : new RegExp(/^([0-9]+(\.?))?[0-9]+$/),
		REGEXP_MONEY : new RegExp(/^[0-9]*\.?[0-9]{0,2}$/),
		REGEXP_COMMA : new RegExp('\,',["g"]),
		REGEXP_DOT : new RegExp('\\.',["g"]),
		REGEXP_EMAIL : new RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/),
		REGEXP_DATE : new RegExp(/(yyyy|mm|dd|hh|mi|ss|ms)/gi),
		REGEXP_PHONE : new RegExp(/^((0\d{2,3})-)(\d{7,8})(-(\d{1,6}))?$/),
		REGEXP_TELPHONE : new RegExp(/^(((((0\d{2,3})-)(\d{7,8})(-(\d{1,6})))|((0\d{2,3})-)(\d{7,8}))|(([2-9][0-9]{6,7})+(\-[0-9]{1,4}))|(([2-9][0-9]{6,7}))|((1[34578])[0-9]{9}))?$/),
		REGEXP_MOBILE : new RegExp(/^(1[1-9])[0-9]{9}$/),
		REGEXP_CONNECTION: new RegExp(/^([0-9]+(\-?))?[0-9]+$/),
		REGEXP_ISINTCHAR : new RegExp(/^[a-zA-Z0-9]+$/),
		REGEXP_CONTAINCHINESE : new RegExp(/^W+$/),
		/**
		* 判断输入变量是否是特殊字符
		* @param {string} s 要检查的变量值
		* @returns {boolean} 是否是特殊字符
		*/
		
		isIncludeChinese: function(s) {
			for (var index = 0, len = s.length; index < len; index++) {
		        var charCode = s.charCodeAt(index);
		        if ( ( charCode >= 19968 ) && (charCode <= 40869) ) {
		            return false;
		        }
		    }
		    return true;
		},
		
		removeComma: function(str){
			return str.replace($.ddgs.format.REGEXP_COMMA,'');
		},
		isEmpty: function(s) {
			return ( s == null || s.length == 0 );
		},

		isInteger: function(s) {
			return ( $.ddgs.format.REGEXP_INTEGER.test(s) );
		},
		
		isIntegerNumber: function(s) {
			return ( $.ddgs.format.REGEXP_ISINTEGERNUMBER.test(s) );
		},
		
		getReadNum:function(s){
			if(s>9999){
				return $.ddgs.format.toDecimal(s/10000)+""+"万"
			}
			return s;
		},
		
		toDecimal:function(s) {  
            var s = parseFloat(s);  
            if (isNaN(s)) {  
                return 0;  
            }  
            return Math.round(s*10)/10;  
        },
        formatByte:function(size){
        	if(!size || size==0){
        		return 0;
        	}
        	if(size<1024){  
                return $.ddgs.format.toDecimal(size)+"bytes";  
            }else if(size<1024*1024){  
                var kbsize = size/1024;    
                return $.ddgs.format.toDecimal(kbsize)+"KB";  
            }else if(size<1024*1024*1024){  
                var mbsize = size/1024/1024;    
                return $.ddgs.format.toDecimal(mbsize)+"MB";  
            }else if(size<1024*1024*1024*1024){  
                var gbsize = size/1024/1024/1024;    
                return $.ddgs.format.toDecimal(gbsize)+"GB";  
            }else{  
                return size;  
            } 
        },
        formatSeconds:function (value) {
        	if(!value){
        		return '0';
        	}
        	if(value == 0){
        		return '0';
        	}
            var theTime = parseInt(value);// 秒

            var theTime1 = 0;// 分

            var theTime2 = 0;// 小时

            if(theTime > 60) {

                theTime1 = parseInt(theTime/60);

                theTime = parseInt(theTime%60);

                    if(theTime1 > 60) {

                    theTime2 = parseInt(theTime1/60);

                    theTime1 = parseInt(theTime1%60);

                    }

            }

                var result = ""+parseInt(theTime)+"秒";

                if(theTime1 > 0) {

                result = ""+parseInt(theTime1)+"分"+result;

                }

                if(theTime2 > 0) {

                result = ""+parseInt(theTime2)+"小时"+result;

                }

            return result;

        },
      //显示舒适的时间格式 最大单位 小时
        formatSecondsSimple:function (value) {
        	if(!value){
        		return '0';
        	}
        	if(value == 0){
        		return '0';
        	}
            var theTime = parseInt(value);// 秒

            var theTime1 = 0;// 分

            var theTime2 = 0;// 小时

            if(theTime > 60) {

                theTime1 = parseInt(theTime/60);

                theTime = parseInt(theTime%60);

                    if(theTime1 > 60) {

                    theTime2 = parseInt(theTime1/60);

                    theTime1 = parseInt(theTime1%60);

                    }

            }
            	
                var result = ""+parseInt(theTime)+"秒";

                if(theTime1 > 0) {
                if(parseInt(theTime)>0){
                	result = ""+parseInt(theTime1)+"分"+result;
                }else{
                	result = ""+parseInt(theTime1)+"分";
                }
                

                }

                if(theTime2 > 0) {
                	if(parseInt(theTime2)>10 || theTime1 == 0){
                		 result = ""+$.ddgs.format.toCashWithOutPonit(parseInt(theTime2))+"小时";
                	}else{
                		result = ""+parseInt(theTime2)+"小时"+parseInt(theTime1)+"分";
                	}
                }

            return result;

        },
		/**
		* 判断输入变量是否是浮点数（即小数点后有数字）
		* @param {string} s 要检查的变量值
		* @returns {boolean} 是否为浮点数
		*/
		isFloat: function( s ){
		    return ( $.ddgs.format.REGEXP_FLOAT.test(s) );
		},

		/**
		* 检查字符串是否为正数（整数或浮点数)
		* @param {string} s 字符串
		* @returns {boolean} 是否为正数（整数或浮点数)
		*/
		isDecimal: function(s) {
		    return ( $.ddgs.format.REGEXP_DECIMAL.test(s) );
		},

		/**
		* 检查字符串是否为合法的金额
		* @param {string} s 字符串
		* @returns {boolean} 是否为合法金额
		*/
		isMoney: function(s) {
			if(s=="."){ //当金额框只输入“.”时，检查不是合法的金额
				return false;
			}
		    return ( $.ddgs.format.REGEXP_MONEY.test(s) );
		},

		/**
		* 检查字符串是否为合法的固定电话号码
		* @param {string} s 字符串
		* @returns {boolean} 是否为合法固定电话号码
		*/
		isPhone: function(s) {
			return ( $.ddgs.format.REGEXP_PHONE.test(s) );
		},
		
		/**
		 * 检查字符串是否为合法的固定电话号码、手机
		 * @param {string} s 字符串
		 * @returns {boolean} 是否为合法固定电话号码
		 */
		isTelPhone: function(s) {
			return ( $.ddgs.format.REGEXP_TELPHONE.test(s) );
		},

		/**
		 * 检查字符串是否为字母和数字
		 * @param {string} s 字符串
		 * @returns {boolean} 是否为合法字母和数字
		 */
		isIntChar: function(s) {
			return ( $.ddgs.format.REGEXP_ISINTCHAR.test(s) );
		},
		
		/**
		* 检查字符串是否为合法的手机号码
		* @param {string} s 字符串
		* @returns {boolean} 是否为合法手机号码
		*/
		isMobile: function(s) {
		    return ( $.ddgs.format.REGEXP_MOBILE.test(s) );
		},
		/**
		* 检查字符串是否为合法联系电话
		* @param {string} s 字符串
		* @returns {boolean} 是否为合法联系电话
		*/
		isConnection: function(s) {
			return ( $.ddgs.format.REGEXP_CONNECTION.test(s) );
		},

		/**
		* 检查字符串是否全部为中文
		* @param {string} s 字符串
		* @returns {boolean} 是否全部为中文
		*/
		isChinese: function(s) {
		    for (var index = 0, len = s.length; index < len; index++) {
		        var charCode = s.charCodeAt(index);
		        if ( ( charCode < 19968 ) || (charCode > 40869) ) {
		            return false;
		        }
		    }
		    return true;
		},

		/**
		* 检查字符串是否为合法的Email
		* @param {string} s 字符串
		* @returns {boolean} 是否为合法Email
		*/
		isEmail: function(s) {
		    if(s.length>50){
		        return false;
		    }
		    return ( $.ddgs.format.REGEXP_EMAIL.test(s) );
		},
		
		/**
		* 检查字符串是否为合法的身份证号码
		* @param {string} s 字符串
		* @returns {boolean} 是否为合法身份证号码
		*/
		isIDNumber: function( s ){
		    // 检查长度是否合法
		    switch(s.length){
		        case 18:{ //case 15: (去掉老身份证校验，老的不用了)
		            break;
		        }
		        default:{
		            return false;
		        }
		    }
		    // 检查是否为数字
		    var testInt = ( s.length==15 ) ? s : s.substr(0,17) ;
		    if( !$.ddgs.format.isInteger(testInt) ) {
		        return false;
		    }
		    return true;
		},
		parseDate: function( dateString, format ){
			var year=2000,month=0,day=1,hour=0,minute=0,second=0;
			format = format ||  $.ddgs.format.DATE_FORMAT;
			var matchArray = format.match( $.ddgs.format.REGEXP_DATE );
			for (var i = 0; i < matchArray.length; i++ ) {
				var postion =format.indexOf( matchArray[i] );
				switch (matchArray[i]) {
					case "yyyy":{
						year = parseInt( dateString.substr(postion,4), 10 );
						break;
					}
					case "mm":{
						month = parseInt( dateString.substr(postion,2), 10 )-1;
						break;
					}
					case "dd":{
						day = parseInt( dateString.substr(postion,2), 10 );
						break;
					}
					case "hh":{
						hour = parseInt( dateString.substr(postion,2), 10 );
						break;
					}
					case "mi":{
						minute = parseInt( dateString.substr(postion,2), 10 );
						break;
					}
					case "ss":{
						second = parseInt( dateString.substr(postion,2), 10 );
						break;
					}
				}
			}
			return new Date(year,month,day,hour,minute,second);
		},
		
		formatDate: function(date, outFormat ) {
			if(date == '' || date == null){
				return '';
			}
			else {
				var parsedDate = $.ddgs.format.parseDate( date, $.ddgs.format.DATE_FORMAT );
				if( outFormat && typeof outFormat === "string" ) {
					return parsedDate.format( outFormat );
				}
				else {
					return parsedDate.format( $.ddgs.format.DATE_FORMAT_SHORT );	
				}
			}
		},
		
		formatTime: function( data, format ){
			var parsedDate = $.ddgs.format.parseDate( data, $.ddgs.format.TIME_FORMAT );
			if( format && typeof outFormat === "string" ) {
				return parsedDate.format( format );
			}
			else {
				return parsedDate.format( $.ddgs.format.TIME_FORMAT_DISPLAY );	
			}
		},
		
		formatDateTime: function( data, format ){
			var parsedDate = $.ddgs.format.parseDate( data, $.ddgs.format.DATETIME_FORMAT );
			if( format && typeof outFormat === "string" ){
				return parsedDate.format( format );
			}
			else {
				return parsedDate.format( $.ddgs.format.DATE_FORMAT_SHORT +" "+ $.ddgs.format.TIME_FORMAT_DISPLAY );	
			}
		},
		addComma: function(str) {
			if (str.length > 3) {
				var mod = str.length % 3;
				var output = (mod > 0 ? (str.substring(0,mod)) : '');
				for (var i=0 ; i < Math.floor(str.length / 3); i++) {
					if ((mod == 0) && (i == 0))
						output += str.substring(mod+ 3 * i, mod + 3 * i + 3);
					else
						output += ',' + str.substring(mod + 3 * i, mod + 3 * i + 3);
				}
				return (output);
			}
			else 
				return str;
		},

		prepareCashString: function( cash, dot, digits ) {
			if (cash == undefined) cash = '0';
			if (dot == undefined) dot = true;
			if (digits == undefined) digits = 2;
			
			if (typeof cash !== 'string') {
				cash = cash.toString();
			}
			cash = $.ddgs.format.removeComma(cash);
			
			//TODO检查是否金额
			// 处理包含正负符号的情况
			var prefix = cash.charAt(0);
			if ( prefix == "-" || prefix == "+" ){
				return prefix + $.ddgs.format.prepareCashString( cash.substr(1), dot, digits );
			}
			
			if (cash.indexOf('.') != -1) {
				dot = true;	//如果输入串本身包含小数点，则忽略dot参数的设置，认为是真实金额大小
			}
			var integerCash, decimalCash;
			if (!dot) {
				if (cash.length <= digits) {
					cash = cash.leftPadZero(digits+1);
				}
				integerCash = cash.substring(0, cash.length - digits);
				decimalCash = cash.substring(cash.length - digits);
			} 
			else {
				var dotPos = cash.indexOf('.');
				if (dotPos != -1) {
					integerCash = cash.substring(0, dotPos);
					decimalCash = cash.substring(dotPos + 1);
				} 
				else {
					integerCash = cash;
					decimalCash = '';
				}
				if (integerCash.length == 0)
					integerCash = '0';
				if (decimalCash.length < digits) {
					decimalCash += '0'.times(digits - decimalCash.length);
				} 
				else {
					decimalCash = decimalCash.substring(0, digits);		//TODO 考虑四舍五入
				}
			}
			
			//去掉头部多余的0
			while (integerCash.charAt(0) == '0' && integerCash.length>1) {
				integerCash = integerCash.substring(1);
			}
			cash = integerCash + '.' + decimalCash;
			
			return cash;
		},
		
		
		toPercentRate: function (rate){
			if($.ddgs.format.isEmpty(rate) ){
				return '';
			}
			if ( parseFloat(rate) == 0 ) {
				return '';
			}
			var temp = parseFloat(rate);
			return temp*100+"%";
		},
		
		//格式化日期，把YYYYMMDD转换YYYY-MM-DD或者YYYYMM转换成YYYY-MM
		dateToDate:function(value){   
			var  flag= "";
			if(value!=null||value!=""){
				if(value.length>=8){
					var yyyy = value.substring(0,4);
					var mm = value.substring(4,6);
					var dd = value.substring(6,8);
					flag = yyyy+"-"+mm+"-"+dd;
				}else if(value.length==6){
					var yyyy = value.substring(0,4);
					var mm = value.substring(4,6);
					flag = yyyy+"-"+mm;
				}
			}
			return flag;
		},
		dataToDate:function(value){   
			var  flag= "";
			if(value!=null && value!=""){
				
				flag = $.ddgs.format.parseDate(value,"yyyymmddhhmiss").format("yyyy-mm-dd hh:mi:ss");
				/*if(value){
					var yyyy = value.substring(0,4);
					var mm = value.substring(4,6);
					var dd = value.substring(6,8);
					flag = yyyy+"-"+mm+"-"+dd;
				}*//*else if(value.length==6){
					var yyyy = value.substring(0,4);
					var mm = value.substring(4,6);
					flag = yyyy+"-"+mm;
				}*/
			}
			return flag;
		},
		dataToDay:function(value){   
			var  flag= "";
			if(value!=null && value!=""){
				if(value.length==8){
					flag = $.ddgs.format.parseDate(value,"yyyymmdd").format("yyyy-mm-dd");
				}else
				flag = $.ddgs.format.parseDate(value,"yyyymmddhhmiss").format("yyyy-mm-dd");
				
				/*if(value){
					var yyyy = value.substring(0,4);
					var mm = value.substring(4,6);
					var dd = value.substring(6,8);
					flag = yyyy+"-"+mm+"-"+dd;
				}*//*else if(value.length==6){
					var yyyy = value.substring(0,4);
					var mm = value.substring(4,6);
					flag = yyyy+"-"+mm;
				}*/
			}
			return flag;
		},
		toCashWithComma: function( cash, dot, digits ) {
			if (cash != null && typeof cash !== "string") {
				cash = cash.toString();
			}
			if(cash == '' || cash == null){
				return '';
			}
			else {
				var temp = $.ddgs.format.prepareCashString( cash, dot, digits );
				
				var dotPos = temp.indexOf('.');
				var integerCash = temp.substring(0, dotPos);
				var decimalCash = temp.substring(dotPos + 1);
			
				// 处理包含正负符号的情况
				var prefix = integerCash.charAt(0);
				if ( prefix == "-" || prefix == "+" ) {
					temp = prefix + $.ddgs.format.addComma(integerCash.substring(1)) + '.' + decimalCash;
				} 
				else {
					temp = $.ddgs.format.addComma(integerCash) + '.' + decimalCash;
				}
				if(temp=="0.00"){
					return '0.00';
				}
				return temp;
			}
		},
		toCashWithOutPonit: function( cash, dot, digits ) {
			if (cash != null && typeof cash !== "string") {
				cash = cash.toString();
			}
			if(cash == '' || cash == null){
				return '';
			}
			else {
				var temp = $.ddgs.format.prepareCashString( cash, dot, digits );
				
				var dotPos = temp.indexOf('.');
				var integerCash = temp.substring(0, dotPos);
				var decimalCash = temp.substring(dotPos + 1);
			
				// 处理包含正负符号的情况
				var prefix = integerCash.charAt(0);
				if ( prefix == "-" || prefix == "+" ) {
					temp = prefix + $.ddgs.format.addComma(integerCash.substring(1));
				} 
				else {
					temp = $.ddgs.format.addComma(integerCash);
				}
				if(temp=="0"){
					return '0';
				}
				return temp;
			}
		},
		getCleanDate:function(date){
			return new Date(date.getFullYear(),date.getMonth(),date.getDate(),0,0,0);
		}
	});
	
	
	$.ddgs.cutString = $.ddgs.cutString || {};
	$.extend( $.ddgs.cutString, {
		cutStringDot:function(stringWord,len){

			var reg = /[^x00-xff]/ ;
			var len = len ? len*2 : 12;//字符串长度
			var count = 0;
			for(var i=0;i<stringWord.length;i++){
				if(reg.test(stringWord.charAt(i)) && stringWord.charAt(i)!="y" && stringWord.charAt(i)!="z"){
					count +=2;
				}else{
					count++;
				}
				if(count >len){
					var str=stringWord.substring(0,i)+'…';
					return str;
				}
			}
			return stringWord;
		}
	});
	
})(jQuery)