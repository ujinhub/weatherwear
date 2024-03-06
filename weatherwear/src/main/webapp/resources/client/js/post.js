/**
 * 다음 주소
 */
 
 function daumPost(){
    new daum.Postcode({
        oncomplete: function(data) {
        	console.log(data.zonecode);
        	console.log(data.userSelectedType);
        	console.log(data.roadAddress);
        	console.log(data.jibunAddress);
        	
        	let addr;
        	if(data.userSelectedType == "J"){
        		addr = data.jibunAddress;
        	}else{
        		addr = data.roadAddress;
        	}
        	document.getElementById("addressPostNum").value = data.zonecode;
        	document.getElementById("address1").value = addr;
        	document.getElementById("address2").focus();
        }
    }).open();
}