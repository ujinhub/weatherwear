/**
 * 엑셀 저장
 */
const excelDownload = document.querySelector('#excelDownload');
let today = new Date();
let date = '';
date += today.getFullYear()
date += (today.getMonth()+1) 
date += today.getDate()

function exportExcel(save){
	// workbook 생성
	let wb = XLSX.utils.book_new();	

	// 시트 생성
	let newWorksheet = excelHandler.getWorksheet();
	
	// workbook에 워크시트 붙이기
	XLSX.utils.book_append_sheet(wb, newWorksheet, excelHandler.getSheetName());
	
	// 엑셀 파일 생성
	let wbout = XLSX.write(wb, {bookType:'xlsx', type:'binary'});
	
	// 엑셀 파일 내보내기
	saveAs(new Blob([s2ab(wbout)], {type:'application/octet-stream'}), excelHandler.getExcelFileName(save));
}

let excelHandler = {
		getExcelFileName : function(save){
			return date + "_" + save + ".xlsx";
		},
		
		getSheetName : function(){
			return date;
		},
		
		getExcelData : function(){
			return document.getElementById('tableData');
		},
		
		getWorksheet : function(){
			return XLSX.utils.table_to_sheet(this.getExcelData());
		}
}

function s2ab(s){
	// convert s to arrayBuffer
	let buf = new ArrayBuffer(s.length);
	
	// create uint8array as viewer
	let view = new Uint8Array(buf);
	
	// convert to octet
	for(let i=0; i<s.length; i++){
		view[i] = s.charCodeAt(i) & 0xFF;
	}
	return buf;
}