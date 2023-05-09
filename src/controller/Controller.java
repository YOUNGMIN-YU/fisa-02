package controller;

import java.util.ArrayList;

import model.Model;
import model.domain.Customer;
import model.domain.Membership;
import util.MyUtil;
import view.EndVeiw;

public class Controller {
	private Model database = new Model();
	private EndVeiw eV = new EndVeiw();
	
	public void showAllCustomer() {
		ArrayList<Customer> al = database.getAllCustomer();
		for(Customer c : al) {
			eV.showPrinter(c.getRecord());
		}
		eV.showPrinter("-------");
	}
	public void showCustomer(Object cName) {
		Customer c = database.getCustomer(cName);
		eV.showPrinter(c.getRecord());
		eV.showPrinter("-------");
	}
	
	public void logIn(String cName) {
		Customer c = database.getCustomer(cName);
		
		if (c == null) {
			eV.showPrinter("로그인에 실패 하였습니다.");
			return;
		}
		Membership m = database.getMembership((Integer)c.getMno());
		
		eV.showPrinter(MyUtil.strMapping(c.getCname()) + "님이 로그인 하셨습니다.");
		eV.showPrinter(" - 잔액은 "
				+ c.getCmoney()+ "원 이며, 등급은 "
				+ MyUtil.strMapping(m.getMname())
				+ "입니다.");
		
	}
	public void signIn(String cname, Integer cmoney) {
		ArrayList<Customer> al = database.getAllCustomer();
		
		this.signIn(al.get(al.size() - 1).getCno() + 1, cname, cmoney);
	}
	public void signIn(int cno, String cname, Integer cmoney) {
		if (database.getCustomer((Integer)cno) != null) {
			eV.showPrinter("이미 등록된 회원입니다.");
			return;
		}
		Integer mno = null;
		if (cmoney == null) {
			mno = null;
		}
		else if (cmoney < 500) {
			//silver
			mno = 10;
		} else if (cmoney >= 500) {
			mno = 20;
		} else {
			mno = null;
		}
		boolean isSuccess = database.insertCustomer(cno, cname, cmoney, mno);
		
		if(isSuccess) {
			eV.showPrinter(cname + "님이 회원가입에 성공하셨습니다.");
		} else {
			eV.showPrinter(cname + "님이 회원가입에 거부되었습니다.");
		}
	}
	public void updateMoney(int cno, Integer cmoney) {
		if (database.getCustomer((Integer)cno) == null) {
			eV.showPrinter("수정할 수 없는 고객 번호 입니다.");
			return;
		}
		boolean isSuccess = database.updateCustomerMoney(cno, cmoney);
		
		if(isSuccess) {
			eV.showPrinter("고객번호 "+ cno + "의 적립금이 "
							+ cmoney +"원으로 업데이트 성공하셨습니다.");
		}
		
		Integer mno = null;
		String mname = null;
		
		if (cmoney == null) {
			mno = null;
		} else if (cmoney < 500) {
			mno = 10;
			mname = "sliver";
		} else if (cmoney >= 500) {
			mno = 20;
			mname = "gold";
		} else {
			mno = null;
		}
		Customer c = database.getCustomer(cno);
		boolean m = database.updateMembership((Integer)c.getMno(), mname);
		
		if(m) {
			eV.showPrinter(" - 잔액은 "
					+ c.getCmoney()+ "원 이며, 등급은 "
					+ MyUtil.strMapping(mname)
					+ "입니다.");
		}
	}
}







