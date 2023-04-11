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

@WebServlet("/userdetails")
public class userdetails extends HttpServlet {

	private static final long serialVersionUID = 1L;
	static String AccountNumber = null;
	static String Balance = null;
	static String IFSC = null;
	static String UPIid = null;
	static String password = null;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		userDetails(username);
		session.setAttribute("AccountNumber", AccountNumber);
		session.setAttribute("Balance", Balance);
		session.setAttribute("IFSC", IFSC);
		session.setAttribute("UPIid", UPIid);
		session.setAttribute("password", password);
		res.sendRedirect("userdetails.jsp");
	}

	public static String userDetails(String username) {
		String qry = "select AccountNumber,Balance,IFSC,UPIid,password from users where username=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DB.DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, username);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				AccountNumber = rs.getString(1);
				Balance = rs.getString(2);
				IFSC = rs.getString(3);
				UPIid = rs.getString(4);
				password = rs.getString(5);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AccountNumber;
	}

}
