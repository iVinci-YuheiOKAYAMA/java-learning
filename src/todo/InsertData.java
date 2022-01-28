package todo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.NamingException;

public class InsertData {
	/**
	 * DBにデータを登録します
	 * @param new_todo 新規データ
	 * @return 論理値
	 */
	public boolean insertData(String new_todo) {
		DbConnection dbcon = new DbConnection();
		Connection con = null;
		Statement stmt = null;

		try {
			con = dbcon.getConnection();
			String sql = "INSERT INTO todo (task, delflg, createtime) VALUES (?, 0, current_timestamp)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, new_todo);
			int rowscount = pstmt.executeUpdate();
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
