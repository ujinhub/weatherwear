/**
 * 
 */
 $(document).ready(function() {
	var hiddenkey = $('#key').val();
	alert(hiddenkey);
 	var dropzoneError = '';
 	Dropzone.autoDiscover = false;
 	const dropzone = new Dropzone('div#dropzone', {
 		url: 'fileUpload.mdo',
 		method: 'post',
		autoProcessQueue: false,
		autoQueue: true,
		clickable: true,
		createImageThumbnails: true,
		thumbnailHeight: 150,
		thumbnailWidth: 150,
		paramName: 'images',
		params: { key: hiddenkey },
		parallelUploads: 10,
		uploadMultiple: true,
		acceptedFiles: '.jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF',
		dictInvalidFileType: '이미지 파일만 업로드 가능합니다.',
 		previewTemplate: document.querySelector('#myTemplate').innerHTML,
 
 		init: function() {
			
			var myDropzone = this;

			$('.insertBtn').on('click', function(e) {
				myDropzone.processQueue();
			});
			
			$('.cancelBtn').on('click', function(e) {
				myDropzone.removeAllFiles(true);
			});
			
			this.on('maxfilesexceeded', function(file) {
				alert('최대 10개까지 업로드 하실 수 있습니다.');
				myDropzone.removeFile(file);
			});

			this.on('sendingmultiple', function(file, xhr, formData) {
				alert('sending');
				console.log('보내는 중');
			});
			
			this.on('successmultiple', function(file, responseText) {
				console.log('성공');
			});
			
			this.on('error', function(file, errorMessage) {
				console.log(errorMessage);
			});
			
			this.on('queuecomplete', function(e) {
				console.log('queuecomplete');
			});
			
			this.on('removedfile', function(data) {
				// alert('a');
			});
		}
 	});
	
});