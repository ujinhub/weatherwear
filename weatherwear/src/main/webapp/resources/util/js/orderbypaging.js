/**
 * 
 */
 
 
$(document).on('click', '.orderbyBtn', function(e) {
	e.preventDefault();
	var url = window.location.pathname;
	url = url.replace('/w2/', '');
	url += "?searchType=" + $('#searchType').val();
	url += "&keyword=" + $('#keyword').val();
	url += "&listSize=" + $('#listSize').val();
	url += "&orderby=" + this.id;
	
	location.href = url;
});
 
 $(document).on('click', '#btnSearch', function(e) {
	e.preventDefault();
	var url = window.location.pathname;
	var orderby = document.querySelector("button.active").id;
	
	url = url.replace('/w2/', '');
	url += "?searchType=" + $('#searchType').val();
	url += "&keyword=" + $('#keyword').val();
	url += "&listSize=" + $('#listSize').val();
	url += "&orderby=" + orderby;
	
	location.href = url;
 });
 
 // 페이지 번호
function fn_pagination(page, range, rangeSize, listSize, searchType, keyword, orderby) {
	var url = window.location.pathname;
	url = url.replace('/w2/', '');
	url += "?page=" + page;
	url += "&range=" + range;
	url += "&listSize=" + listSize;
	url += "&searchType=" + searchType;
	url += "&keyword=" + keyword;
	url += "&orderby=" + orderby;
	
	location.href = url;
}

// 이전 페이지 범위
function fn_prev(page, range, rangeSize, listSize, searchType, keyword, orderby) {
	var page = ((range - 2) * rangeSize) + 1;
	var range = range - 1;
	
	var url = window.location.pathname;
	url = url.replace('/w2/', '');
	url += "?page=" + page;
	url += "&range=" + range;
	url += "&listSize=" + listSize;
	url += "&searchType=" + searchType;
	url += "&keyword=" + keyword;
	url += "&orderby=" + orderby;
	
	location.href = url;
}

// 다음 페이지 범위
function fn_next(page, range, rangeSize, listSize, searchType, keyword, orderby) {
	var page = parseInt(range * rangeSize) + 1;
	var range = parseInt(range) + 1;
	
	var url = window.location.pathname;
	url = url.replace('/w2/', '');
	url += "?page=" + page;
	url += "&range=" + range;
	url += "&listSize=" + listSize;
	url += "&searchType=" + searchType;
	url += "&keyword=" + keyword;
	url += "&orderby=" + orderby;
	
	location.href = url;
}

function page(pageId) {
	var startPage = pageId;
	var listSize = $('#listSize option:selected').val();
	var orderby = document.querySelector("button.active").id;
	
	var url = window.location.pathname;
	url = url.replace('/w2/', '');
	url += "?startPage=" + startPage;
	url += "&listSize=" + listSize;
	url += "&orderby=" + orderby;
	
	location.href = url;
}