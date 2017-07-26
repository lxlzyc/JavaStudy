/**
 * param 
 * @author lxl
 * @version 2016.8.15
 * 
 */
(function( $, undefined ) {
	
	$.ddgs.param = $.ddgs.param || {};
	
	$.extend( $.ddgs.param, {
        /**
		 * demo $.ddgs.param.getDisplay("DefaultType",0) = '关闭'
         */
		DefaultType:{
			'0':'关闭',
			'1':'开启'
		},
		getSelect: function( appName, selectCode, filter, blankSelect ) {
			var html = "";
			var map = $.ddgs.param[ appName ];
			if ( !map ) {
				alert( "param: " + appName + " not defined!");
				return null;
			}
			if(blankSelect)
				html += "<option value=\"\">请选择</option>";
			
			for(var key in map){
				var value = map[key];
				if(filter) {
					if(filter.include(key))
						continue;
				}
				if ( selectCode && selectCode == key ) {
					html += "<option value=\""+key+"\" selected=\"selected\">"+value+"</option>";
				}
				else {
					html += "<option value=\""+key+"\" >"+value+"</option>";
				}
			}
			return html;
		},
		getDisplay: function( appName, appValue ){
				var map = $.ddgs.param[ appName ];
				if ( !map ) {
					return null;
				}
				appValue=$.trim(appValue);
				if(map[appValue]==undefined||map[appValue]=='undefined'){
					return appValue;
				}
				return map[appValue];
		}
		
	});
	
})(jQuery)