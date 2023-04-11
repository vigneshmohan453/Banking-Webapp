package users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/useraccountbalance")
public class useraccountbalance extends login{

	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		final double balance = userAccountBalance(username);
		session.setAttribute("balance", balance);
		res.sendRedirect("useraccountbalance.jsp");
	}

	public static double userAccountBalance(String username) {
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
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return accbal;
	}

}
