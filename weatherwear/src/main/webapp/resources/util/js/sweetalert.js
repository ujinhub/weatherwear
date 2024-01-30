/**
	Icon
	- success
	- error
	- warning
	- info
	- question

*/

function playAlert(message, iconType, text){
    Swal.fire({
      icon: iconType,
      title: message,
      text: text,
    });
}

function playConfirm(message, action, iconType, confirmBtn, cancleBtn, success, error){
	Swal.fire({
	  title: message,
	  text: action,
	  icon: iconType,
	  showCancelButton: true,
	  confirmButtonColor: '#3085d6',
	  cancelButtonColor: '#d33',
	  confirmButtonText: confirmBtn,
	  cancelButtonText: cancleBtn,
	  reverseButtons: true, // 버튼 순서 거꾸로
	}).then((result) => {
		if(result.isConfirmed){	// 매개변수 list 안됨
			eval(success);
		} else {
			eval(error);
		}
	});
}

function playToast(message, iconType){
    const Toast = Swal.mixin({
      toast: true,
      position: 'center-center',
      showConfirmButton: false,
      timer: 1000,
      timerProgressBar: false,
      didOpen: (toast) => {
        toast.addEventListener('mouseenter', Swal.stopTimer)
        toast.addEventListener('mouseleave', Swal.resumeTimer)
      }
    });

    Toast.fire({
      icon: iconType,
      title: message
    });
}