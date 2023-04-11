package users;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/userpasswordchange")
public class userspasswordchange extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		HttpSession session = req.getSession();
		String username = (String) session.getAttribute("username");
		String pass1 = req.getParameter("pass1");
		String pass2 = req.getParameter("pass2");

		if (pass1.equals(pass2)) {
			if (changePassword(username, pass1) == 1)
				res.sendRedirect("userpasswordchangesuccess.jsp");
			else {
				res.sendRedirect("userpasswordchangefailed.jsp");
			}
		} else
			res.sendRedirect("userpasswordchangefailed.jsp");
	}

	public static int changePassword(String username, String pass1) {
		int row = 0;
		String qry = "update users set password=? where username=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DB.DBConnection.getConnection();
			PreparedStatement pst = con.prepareStatement(qry);
			pst.setString(1, pass1);
			pst.setString(2, username);
			row = pst.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return row;
	}
}
