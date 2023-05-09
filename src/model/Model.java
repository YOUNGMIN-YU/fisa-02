package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.domain.Customer;
import model.domain.Membership;
import util.MyUtil;

public class Model {
	public ArrayList<Customer> getAllCustomer() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		ArrayList<Customer> al = null;
		
		try {
			conn = MyUtil.getConnection();
			pstmt = conn.prepareStatement("SELECT * FROM customer");
			rs = pstmt.executeQuery();
			
			al = new ArrayList<Customer>();
			
			while (rs.next()) {
				
				String str = rs.getString("cname");
					
				al.add(Customer.builder()
						.cno(rs.getInt("cno"))
						.cname(MyUtil.strMapping(rs.getString("cname")))
						.cmoney(rs.getInt("cmoney"))
						.mno((Integer)rs.getInt("mno")).build());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return al;
	}
	
	public Customer getCustomer(Object o) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		Customer c = null;
		
		try {
			conn = MyUtil.getConnection();	
			
			if (o instanceof String) {
				pstmt = conn.prepareStatement("SELECT * FROM customer WHERE cname = ?");
				pstmt.setString(1, (String)o);
				
			}else if(o instanceof Integer){
				pstmt = conn.prepareStatement("SELECT * FROM customer WHERE cno = ?");
				pstmt.setInt(1, (int)o);
			}else {
				return null;
			}
			
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				c = Customer.builder()
						.cno(rs.getInt("cno"))
						.cname(MyUtil.strMapping(rs.getString("cname")))
						.cmoney(rs.getInt("cmoney"))
						.mno((Integer)rs.getObject("mno")).build();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return c;
	}
	public Membership getMembership(Integer mno) {
		Membership mem = null;
		if (mno == null) {
			mem = new Membership(0, MyUtil.strMapping("empty"));
			return mem;
		}
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = MyUtil.getConnection();	
			
			pstmt = conn.prepareStatement("SELECT * FROM membership WHERE mno=?");
			pstmt.setInt(1, mno);
			
			rs = MyUtil.executeQuery(pstmt);
			
			mem = Membership.builder()
					.mno(rs.getInt("mno"))
					.mname(MyUtil.strMapping(rs.getString("mname"))).build();					
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return mem;
	}
	
	public boolean insertCustomer(int cno, String cname, Integer cmoney, Integer mno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = MyUtil.getConnection();	
			
			pstmt = conn.prepareStatement("INSERT INTO customer VALUES(?,?,?,?)");
			pstmt.setInt(1, cno);
			pstmt.setString(2, cname);
			pstmt.setObject(3, cmoney);
			pstmt.setObject(4, mno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean updateCustomerMoney(int cno, Integer cmoney) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = MyUtil.getConnection();	
			
			pstmt = conn.prepareStatement("UPDATE customer SET cmoney=? WHERE cno=?");			
			pstmt.setObject(1, cmoney);
			pstmt.setInt(2, cno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	public boolean updateMembership(int mno, String mname) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = MyUtil.getConnection();	
			
			pstmt = conn.prepareStatement("UPDATE membership SET mname=? WHERE mno=?");			
			pstmt.setObject(1, mname);
			pstmt.setInt(2, mno);
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
}
