/**
 * 
 */
 $(document).ready(function() {
 	var key = $('#key').val();
 	let list = [];
 	
 	// 이미지 테이블 등록
 	$("#checkBtn").click(function(){
 		let imageStatus = $("#imageStatus").val();
 		let imageBy = $("#imageBy").val();
 		imageInsert(key, list, imageBy, imageStatus);
 	});
 	
 	$('#summernote').summernote({
 		height: 500,
 		minHeight: null,
 		maxHeight: null,
 		focus: false,
 		lang: 'ko-KR',
 		toolbar: [
 					['fontname', ['fontname']],
 					['fontsize', ['fontsize']],
 					['style', ['bold', 'italic', 'underline', 'strikethrough', 'clear']],
 					['color', ['forecolor', 'color']],
 					['table', ['table']],
 					['para', ['ul', 'ol', 'paragraph']],
 					['height', ['height']],
 					['insert', ['link', 'picture']],
 					['view', ['help']]
 				],
 		fontNames: ['Arial', 'Arial Black', 'Comic Sans MS', 'Courier New', '맑은 고딕', '궁서', '굴림체', '굴림', '돋움', '돋움체', '바탕체'],
 		fontSize: ['8', '9', '10', '11', '12', '14', '16', '18', '20', '22', '24', '28', '30', '36', '50', '72'],
 		callbacks: {
 			// 이미지 업로드
 			onImageUpload: function(files, editor) {
 				for(var i = files.length - 1; i >= 0; i--) {
 					sendFile(files[i], this);
 				}
 			},
 			
 			// 이미지 삭제
 			onMediaDelete: function(target) {
 				deleteFile(target[0].src);
 			}
 		},
 	});
 	
 	function sendFile(file, el) {
 		var formData = new FormData();
 		formData.append('file', file);
 		formData.append('key', key);
 		$.ajax({
 			url: "summernoteFileUpload.mdo",
 			type: "post",
 			data: formData,
 			cache: false,
 			contentType: false,
 			enctype: "multipart/form-data",
 			processData: false,
 			success: function(imagePath) {
 				$(el).summernote('editor.insertImage', imagePath);
 				list.push(imagePath);
 			}
 		});
 	}
 	
 	function deleteFile(fileSrc) {
 		var formData = new FormData();
 		formData.append('file', file);
		$.ajax({
 			url: "summernoteFileDelete.mdo",
 			type: "post",
 			data: formData,
 			cache: false,
 			contentType: false,
 			enctype: 'multipart/form-data',
 			processData: false,
 		});
 	}
 	
 	var reset = function() {
 		$('#summernote').reset();
 	}
 	
 	function imageInsert(key, list, imageBy, imageStatus){
 		let imList = [];
 		list.forEach(function(imagePath){
	 		let image = {};
	 		
	 		if(imageStatus == null || imageStatus == ''){
	 			switch(key){
	 			case 'product': image.imageStatus = '상세'; break;
	 			case 'notice': image.imageStatus = '공지'; break;
	 			case 'main': image.imageStatus = '메인'; break;
	 			case 'review': image.imageStatus = '리뷰'; break;
	 			case 'qna': image.imageStatus = '문의'; break;
	 			case 'refund': image.imageStatus = '환불'; break;
	 			default: image.imageStatus = '기타'; break;
	 			}
	 		}else {
	 			image.imageStatus = imageStatus;
	 		}
	 		image.imageBy = imageBy;
	 		image.imageDir = imagePath.split("_image/")[0]+"_image/";
	 		image.imageName = imagePath.split("_image/")[1];
	 		image.imageId = (imagePath.split("_image/")[1]).split(".")[0];
 			imList.push(image);
 		});
 		
		$.ajax({
			url: "/w2/imageInsert.mdo?key=" + key,
			type: "POST",
			async: true,
			data: JSON.stringify(imList),
			contentType: "application/json",
			success: function(res){
			},
			error : function(error){
			}
		});
 	}
 });