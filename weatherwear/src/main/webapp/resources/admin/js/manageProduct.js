/**
 * 상품 옵션/재고 적용
 */
 $(document).ready(function(){
 	// summernote 실행
	$('#summernote').summernote({
		height: 300
	});
 
	const optionDiv = document.querySelector("#option-div");
	
	let optionColorList = applyOption('optionColor');
	let optionSizeList = applyOption('optionSize');
		
	// 옵션 적용
	$("#apply-option").click(function(){
		optionColorList = applyOption('optionColor');
		optionSizeList = applyOption('optionSize');
		if(optionColorList == "" || optionSizeList == ""){
			return;
		}
		
		optionDiv.style.display = 'flex';
		$(".option-stock").html("");	
		
		for(var i=0; i<optionColorList.length; i++){
			for(var j=0; j<optionSizeList.length; j++){
				$(".option-stock").append("<tr><td>" + optionColorList[i] +"</td><td>" + optionSizeList[j] + "</td><td>"
							 + "<input type='number' class='custom-select form-control-border' name='stCnt' id='stCnt" 
							 + optionColorList[i] + optionSizeList[j] + "' value='0'></td></tr>");
			}
		}
	});
	
	// 재고 일괄 적용
	$("#applyStockAll").click(function(){
		var stCntList = [];
		var cnt = $("input[name='allStock']").val();
		
		for(var i=0; i<optionColorList.length; i++){
			for(var j=0; j<optionSizeList.length; j++){
				
				stCntList.push(cnt);
				$("#stCnt" + optionColorList[i] + optionSizeList[j]).val(cnt);
			}
		}
		setStock(stCntList);
	});
	
	function setProductId() {
		// 선택된 option의 value 가져오기
		let productCate =  document.querySelector("#exampleSelectBorder").value;
		let productId = 'W2';
		
		switch(productCate){
		case "11":
			productId += 'OT'; break;
		case "12":
			productId += 'TS'; break;
		case "13":
			productId += 'PT'; break;
		case "14":
			productId += 'SK'; break;
		case "15":
			productId += 'DR'; break;
		default :
			productId += 'AL'; break;
		}
		productId += $("#lastId").val();
		$("input[name='productId']").val(productId);
		$("input[name='imageBy']").val(productId);
		
		// dropzone의 imageBy 값 갱신
		dropzone.options.params.imageBy = productId;
	}
	
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
				playToast('sending', 'info');
				console.log('보내는 중');
			});
			
			// successmultiple : 다중 파일 업로드 성공한 경우
			this.on('successmultiple', function(file, responseText) {
				playToast('Success to Upload', 'success');
			});
			
			this.on('error', function(file, errorMessage) {
				playToast('Error to Upload', 'error');
				console.log(errorMessage);
			});
			
			// queuecomplete : 이벤트 성공 여부 확인 로그
			this.on('queuecomplete', function(e) {
				console.log('queuecomplete');
			});
			
			// 업로드된 파일 삭제
			this.on('removedfile', function(data) {
				console.log("data : " + data.name);
				playToast('Remove File', 'success');
			});
		}
 	});
});

// 재고 적용
function setStock(stCntList){
	$("input[name='stockList']").val(stCntList);
}

// 옵션 적용
function applyOption(name){
	var optionList = $("input[name='" + name + "']").val().replace(/\s/g, '');
	var list = [];

	if(optionList){
		optionList.split(",").forEach(function(option){
			if(list.includes(option)){
				playToast("동일한 값을 적용할 수 없습니다.", 'error');
				$("input[name='" + name + "']").val("");
				document.querySelector("input[name='" + name + "']").focus();
				list = [];
				return null;
			}
			if(option != ""){
				list.push(option);
			}
		});
	}
	
	$("input[name='" + name + "List']").val(list);
	
	return list;
}

// 재고 적용
function applyStock(optionColorList, optionSizeList){
	var stCntList = [];
	
	for(var i=0; i<optionColorList.length; i++){
		for(var j=0; j<optionSizeList.length; j++){
			console.log("cnt : " + $("#stCnt" + optionColorList[i] + optionSizeList[j]).val());
			stCntList.push($("#stCnt" + optionColorList[i] + optionSizeList[j]).val());
		}
	}
	setStock(stCntList);
}

