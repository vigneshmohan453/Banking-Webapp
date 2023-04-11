package users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userwithdraw")
public class userwithdraw extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String balance = req.getParameter("amount");
		double amount = Double.parseDouble(balance);
		
		if(userWithdraw(username,amount)==1)
			res.sendRedirect("userwithdrawsuccess.jsp");
		else
			res.sendRedirect("userwithdrawfailed.jsp");
	}

	public static int userWithdraw(String username, double amount) {
		int row=0;
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
			if (accbal >= amount && amount > 0) {
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
}
