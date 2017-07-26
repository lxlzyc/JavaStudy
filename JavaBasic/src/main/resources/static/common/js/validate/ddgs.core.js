/**
 * core
 * @author lxl
 * @version 2016.7.22
 * 
 */

(function($, undefined) {

	$.ddgs = $.ddgs || {};
	$.ddgs.core = $.ddgs.core || {};

	$.extend($.ddgs.core, {

		updateImg : function(img,type) {
			var d = "";
			$.ajax({
				data : {'data':img,'type':type},
				url : 'metronic/image/up/base64',
				type : 'POST',
				async:false, //!!!
				success : function(data) {
					d = data;
				}
			});
			return d;
		},
		//dom a 上传文件展示到dom b
		previewImg:function (file,preview) {
			var objUrl = $.ddgs.core.getObjectURL(file);
            if (!objUrl) {
                objUrl = "";//将图片路径存入src中，显示出图片
            }
            preview.attr("src", objUrl) ;
        },
		getObjectURL:function(file){
            var url = null ;
            if (window.createObjectURL!=undefined) { // basic
                url = window.createObjectURL(file) ;
            } else if (window.URL!=undefined) { // mozilla(firefox)
                url = window.URL.createObjectURL(file) ;
            } else if (window.webkitURL!=undefined) { // webkit or chrome
                url = window.webkitURL.createObjectURL(file) ;
            }
            return url ;
		},
		clearInputFile:function (f){  
		    if(f.value){  
		        try{  
		            f.value = ''; //for IE11, latest Chrome/Firefox/Opera...  
		        }catch(err){  
		        }  
		        if(f.value){ //for IE5 ~ IE10  
		            var form = document.createElement('form'), ref = f.nextSibling, p = f.parentNode;  
		            form.appendChild(f);  
		            form.reset();  
		            p.insertBefore(f,ref);  
		        }  
		    }  
		},
		pluloadFileToFile:function (pluloadFile) {
			var file=new File();
			file.name=pluloadFile.name;
			file.type=pluloadFile.type;
			file.size=pluloadFile.size;
			file.fileSize=pluloadFile.size;
			file.lastModifiedDate=pluloadFile.lastModifiedDate;
			return file;
		}
	});
})(jQuery);