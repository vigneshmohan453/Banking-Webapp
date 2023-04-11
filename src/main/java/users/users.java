package users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class users {
	String query = "select * from users where username=? and password=?";

	public boolean check(String uname, String upass) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DB.DBConnection.getConnection();
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