// 판매가 확인
function checkCost(){
	let price = parseInt($("input[name='productPrimeCost']").val());
	let cost = Math.ceil((price + (price*0.1)*2.5)/100)*100;
	document.querySelector("#cost").innerHTML = cost;
}

// 적용
function submit(type){
	let colorList = applyOption('optionColor');
	let sizeList = applyOption('optionSize');
	let url_type = '';
	let productId = $("input[name='productId']").val();

	applyStock(colorList, sizeList);
	
	// 카테고리
	let productCate = document.querySelector("#exampleSelectBorder").value;
	let productName = $("input[name='productName']").val();
	let productContent = $('#summernote').summernote('code');
	let productPrimeCost = $("input[name='productPrimeCost']").val();
	let stockCntList = $("input[name='stockList']").val();
	let optionColorList = $("input[name='optionColorList']").val();
	let optionSizeList = $("input[name='optionSizeList']").val();
	
	if(type == 'register'){
		url_type = 'productRegisterProc.mdo';
	} else if(type == 'modify'){
		url_type = 'productUpdateProc.mdo';
		productId = $("input[name='productId']").val();
	}
	
	$.ajax({
		type: "post",
		url: url_type,
		dataType: "json",
		data: {
			productId: productId,
			productCate: productCate,
			productName: productName,
			productContent: productContent,
			productPrimeCost: productPrimeCost,
			colorList: optionColorList,
			sizeList: optionSizeList,
			cntList: stockCntList,
		},
		success: function(res) {
			if(res.code == -1) {
				playToast(res.message, 'warning');
				return;
			} 
			
			if(res.code == 1) {
				let successAction = "location.href='productInfo.mdo?productId='" + productId;
				playConfirm(res.message, res.data, "success", "상품 상세로 이동", "상품 관리페이지로", successAction, "location.href='productList.mdo'");
			}
		},
		error: function(request, status, error) {
			console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
		}
	})
}

// 상품 삭제
function deleteProduct(){
	Swal.fire({
	  title: "상품을 삭제하시겠습니까?",
	  icon: "warning",
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: "삭제하기",
	  cancelButtonText: "취소하기",
	  reverseButtons: true, // 버튼 순서 거꾸로
	}).then((result) => {
		if(result.isConfirmed){	// 매개변수 list 안됨
			console.log("confirm");
			let productId = $("input[name='productId']").val();
			$.ajax({
				type: "post",
				url: "productDelete.mdo",
				dataType: "json",
				data: {
					productId: productId,
				},
				success: function(res) {
					if(res.code == -1) {
						playToast(res.message, 'warning');
						return;
					} 
					
					if(res.code == 1) {
						playToast(res.message, 'success');
						location.href="productList.mdo";
					}
				},
				error: function(request, status, error) {
					console.log("code: " + request.status + "\n" + "message: " + request.responseText + "\n" + "error: " + error);
				}
			})
		} else {
			playToast("취소합니다.", 'info');
		}
	});
}

// 상품 상태 변경
function update(){
	let checkList = [];
	let selectedList = document.querySelectorAll("input[type='checkbox']:checked");
	let productSell = $("#productSell_value").val();
	
	if(selectedList.length < 1){
		playToast("변경할 데이터를 선택해주세요", 'warning');
		return;
	}
	
	for(let i=0; i<selectedList.length; i++){ 
		if(!checkList.includes(selectedList[i].value)){
			let product = {};
			
			product.productSell = productSell;
			product.productId = selectedList[i].value;
			checkList.push(product);
		}
	}
	
	$.ajax({
		url: "/w2/productUpdateStatus.mdo",
		type: "POST",
		data: JSON.stringify(checkList),
		dataType: "json",
		contentType: "application/json",
		success: function(){
			playToast("수정되었습니다.", 'success');
			window.location.reload();
		},
		error: function(){
			console.log("실패");
		}
	});
}