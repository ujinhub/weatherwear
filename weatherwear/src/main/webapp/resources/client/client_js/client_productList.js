$(document).ready(function(){
	const params = new URLSearchParams(location.search);
	let gubun = "." + params.get('gubun') + "";
	
	document.querySelector(gubun).style.color="red";
})

function orderBy(){
	var value = document.getElementById("ordertype").value;
	const params = new URLSearchParams(location.search);
	let gubun = params.get('gubun');
	
	window.location.replace("productList.do?gubun=" + gubun + "&ordertype=" + value);
}
