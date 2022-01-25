package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/create")
public class create extends HttpServlet {
	
	static final boolean useConnectionPool = false;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String new_todo = request.getParameter("new_todo");
		insertData(new_todo);
		response.sendRedirect("/");
	}

	private String insertData(String new_todo) {
		StringBuilder sb = new StringBuilder();
		Connection con = null;
		Statement stmt = null;
//		Integer rs = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			int rowscount = stmt.executeUpdate("INSERT INTO todo (task, delflg, createtime) VALUES ('" + new_todo + "', 0, current_timestamp)");
		} catch (SQLException e) {
			e.printStackTrace();
			return "エラー発生(SQLException)";
		} catch (NamingException e) {
			e.printStackTrace();
			return "エラー発生(NamingException)";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "エラー発生(ClassNotFoundException)";
		} finally {
//			try { if (rs   != null) rs.close();   } catch (Exception e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (con  != null) con.close();  } catch (Exception e) { e.printStackTrace(); }
		}
		return sb.toString();
	}

	
	/**
	 * DBとの接続を取得します。
	 * @return DBとのConnection
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Connection getConnection() throws NamingException, SQLException, ClassNotFoundException {
		final String dataSourceName = "java:comp/env/jdbc/postgresql";

		final String dbUrl = "jdbc:postgresql://localhost:5432/postgres";
		final String dbUser = "postgres";
		final String dbPass = "postgres";

		Connection con = null;

		if (useConnectionPool) {
			System.out.println("コネクションプールよりDB接続を取得します。");
			Context context = new InitialContext();
			DataSource ds = (DataSource) context.lookup(dataSourceName);
			con = ds.getConnection();
		} else {
			System.out.println("DBへ直接接続を行います。");
			Class.forName("org.postgresql.Driver");
			con = DriverManager.getConnection(dbUrl, dbUser, dbPass);
		}

		return con;
	}
}
