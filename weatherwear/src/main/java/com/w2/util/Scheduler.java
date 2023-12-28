package com.w2.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.w2.client.cart.ClientCartDAO;
import com.w2.client.cart.ClientCartVO;

@Component
public class Scheduler {
	
	@Autowired
	private ClientCartDAO cartdao;
	
	@Scheduled
	public void autoDelete() throws ParseException {
		System.out.println("비회원 장바구니 자동 삭제 시작합니다.");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar now = Calendar.getInstance();

		Date today = formatter.parse(formatter.format(now.getTime()));

		List<ClientCartVO> cartList = cartdao.getNonClientCart();

		for(int i = 0; i < cartList.size(); i++) {
			Date chkDate = cartList.get(i).getCkLimit();
			if(today.after(chkDate)) {
				int result = cartdao.deleteClientCart(cartList.get(i));
				if(result == 1) {
					System.out.println("[장바구니 자동 삭제 성공]");
					System.out.println(cartList.get(i).getCaId() + " - " + cartList.get(i).getCkId() + " - " + cartList.get(i).getCkLimit());
				}
			}
		}	

		System.out.println("비회원 장바구니 자동 삭제 종료되었습니다.");
	}
}
