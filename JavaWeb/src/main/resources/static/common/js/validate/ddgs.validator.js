/**
 * validator
 * @author lxl
 * @version 2016.7.22
 * 
 */

  
(function( $, undefined ){
	
	$.ddgs.validator = $.ddgs.validator || {};
	
	$.extend( $.ddgs.validator, {
		failStop : false,			// 是否在第一次校验失败后就停止校验
		countByChar : true,     //是否以字符来计算长度
		tipContainer : null		//tipContainer
	});
	
	$.extend( $.ddgs.validator, {
		/*
		 * 多语言资源模板定义：其中{%name}表示被校验项的名称
		 */
		//LANGUAGE_ACCOUNT : "{%name}为" + $.ddgs.validator.ACCOUNTNO_MIN + "位至" + $.ddgs.validator.ACCOUNTNO_MAX + "位的数字",
		LANGUAGE_ACCOUNT : "{%name}为不能大于" + $.ddgs.validator.ACCOUNTNO_MAX + "位的数字",
		LANGUAGE_SELECT : "请选择{%name}",
		LANGUAGE_REQUEST_INPUT : "请输入{%name}，此为必输项",
		LANGUAGE_DATA_ILLEGAL : "请输入合法的{%name}",
		LANGUAGE_SHORTER_THAN_MIN_LENGTH : "{%name}长度不能小于{%minLength}个字符",
		LANGUAGE_LONGER_THAN_MAX_LENGTH : "{%name}长度不能大于{%maxLength}个字符",
		LANGUAGE_LENGTH_NOT_EQUAL : "{%name}长度必须为{%length}",
		LANGUAGE_LESS_THAN_MIN_VALUE : "{%name}不能小于{%minValue}",
		LANGUAGE_GREATER_THAN_MAX_VALUE : "{%name}不能大于{%maxValue}",
		LANGUAGE_NOTLESS :"{%name}不能小于{%otherName}",
		LANGUAGE_NOTGREATER : "{%name}不能大于{%otherName}",
		LANGUAGE_DATATYPE_NOT_INTEGER : "{%name}请输入数字",
		LANGUAGE_DATATYPE_NOT_FLOAT : "{%name}请输入数字",
		LANGUAGE_DATATYPE_NOT_INTEGERNUMBER : "{%name}请输入数字",
		LANGUAGE_DATATYPE_NOT_INTCHAR : "{%name}请输入数字字母",
		LANGUAGE_DATATYPE_NOT_DECIMAL : "{%name}请输入合法的浮点数",
		LANGUAGE_DATATYPE_NOT_CHINESE : "{%name}请输入中文",
		LANGUAGE_NOT_EQUAL : "两次输入的{%name}不相同",
		LANGUAGE_EQUAL : "{%otherName}不能与{%name}相同",
		LANGUAGE_GROUP_NOT_SAME : "需要全部输入或者全部留空",
		LANGUAGE_GROUP_NOT_ONE : "只需要输入其中一项",
		LANGUAGE_GROUP_NOT_SINGLE : "必须并且只需输入其中一项",
		LANGUAGE_GROUP_NO_INPUT : "需要至少输入其中一项",
		LANGUAGE_PHONE_ILLEGAL : "请输入合法的{%name}，格式为区号-电话号-分机号",
		LANGUAGE_GREATER_DATE_TWO : "{%name}不能小于{%otherName}",
		LANGUAGE_DATE_QUERY_TIME : "只能查询近{%yearCount}年记录",
		LANGUAGE_DATE_QUERY_MONTH : "只能查询{%monthCount}个月之间记录",
		LANGUAGE_DATA_BEGIN_GT_NEWDATE : "{%otherName}不能大于当前日期",
		LANGUAGE_DATA_BEGIN_LT_NEWDATE : "{%otherName}不能小于当前日期",
		LANGUAGE_DATA_END_GT_NEWDATE : "{%name}不能大于当前日期",
		LANGUAGE_CONNECTION : "请输入正确的联系电话",
		LANGUAGE_DATATYPE_NOT_INTEGER_PERCENT:"{%name}请输入0-100之内的数字",
		LANGUAGE_DATATYPE_NOT_SPECIAL_REMARK:"{%name}含有非法字符，如<>\'`\"%{}[]():\\@#$&?;等，请重新输入",
		LANGUAGE_DATATYPE_NOT_SPECIALCHAR_REG:"{%name}含有非法字符，如~!^*-+=[]|<>\'\"%{}\\@#$&,.?;:等，请重新输入"
	});
	
	$.extend( $.ddgs.validator, {
		/**
	     * 内置的数据类型定义
	     *       select  : 选择框
	     *       combox  : Liana.Combox组件
	     *       radio   : 单选按钮
	     *       checkbox : 选择框
	     *       file        : 文件选择
	     *       number  : 非负整数
	     *       decimal : 浮点数
	     *       currency    : 金额
	     *       chinese : 中文
	     *       email   : 电子邮件
	     *       date        : 日期
	     *       time        : HHMMSS 格式的时间
	     *       phone   : 固定电话（国内）
	     *       mobile  : 手机号（国内）
	     *       IDNumber: 身份证号码（国内）
	     * @private
	     * @type object 格式说明
	     *   currency:{
	     *       errorTemplet: // 错误信息模板
	     *       valueTester : ( function( value ){
	     *           // 测试函数，需返回true或false
	     *       }),
	     *       fieldTester : ( function( config ){
	     *           // 测试函数，需返回true或false
	     *       }),
	     *       afterPass : ( function( value, fieldConfig ){
	     *           // 测试返回true后的回调函数
	     *       }),
	     *       afterFail : ( function( value, fieldConfig ){
	     *           // 测试返回false后的回调函数
	     *       }),
	     *       output : ( function( fieldConfig ){
	     *           // 返回格式化后的数据
	     *       }),
	     *   },
	     */
	    _typeDefine:{
	        'accountNo':{
	            errorTemplet: $.ddgs.validator.LANGUAGE_ACCOUNT,
	            valueTester : ( function( value ) {
	                return ( $.ddgs.format.isInteger(value) &&  value.length >= $.ddgs.validator.ACCOUNTNO_MIN && value.length <= $.ddgs.validator.ACCOUNTNO_MAX);
	            }),
	            resetter: ( function( fieldConfig ) {
	            	if(fieldConfig.$element.data("accountSelect"))
	            		fieldConfig.$element.data("accountSelect").reset();
	            })
	        },
	        
	        'chinese':{
	            errorTemplet: $.ddgs.validator.LANGUAGE_DATATYPE_NOT_CHINESE,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isChinese(value) );
	            } ),
	            afterFail : ( function( value, fieldConfig,currentElement ){
	                $('#' + fieldConfig.id, currentElement).val("");
	            } )
	        },
	        'checkbox':{
	            output : ( function( fieldConfig ){
	                return $('#' + fieldConfig.id).checked;
	            } )
	        },
	        'combox':{
	            errorTemplet: $.ddgs.validator.LANGUAGE_SELECT,
	            fieldTester : ( function( fieldConfig ){
	                var combox = $('#' + fieldConfig.id).combox;
	                return ( combox.getValue() != null );
	            } ),
	            output : ( function( fieldConfig ){
	                var combox = $('#' + fieldConfig.id).combox;
	                return combox.getValue();
	            } )
	        },	 
	        'decimal':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATATYPE_NOT_DECIMAL,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isDecimal(value) );
	            } )
	        },
	        'email':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATA_ILLEGAL,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isEmail(value) );
	            } )
	        },
	        'IDNumber':{//身份证号验证
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATA_ILLEGAL,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isIDNumber(value) );
	            } )
	        },
	        'number':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATATYPE_NOT_INTEGER,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isInteger(value) );
	            
	            } )
	        },
	        'float':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATATYPE_NOT_FLOAT,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isFloat(value)||$.ddgs.format.isInteger(value) );
	            
	            } )
	        },
	        'allNumber':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATATYPE_NOT_INTEGERNUMBER,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isIntegerNumber(value) );
	            } )
	        },
	        'radio':{
	            output : ( function( fieldConfig ){
	                var radioList = $("input:checked[name="+fieldConfig.id+"]");
	                if ( radioList.length === 0 ){
	                    return null;
	                }
	                return radioList[0].value;
	            } )
	        },
	        'select':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_SELECT,
	            valueTester : ( function( value ){
	                return ( !$.ddgs.format.isEmpty(value) );
	            } )
	        },
	        'time':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATA_ILLEGAL,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isTime(value) );
	            } )
	        },
	        'phone':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_PHONE_ILLEGAL,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isPhone(value) );
	            } )
	        },
	        'telPhone':{
	        	errorTemplet:$.ddgs.validator.LANGUAGE_CONNECTION,
	        	valueTester : ( function( value ){
	        		return ( $.ddgs.format.isTelPhone(value) );
	        	} )
	        },
	        'intChar':{
	        	errorTemplet:$.ddgs.validator.LANGUAGE_DATATYPE_NOT_INTCHAR,
	        	valueTester : ( function( value ){
	        		return ( $.ddgs.format.isIntChar(value) );
	        	} )
	        },
	        'mobile':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATA_ILLEGAL,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isMobile(value) );
	            } )/*,
	             afterFail : ( function( value, fieldConfig,currentElement ){
	                $('#' + fieldConfig.id, currentElement).val("");
	            } )*/
	        },
	        'isConnection':{  //
	            errorTemplet:$.ddgs.validator.LANGUAGE_CONNECTION,
	            valueTester : ( function( value ){
	                return ( $.ddgs.format.isConnection(value) );
	            } )
	        },
	        'number%':{
	            errorTemplet:$.ddgs.validator.LANGUAGE_DATATYPE_NOT_INTEGER_PERCENT,
	            valueTester : ( function( value ){
	            	var val = parseFloat(value);
	            	if($.ddgs.format.isDecimal(value) && 0<val && val <= 100  ) {
	            		return true;
	            	}else{
	            		return false;
	            	}
	            } )
	        }
	    },
	    
		_requiredValidator : function(fieldValue, fieldConfig, ruleHash, currentElement) {
			if(fieldConfig.chineseDisplay){
				$('#' + fieldConfig.chineseDisplay, currentElement).text("零圆整");
			}

			//radio一定检查是否选中
			if (fieldConfig.type == 'radio') {
				var radioList = $("input:checked[name="+fieldConfig.id+"]");
				if ( radioList.length === 0 ){
					this._errorCount++;
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_SELECT, true );
				}
				return true;
			}
			if(fieldValue == "" || fieldValue == null || fieldValue == "null"){
				if (this._ignoreBlank) {
					return false;
				}
				if (fieldConfig.request) {
					this._errorCount++;
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_REQUEST_INPUT );
				} 
				else {
					return false;	//如果没有输入内容，则不再进行后续检验
				}
			}
				
			return true;
		},
	    
		_dataAccesser : function(fieldConfig) {
			
			if (fieldConfig.isExtra) {
				return null;
			}
			if(fieldConfig.type) {
				var typeDefine = $.ddgs.validator._typeDefine[fieldConfig.type];
				if ( typeDefine && typeDefine.output) {
					return typeDefine.output(fieldConfig);
				}
			}
			
			if (fieldConfig.$element == null) {
				alert( "Validator: Field["+fieldConfig.id+"] not found in html!" );
				return null;
			}
			fieldValue = fieldConfig.$element.val();
			if(fieldConfig.formatType == "account") {
				fieldValue = fieldValue.substring(0,fieldValue.indexOf("["));
			}
			return fieldValue;
		},
		
		_errorHandler : function(errors) {
			
		},
		_lengthValidator : function(fieldValue, fieldConfig) {

			if ( fieldConfig.minLength!=null || fieldConfig.maxLength!=null || fieldConfig.length!=null){
				var inputLength = 0;
				if ( $.ddgs.validator.countByChar ){
					// 按字符计算长度
					inputLength = fieldValue.length;
//					inputLength = $.ddgs.validator._check_chinese_char(fieldValue);
				}
				else {
					// 按字节计算长度（编码为UTF-8时）
					for (var index = 0, len = fieldValue.length; index < len; index++) {
						var charCode = fieldValue.charCodeAt(index);
						if (charCode < 0x007f) {
							inputLength ++;
						} 
						else if ((0x0080 <= charCode) && (charCode <= 0x07ff)) {
							inputLength += 2;
						} 
						else if ((0x0800 <= charCode) && (charCode <= 0xffff)) {
							inputLength += 3;
						}
				    }
				}
				if ( fieldConfig.length!=null && inputLength != fieldConfig.length ) {
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_LENGTH_NOT_EQUAL );
				}
				if ( fieldConfig.minLength!=null && inputLength < fieldConfig.minLength ) {
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_SHORTER_THAN_MIN_LENGTH );
				}
				if ( fieldConfig.maxLength!=null && inputLength > fieldConfig.maxLength ) {
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_LONGER_THAN_MAX_LENGTH );
				}
			}
			return true;
		},
		_check_chinese_char : function(str){//字符长度
			var reg = /[^x00-xff]/ ;
			var count = 0;
			for(var i=0;i<str.length;i++){
				if(reg.test(str.charAt(i)) && str.charAt(i)!="y" && str.charAt(i)!="z"){
					count +=2;
				}else{
					count++;
				}
			}
			return count;
		},
		
		_getTargetValue : function (targetId,ruleHash,currentElement){
			var targetConfig = ruleHash[targetId];
			var targetValue;
			if ( targetConfig ){
				// 如果目标类型已定义，使用其输出格式化函数
				var typeDefine = $.ddgs.validator._typeDefine[targetConfig.type];
				if ( typeDefine && typeDefine.output ){
					targetValue = typeDefine.output(targetConfig);
				}
				else {
					targetValue = targetConfig.$element.val();
				}
			}else{
				if(currentElement){
					targetValue = $('#'+targetId,currentElement).val();
				}else{
					targetValue = $('#'+targetId).val();
				}
				
			}
			return targetValue;
		}
		,
		
		_valueRangeValidator : function(fieldValue, fieldConfig,ruleHash,currentElement) {

			if ( fieldConfig.minValue!=null || fieldConfig.maxValue!=null ){
				// 字符串去掉,并转换为浮点数
				var checkValue = parseFloat( fieldValue.replace( new RegExp('\,',["g"]),'') );
				if ( fieldConfig.minValue!=null && checkValue < fieldConfig.minValue ){
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_LESS_THAN_MIN_VALUE );
				}
				if ( fieldConfig.maxValue!=null && checkValue > fieldConfig.maxValue ){
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_GREATER_THAN_MAX_VALUE );
				}
				
				
			}
			
			if(fieldConfig.notlessTarget || fieldConfig.notgreaterTarget){
				var checkValue = parseFloat( fieldValue.replace( new RegExp('\,',["g"]),'') );
				
				var targetId = ( fieldConfig.notlessTarget ) ? fieldConfig.notlessTarget : fieldConfig.notgreaterTarget;
				
				var targetValue = $.ddgs.validator._getTargetValue(targetId,ruleHash,currentElement);
				
				var targetValueTmp = parseFloat( targetValue );
				
				if ( fieldConfig.notlessTarget && checkValue < targetValueTmp   ){//前提是比较数字大小，如果比较字符串大小另行处理
					
						return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_NOTLESS );
				}
				
				if ( fieldConfig.notgreaterTarget && checkValue > targetValueTmp  ){
				
						return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_NOTGREATER );
				}
			}
			
			
			
			return true;
		},
		_dataTypeValidator : function(fieldValue, fieldConfig, ruleHash, currentElement) {
			fieldValue=$.trim(fieldValue);
			var result = true;
			if ( fieldConfig.type!=null ) {
				var typeDefine = $.ddgs.validator._typeDefine[fieldConfig.type];
				
				if ( typeDefine ){
					if ( typeDefine.valueTester ){
						result = typeDefine.valueTester(fieldValue,fieldConfig);
					} 
					else if ( typeDefine.fieldTester ){
						result = typeDefine.fieldTester(fieldConfig);
					}
					if ( result && typeDefine.afterPass ) {
						typeDefine.afterPass(fieldValue, fieldConfig, currentElement);
					}
					if ( !result ){
						if ( typeDefine.afterFail ) {
							typeDefine.afterFail(fieldValue, fieldConfig, currentElement);
						}
						return $.ddgs.validator._getErrorMessage( fieldConfig, typeDefine.errorTemplet );
					}
				}
				else {
					alert( "$.ddgs.Validator: Datatype["+fieldConfig.type+"] not supported!" );	
				}	
			}
			return true;
		},
		
		_regexpValidator : function(fieldValue, fieldConfig) {

			if ( fieldConfig.regexp!=null ){
				var result = ( new RegExp( fieldConfig.regexp ) ).test(fieldValue);
				if ( !result ) {
					return $.ddgs.validator._getErrorMessage( fieldConfig, $.ddgs.validator.LANGUAGE_DATA_ILLEGAL );
				}
			}
			return true;
		},
		
		_equalToValidator : function(fieldValue, fieldConfig, ruleHash,currentElement) {
			// 检查是否和指定域的值相等或不等
			if ( fieldConfig.equalTo || fieldConfig.notEqualTo ){
				var targetId = ( fieldConfig.equalTo ) ? fieldConfig.equalTo : fieldConfig.notEqualTo;
				var targetConfig = ruleHash[targetId];
				var targetValue;
				if ( targetConfig ){
					// 如果目标类型已定义，使用其输出格式化函数
					var typeDefine = $.ddgs.validator._typeDefine[targetConfig.type];
					if ( typeDefine && typeDefine.output ){
						targetValue = typeDefine.output(targetConfig);
					}
					else {
						targetValue = targetConfig.$element.val();
					}
				}else{
					targetValue = $(targetId);
				}
				if ( fieldConfig.equalTo && fieldValue != targetValue  ){
					return $.ddgs.validator._getErrorMessage( targetConfig, $.ddgs.validator.LANGUAGE_NOT_EQUAL );
				}
				if ( fieldConfig.notEqualTo && fieldValue == targetValue  ){
					return $.ddgs.validator._getErrorMessage( targetConfig, $.ddgs.validator.LANGUAGE_EQUAL );
				}
			}
			return true;
		},
		
		_checkerValidator : function(fieldValue, fieldConfig, ruleHash, currentElement) {
			
			/*示例：---------------------------------------------------------------------------------------
			 *
			 * 页面配置：
			   data-validate='{"id":"cardNo", "name":"卡号", "request":true,"checker":"myValidateFunction"}'
			
			  js函数编写：（函数写到pageCallback外面，或者公共函数文件）
			
				function myValidateFunction(fieldConfig,fieldValue,currentElement){
				两种返回方式： 
				return false;  提示用户默认错误：数据不合法
				return "请选择{%name}"  ; 按照自定义模板返回
				成功 return true;
			    }
			*/
			
			// 自定义的校验函数
			if ( fieldConfig.checker != null ){
				var checkerResult = eval(fieldConfig.checker + "(fieldConfig, fieldValue, currentElement)");
				if ( checkerResult !== true ){
					var errorTemplet = ( checkerResult === false ) ? $.ddgs.validator.LANGUAGE_DATA_ILLEGAL : checkerResult;
					return $.ddgs.validator._getErrorMessage( fieldConfig, errorTemplet ); 
				}
			}
			return true;
		},
		
		_relatedValidator : function(fieldValue, fieldConfig) {
		
			if ($.isArray(fieldConfig.related) && this._relatedCheckDepth < 5) {
				this._relatedCheckDepth ++;
				try {
					this.checkFields(fieldConfig.related);
				} 
				catch (e) {
					
				}
				this._relatedCheckDepth --;
			}
		},
		_commonReset: function(fieldConfig, currentElement) {
			if ( fieldConfig.type!=null ) {
				var typeDefine = $.ddgs.validator._typeDefine[fieldConfig.type];
				if ( typeDefine ){
					if ( typeDefine.resetter ) {
						result = typeDefine.resetter(fieldConfig, currentElement);
					}
				}
			}
		},
		
		/**
		* 根据模板生成要显示的错误信息
		* @private
		* @param {object} config 校验配置
		* @param {string} templet 信息模板
		* @returns {string} 生成的错误信息
		*/
		_getErrorMessage : function( config, templet, ignoreCustom ){
			
			// 使用自定义的提示信息模板
			if (!ignoreCustom && config.templet != null) {
				templet = config.templet;
			}
			
			if ( window.Ln ){
				templet = Ln.g( templet, config );
			}
			var result = templet;
			var dataBeginPos = 0;
			while ( true ){
				// 模板中使用%作为变量前缀
				dataBeginPos = result.indexOf( "{%", dataBeginPos );
				if ( dataBeginPos < 0 ){
					break;
				}
				var dataEndPos = result.indexOf( "}", dataBeginPos );
				var dataName = result.substring( dataBeginPos+2, dataEndPos );
				result = result.substring( 0, dataBeginPos ) + config[dataName] + result.substring( dataEndPos+1 );
			}
			return result;
		}
	});

	"use strict";
	
	var Validator = function (element, options) {
		this.$element = $(element);
		this.options = options;
		this._rules = [];
		this.ruleHash = {};
		var self = this;
		$('input, select, textarea' , this.$element).each(function () {
			var $this = $(this);
			var dataValidate = $this.attr('data-validate');
			if(dataValidate) {
				var config = $.parseJSON(dataValidate);
				config.$element = $this;
				self.addRule(config);
			
					$this.focus(function() {
						if(config.clear){
							$('#'+config.id,this.$element).val("");
						}
					});
					//处理日期组件blur事件
					var isDatapick = $this.attr('data-toggle');
					if( isDatapick != 'datapick'){
						$this.blur(function() {
							var result = self.checkRule(config);
							var tipId=config.tipId;//取得自定义的tipId
							if ( !result.passed ) {
							
								self.addErrors(result,$this[0].id);
							}else{
								self.removeErrors($.ddgs.errors,$this[0].id,tipId);
							}
						});
					}else{//是日期
						
						$this.blur(function() {
							//判断日期是否为空///////////
							var dataInputBlank=window.setTimeout(function(){
								window.clearTimeout(dataInputBlank);
								var result = self.checkRule(config);
								
								var tipId=config.tipId;//取得自定义的tipId
							
								if(!result.passed){
									//self.removeErrors($.ddgs.errors,$this[0].id);
									self.addErrors(result,$this[0].id);
								}else{
									self.removeErrors($.ddgs.errors,$this[0].id,tipId);
									window.clearTimeout(dataInputBlank);
								};
								$('input[placeholder]').placeholder($this.parent());
							},250);
							//为空判断结束//////////
						});
					};
					
					
			
			}
		});		
	};
	
	Validator.prototype = {
		constructor: Validator,
		
		addRule: function(config) {
			if ( !config.id ){
				alert( "Validator: Field id must be set!" );
				return;
			}
			
			config.key = config.id;
			
			this._rules.push(config);
			this.ruleHash[config.key] = config;
		},
		
		check: function() {
			var errors = [];
			var requestData = {};
			var signData = [];
			for(var i = 0,size = this._rules.length;i < size; ++i) {
				var rule = this._rules[i];
				var result = this.checkRule(rule);
				if ( result.passed ) {
					requestData[rule.id] = result.data;
				}
				else {
					errors.push(result);
				}
				if(rule.signData) 
					signData.push(rule.id);
			}
			if (errors.length > 0) {	
					$.ddgs.errors = errors;
					
				this.addErrors(errors);
			}else{
				
				this.removeErrors($.ddgs.errors);
			}

			return {
				passed: errors.length == 0,
				requestData: requestData,
				signData: signData
			};
			
		},
		removeErrors: function(errors,id,tipId) {
			var errorRelativeClass = this.options.errorRelativeClass;
			
			if(!tipId){//如果没有自定义tipId
				$("#"+id).parent().parent().find('.'+errorRelativeClass).remove();//移除当前的错误提示
			}else{//自定义了tipId
				$("#"+tipId).empty();
				
			};
			
			if(errors){
				if($.isArray(errors)) {
					for(var i = 0, length = errors.length; i < length; ++i) {
						$("#"+errors[i].key.tipId).empty();
					}
				}else {
					$("#"+errors.tipId).empty();
				};
			};
			
		},
		
		addErrors: function(errors,id) {
			var errorRelativeClass = this.options.errorRelativeClass;
			if(id){
				$("#"+id).parent().parent().find('.'+errorRelativeClass).remove();
			}else{
				$('.'+errorRelativeClass).remove();
			};
			
			if(this.options.errorShowStyle == 'relative'){//相对位置显示
				this.removeErrors(errors);
				if($.isArray(errors)) {
					for(var i = 0, length = errors.length; i < length; ++i) {
						if(errors[i].tipId){
							
							$("#"+errors[i].tipId).css('color','#ff5243').empty().append(errors[i].error);
							continue;
						}
							var $errorTip =$('<div class="'+errorRelativeClass+'" >'+errors[i].error+'</div>');
							//将校验插入到当前验证框之后
							$('#'+errors[i].key).parent().addClass('error-relative').append($errorTip);
				
						
					}
				}else{
					if(errors.tipId){//自定义错误显示域
						
						$("#"+errors.tipId).css('color','#ff5243').empty().append(errors.error);
						return;
					}
						var $errorTip =$('<div class="'+errorRelativeClass+'" >'+errors.error+'</div>');
						
						//将校验插入到当前验证框之后
						$('#'+errors.key).parent().addClass('error-relative').append($errorTip);
					
				}
			}
			if(this.options.errorShowStyle == 'float'){//浮动位置
				
				var htmlStr = "<ul>";

				if($.isArray(errors)) {
					for(var i = 0, length = errors.length; i < length; ++i) {
						htmlStr += '<li>' + errors[i].error + '</li>';
					}
				}
				else {
					htmlStr += '<li>' + errors.error + '</li>';
				}
				htmlStr += "</ul>";
				var errorHtmlObj = $(htmlStr);
				var $errorWindow = $('trans-window-error-showWindow');
				
				if($errorWindow && $errorWindow.length > 0) {
					$errorWindow.remove();
				}
				$.openWindow({
					windowId: 'trans-window-error-showWindow',  //错误提示框的唯一ID
					content: errorHtmlObj,
					windowClass: this.options.errorShowClass,
					appendTo: 'body',
					autoClose: this.options.errorShowTime
				});	
				
				var errorWindowTop=this.options.errorWindowTop;
				//***错误提示定位*//*
				if($(window).scrollTop()>0 && (errorWindowTop-$(window).scrollTop())>0){				
					$('.'+this.options.errorShowClass).css('top',(errorWindowTop-$(window).scrollTop())+'px');
				}
				else if($(window).scrollTop()==0){
					$('.'+this.options.errorShowClass).css('top',errorWindowTop+'px');
				}
				else{
					$('.'+this.options.errorShowClass).css('top','0px');
				}
			}
		},
		 checkRule: function(rule) {
			this.removeErrors($.ddgs.errors);
			var key = rule.key;
			var tipId=rule.tipId;
			var dataAccesser = $.isFunction(rule.dataAccesser) ? rule.dataAccesser : this.options.dataAccesser;
			var batch = rule.batch;
			var validators = rule.validator;
		
			
			//是否检查输入项 false 为不校验
			/*示例
			data-validate='{"id":"rechargeAmt", "name":"充值金额", "checkInput":false}'*/

			 var checkInput=null;
				try{checkInput=$(rule.$element.context).attr('checkInput');}catch(e){};
			
			
			if(null != checkInput && checkInput == 'false'){
				return {passed:true};
			} 

			if (!$.isFunction(dataAccesser))
				return ;
			
			var data = dataAccesser(rule);
			var ignoreCommonValidators = rule.ignoreCommonValidators;
			var commonValidators = this.options.commonValidators;
			if (commonValidators && !ignoreCommonValidators) {
				var commonSize = commonValidators.length;
				
				for (var i=0; i<commonSize; i++) {
					var commonValidator = commonValidators[i];
					if ($.isFunction(commonValidator)) {
						var result = commonValidator(data, rule, this.ruleHash, this.$element);
						if (result === undefined || result == null || result == false) {
							return {
								passed: true,
								key : key,
								error : result,
								data : data,
								rule : rule,
								tipId : tipId
							};
						}
						if (result != true) {
							return {
								passed: false,
								key : key,
								error : result,
								data : data,
								rule : rule,
								tipId : tipId
							};
						}
					}
				}
			}
			
			if (!$.isArray(validators)) {
				validators = [validators];
			}
			var size = validators.size();
			for (var i=0; i<size; i++) {
				var validator = validators[i];
				
				if ($.isFunction(validator)) {
					var result = validator(data, rule);
					if (result === undefined || result == null || result == false) {
						return {
							passed: true,
							key : key,
							error : result,
							data : data,
							rule : rule,
							tipId : tipId
						};
					}
					if (result != true) {
						return {
							passed: false,
							key : key,
							error : result,
							data : data,
							rule : rule,
							tipId : tipId
						};
					}
				}
			}
			return {
				passed: true,
				key : key,
				error : '',  //result  success
				data : data,
				rule : rule,
				tipId : tipId
			};
		},
		
		reset: function() {
			for(var i = 0,size = this._rules.length;i < size; ++i) {
				var rule = this._rules[i];
				$.ddgs.validator._commonReset(rule, this.$element);
			}
		}
	};
	
	$.fn.validator = function ( option ) {
		return this.each(function () {
      		var $this = $(this), 
      			data = $this.data('validator'), 
      			options = $.extend({}, $.fn.validator.defaults, $this.data(), typeof option == 'object' && option);
      		if (!data) 
      			$this.data('validator', (data = new Validator(this, options)));
    	});
	};
	
	$.fn.validator.defaults = {
		dataAccesser : $.ddgs.validator._dataAccesser ,
		errorHandler : $.ddgs.validator._errorHandler ,
		commonValidators : [
           
		    $.ddgs.validator._requiredValidator ,
		    $.ddgs.validator._lengthValidator ,
		    $.ddgs.validator._valueRangeValidator ,
		    $.ddgs.validator._dataTypeValidator ,
		    $.ddgs.validator._equalToValidator ,
		    $.ddgs.validator._checkBeginOrEendDateRange,
		   // $.ddgs.validator._checkRangeDateValidator,
		    $.ddgs.validator._regexpValidator ,
		    $.ddgs.validator._checkerValidator ,
		    $.ddgs.validator._checkerAllOrNull ,
		    $.ddgs.validator._relatedValidator
			
		    
		],
		ignoreCommonValidators:[
		                        
		],
		errorShowTime:5000,
		//------------------------------------------------------此处定义错误样式
		errorRelativeClass:'wms-error',
		errorWindowTop:220,
		errorShowClass:'error-show',
		errorShowStyle:'relative'       //float 为悬浮     //relative 为相对文本框位置显示
	};
	$.fn.validator.Constructor = Validator;
})( window.jQuery );
	
	