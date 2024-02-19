/**
 * 
 */
function setProvince(){
	var province = document.getElementById("province").value;

	switch(province){
	case "seoul":	window.location.replace("main.do?province=seoul");	break;
	case "chuncheon":	window.location.replace("main.do?province=chuncheon");	break;
	case "suwon":	window.location.replace("main.do?province=suwon");	break;
	case "incheon":	window.location.replace("main.do?province=incheon");	break;
	case "daegu":	window.location.replace("main.do?province=daegu");	break;
	case "busan":	window.location.replace("main.do?province=busan");	break;
	case "jeju":	window.location.replace("main.do?province=jeju");	break;
	default:	break;
	}
}

function setProvince(province){
	switch(province){
	case "seoul":	window.location.replace("main.do?province=seoul");	break;
	case "chuncheon":	window.location.replace("main.do?province=chuncheon");	break;
	case "suwon":	window.location.replace("main.do?province=suwon");	break;
	case "incheon":	window.location.replace("main.do?province=incheon");	break;
	case "daegu":	window.location.replace("main.do?province=daegu");	break;
	case "busan":	window.location.replace("main.do?province=busan");	break;
	case "jeju":	window.location.replace("main.do?province=jeju");	break;
	default:	break;
	}
}

function selectWeather(weatherId){

	switch(weatherId){
	// 천둥번개
	case 200: case 201: case 202: case 210: case 211: 
	case 212: case 221: case 230: case 231: case 232:
		image = 'resources/client/images/weather/thunder.gif';
		break;
	// 안개비
	case 300: case 301: case 302: case 310: case 311: 
	case 312: case 313: case 314: case 321:
		image = 'resources/client/images/weather/rainy.gif';
		break;
	// 비
	case 500: case 501: case 502: case 503: case 504: 
	case 511: case 520: case 521: case 522: case 531:
		image = 'resources/client/images/weather/rainyrainy.gif';
		break;
	// 눈
	case 600: case 601: case 602: case 611: case 612: 
	case 615: case 616: case 620: case 621: case 622:
		image = 'resources/client/images/weather/snowy.gif';
		break;
	// 안개
	case 701: case 711: case 721: case 731: case 741: 
	case 751: case 761: case 762: case 771: case 781:
		image = 'resources/client/images/weather/fog.gif';
		break;
	// 구름
	case 801: case 802: case 803: case 804:
		image = 'resources/client/images/weather/wind.gif';
		break;
	// 재난
	case 900: case 901: case 902: case 903: case 904: case 905: case 906:
		image = 'resources/client/images/weather/thunderthunder.gif';
		break;
	// 맑음
	case 800: default: //맑음으로 합니다.
		image = 'resources/client/images/weather/sunny.gif';
		break;
	}
	
	var div = document.querySelector("#weather");
	
	div = "<section id='background_img' style='background-image:url(" + image + ");'>" + div.innerHTML;
	
	$("#weather").html(div);
}