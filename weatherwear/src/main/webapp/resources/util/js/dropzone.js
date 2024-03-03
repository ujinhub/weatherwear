/**
 * 
 */
 $(document).ready(function() {
 	
 	// 서버에 전송할 파일과 관련된 추가 정보
	var hiddenkey = $('#key').val();
	var imageStatus = $('#imageStatus').val();
	var imageBy = $("#imageBy").val();
	
 	var dropzoneError = '';
 	Dropzone.autoDiscover = false;
 	const dropzone = new Dropzone('div#dropzone', {
 		url: 'fileUpload.mdo',	// 파일 업로드 로직 호출
 		method: 'post',	
		autoProcessQueue: false,		/** 자동 업로드 */
		autoQueue: true,				/** 파일 업로드시 큐에 자동 업로드 */ 
		clickable: true,				/** 클릭 가능 여부 */
		createImageThumbnails: true,	/** 이미지 미리보기 */
		thumbnailHeight: 150,			/** 이미지 크기 조절 */
		thumbnailWidth: 150,			/** 이미지 크기 조절 */
		paramName: 'images',			/** 서버로 전송될 파일의 파라미터 이름 */
		params: { 						/** 파일과 전송될 추가 정보 */
			key: hiddenkey,
			imageStatus: imageStatus,
			imageBy: imageBy
		 },
		// maxFiles : 업로드하는 파일 수
		// maxFilesize: 최대 업로드용량(MB)
		// timeout: 커넥션 타임아웃 설정
		addRemoveLinkes: true,			/** 삭제 버튼 표시 여부 */
		dicRemoveFile: 'X',				/** 삭제 버튼 텍스트 */
		parallelUploads: 10,			/** 동시 업로드가능한 파일 수 */
		uploadMultiple: true,			/** 다중 업로드 기능 */
		acceptedFiles: '.jpeg,.jpg,.png,.gif,.JPEG,.JPG,.PNG,.GIF',
		dictInvalidFileType: '이미지 파일만 업로드 가능합니다.',
 		previewTemplate: document.querySelector('#dropzone-preview').innerHTML,
 
 		/** 초기 설정 */
 		init: function() {
			var myDropzone = this;
			
			// 업로드 대기중인 파일 처리
			$('.insertBtn').on('click', function(e) {
				myDropzone.processQueue();	
			});
			
			// 업로드 대기중/업로드된 파일 모두 삭제
			$('.cancelBtn').on('click', function(e) {
				playToast('Delete Image', 'info');
				myDropzone.removeAllFiles(true);	
			});
			
			// maxfilesexceeded : 업로드 개수를 초과하는 경우
			this.on('maxfilesexceeded', function(file) {
				playToast('최대 10개까지 업로드 하실 수 있습니다.', 'info');
				myDropzone.removeFile(file);
			});

			// sendingmultiple : 여러 파일을 동시에 업로드시, 첫 전송 직전에 발생
			this.on('sendingmultiple', function(file, xhr, formData) {
			});
			
			// successmultiple : 다중 파일 업로드 성공한 경우
			this.on('successmultiple', function(file, responseText) {
			});
			
			this.on('error', function(file, errorMessage) {
				playToast('Error to Upload', 'error');
				console.log(errorMessage);
			});
			
			// queuecomplete : 이벤트 성공 여부 확인 로그
			this.on('queuecomplete', function(e) {
			});
			
			// 업로드된 파일 삭제
			this.on('removedfile', function(data) {
			});
		}
 	});
});