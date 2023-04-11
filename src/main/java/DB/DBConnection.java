package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String url = "jdbc:mysql://localhost:3306/Bank";
	private static final String user = "root";
	private static final String pwd = "tiger";

	public static Connection getConnection() throws SQLException {

		Connection con = DriverManager.getConnection(url, user, pwd);
		return con;
	}
}
