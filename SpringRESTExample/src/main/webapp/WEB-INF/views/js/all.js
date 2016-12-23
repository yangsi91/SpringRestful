
//form =======================

var set_foodtype_select_form = function(type_id,foodtype_list){
	  var chk_ok=true;
	    $( "#foodtype_name" ).html('');
                               jQuery.each(foodtype_list,function(i, foodtype){
                                    chk="";
                                    if(chk_ok && type_id == foodtype.type_id) {
                                      chk="selected";
                                      chk_ok=false;
                                    }
                                     $( "#foodtype_name" ).append('<option value="'+foodtype.type_id+'"  '+chk+'>'+foodtype.name+'</option>');
                    });
}

var set_fooditem_select_form = function(item_id,fooditem_list){
	  var chk_ok=true;
	      $( "#fooditem_name" ).html('');
                               jQuery.each(fooditem_list,function(i, fooditem){
                                    chk="";
                                    if(chk_ok && item_id == fooditem.ingredient_id) {
                                      chk="selected";
                                      chk_ok=false;
                                    }
                                     $( "#fooditem_name" ).append('<option value="'+ fooditem.ingredient_id+'"  '+chk+'>'+ fooditem.kor_name+'</option>');
                    });
}

var set_handle_select_form = function(handle_id,handle_list){
	  var chk_ok=true;
	    $( "#handle_name" ).html('');
                     jQuery.each(handle_list,function(i, handle){
                                    chk="";
                                    if(chk_ok && handle_id == handle.handling_id) {
                                      chk="selected";
                                      chk_ok=false;
                                    }
                                     $( "#handle_name" ).append('<option value="'+ handle.handling_id+'"  '+chk+'>'+ handle.name+'</option>');
                    });
}

var set_sidebar = function(k,sidebars){

                sidebars.each(function(i) {
                      $(this).removeClass('acitve');
                     if( i== k) {
                        $(this).addClass('acitve');
                    }
                });
}

var  foodtype_set = function(type_id){
	form_data("/foodtype/list",null,function(data){
		set_foodtype_select_form(type_id,data.data);
		fooditem_set(type_id);
	});

}

var  fooditem_set = function(type_id,item_id){
	var senddata = {"type_id": type_id};
	form_data("/fooditem/foodtype",senddata,function(data){
		set_fooditem_select_form(item_id,data.data);
		handle_set(item_id);
	});

}

var  handle_set = function(item_id,handle_id){
	var senddata = {"ingredient_id": item_id};
	form_data("/foodhandle/fooditem",senddata,function(data){
		set_handle_select_form(handle_id,data.data);
	});
}

$(document).on( "click", "#recipe-content-edit-btn", function( e) {
  e.preventDefault();

   var content =    $('#recipe_content').html();
    $('#recipe_content').summernote({height: 380});
    $('#recipe_content').summernote('code', content);
    $('#recipe_content_edit').html('');
    $('#modal-save-btn').attr("disabled",false);

});

$(document).on( "change", "#foodtype_name", function( e) {
  e.preventDefault();
  var foodtype_id = $(this).find(":selected").val();
  fooditem_set(foodtype_id);

});




$(document).on( "click", "#img-save_btn", function( e) {
  e.preventDefault();
   var formData = new FormData();
   formData.append("imagefile", $("input[name=imagefile]")[0].files[0]);
   image_upload('/fooditem/imageupload',formData,function(data){
          var imgval  = '/image/'+data.filename;
          $('#fooditem_image').val(imgval);
          $('#fooditem_image_div').html('<img src="'+imgval+'"  height="60", width="90" ></img>');
    });
});

//======================

var form_html  = function(url,target_div,sdata,formdata,callback){
	$.ajax({
		type: "get",
		url: url,
		contentType: "application/json; charset=utf-8",
		data: sdata,
		dataType: "html",
		success: function(data){

			target_div.html(data);
			callback(formdata);
		},
		failure: function(errMsg) {
			alert(errMsg);
			return "";
		}
	});
}

var form_data  = function(url,sdata,callback){
	$.ajax({
		type: "get",
		url: url,
		contentType: "application/json; charset=utf-8",
		data: sdata,
		dataType: "json",
		success: function(data){
			callback(data);
		},
		failure: function(errMsg) {
			alert(errMsg);
			return "";
		}
	});
}

var image_upload  = function(url,sdata,callback){
	$.ajax({
		type: "post",
		url: url,
		enctype: 'multipart/form-data',
                    	processData: false, // Don't process the files
                    	contentType: false, // Set content type to false as jQuery will tell the
		data: sdata,
		dataType: "json",
		success: function(data){
			callback(data);
		},
		failure: function(errMsg) {
			alert(errMsg);
			return "";
		}
	});
}

var table_view  = function(list,target_div,cols,callback){
	var table = target_div.DataTable(
		{
			paging: true,
			scrollY: 280,
			data:list.data,
			"columns": cols
		}
	);
	callback();
}

var table_data  = function(url,callback){

	$.ajax({
		type: "GET",
		url: url,
		contentType: "application/json; charset=utf-8",
		dataType: "json",
		success: function(data){
			callback(data);
		},
		failure: function(errMsg) {
			alert(errMsg);
		}
	});
}

var ajax_save  = function(url,type,sdata,callback){
	$.ajax({type: type,
		url: url,
		contentType: "application/json; charset=utf-8",
		data: JSON.stringify(sdata),
		dataType: "json",
		success: function(data){
			callback(data);
		},
		failure: function(errMsg) {
			alert(errMsg);
		}
	});
}

var ajax_image_save  = function(url,type,sdata,callback){
	$.ajax({type: type,
		url: url,
		type: 'POST',
		data: sdata,
		cache: false,
		enctype: 'multipart/form-data',
		dataType: 'json',
        processData: false, // Don't process the files
        contentType: false, // Set content type to false as jQuery will tell the server its a query string request
        success: function(data){
        	callback(data);
        },
        failure: function(errMsg) {
        	alert(errMsg);
        },
        error: function(jqXHR, textStatus, errorThrown){
                         alert(textStatus);
        }
    });
}