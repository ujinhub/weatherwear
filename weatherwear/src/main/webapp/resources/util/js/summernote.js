/**
 * 
 */
 $(document).ready(function() {
 	var key = $('#key').val();
 
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
 });