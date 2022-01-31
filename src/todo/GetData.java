package todo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.naming.NamingException;

import bean.Member;

public class GetData {
	
	/**
	 * DBからデータを取得します
	 * @param list 取得したデータを格納するリスト
	 * @return 論理値
	 */
	public boolean getDbData(List<Member> list) {		
		DbConnection dbcon = new DbConnection();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = dbcon.getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, task from todo where delflg = 0 order by createtime desc");

			while (rs.next()) {
				String id = rs.getString("id");
				String task = rs.getString("task");
				list.add(new Member(id,task));
			}
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
			try { if (rs   != null) rs.close();   } catch (Exception e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (con  != null) con.close();  } catch (Exception e) { e.printStackTrace(); }
		}
		return true;
	}
}
