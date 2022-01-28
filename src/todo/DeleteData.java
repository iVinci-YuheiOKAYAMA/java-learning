package todo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class DeleteData {
	/**
	 * DBからデータを削除します
	 * @param id
	 * @return
	 */
	public boolean deleteData(String id) {
		DbConnection dbcon = new DbConnection();
		Connection con = null;
		Statement stmt = null;

		try {
			con = dbcon.getConnection();
			stmt = con.createStatement();
			int rowscount = stmt.executeUpdate("UPDATE  todo set delflg=1 where id=" + id);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (NamingException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (con  != null) con.close();  } catch (Exception e) { e.printStackTrace(); }
		}
		return true;
	}
}
