package view;

import controller.Controller;

public class StartView {
	
	public static void main(String[] args) {
		Controller cont = new Controller();
//		cont.showAllCustomer(); // 모든 customer table 출력
//		cont.showCustomer("NOA"); // customer table에 NOA라는 고객명을 가진 값 출력
//		cont.showCustomer(101); // customer table에 101이라는 고객번호를 가진 값 출력
//		
//		cont.logIn("N"); // N이라는 고객명을 가진 값에 로그인
//		cont.logIn("PEA"); // PEA라는 고객명을 가진 값에 로그인
//		cont.signIn("COM", 800); // COM이라는 고객명, 800의 적립금을 가진 고객 회원가입
//		
//		cont.logIn("BEE");
//		cont.logIn("KIN");
		cont.updateMoney(110, 300);
		cont.showAllCustomer();

	}
}