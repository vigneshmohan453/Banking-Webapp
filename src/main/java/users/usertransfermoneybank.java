package users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/usertransfermoneybank")
public class usertransfermoneybank extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String balance = req.getParameter("amount");
		double amount = Double.parseDouble(balance);
		String AccountNumber = req.getParameter("accnumber");
		String IFSC = req.getParameter("IFSC");

		if (userWithdrawBank(username, amount, AccountNumber, IFSC) == 1
				&& userTransferBank(AccountNumber, IFSC, amount) == 1)
			res.sendRedirect("usertransfermoneysuccess.jsp");
		else
			res.sendRedirect("usertransfermoneyfailed.jsp");
	}

	public static boolean checkbank(String AccountNumber, String IFSC) {
		final String url = "jdbc:mysql://localhost:3306/Bank";
		final String user = "root";
		final String pwd = "tiger";
		String query = "select * from users where AccountNumber=? and IFSC=?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pwd);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, AccountNumber);
			pst.setString(2, IFSC);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int userWithdrawBank(String username, double amount, String AccountNumber, String IFSC) {
		int row = 0;
		double accbal = 0.0;
		String qry = "select balance from users where username=?";
		try {
			Connection con = DB.DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				accbal = rs.getDouble(1);
			}
			if (checkbank(AccountNumber, IFSC) && accbal >= amount && amount > 0) {
				double newbal = accbal - amount;

				String qry1 = "update users set Balance=? where username=?";
				pst = con.prepareStatement(qry1);
				pst.setDouble(1, newbal);
				pst.setString(2, username);
				row = pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

	public static int userTransferBank(String AccountNumber, String IFSC, double amount) {
		int row = 0;
		double accbal = 0.0;
		String qry = "select balance from users where AccountNumber=? and IFSC=?";
		try {
			Connection con = DB.DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, AccountNumber);
			pst.setString(2, IFSC);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				accbal = rs.getDouble(1);
			}
			if (amount > 0) {
				double newbal = accbal + amount;

				String qry1 = "update users set Balance=? where AccountNumber=? and IFSC=?";
				pst = con.prepareStatement(qry1);
				pst.setDouble(1, newbal);
				pst.setString(2, AccountNumber);
				pst.setString(3, IFSC);
				row = pst.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}

}
