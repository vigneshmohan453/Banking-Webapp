package admin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class admin {
	private static final String url = "jdbc:mysql://localhost:3306/Bank";
	private static final String user = "root";
	private static final String pwd = "tiger";
	String query = "select * from admin where Adminname=? and password=?";

	public boolean check(String uname, String upass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pwd);
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, uname);
			pst.setString(2, upass);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
