package todo;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class list extends HttpServlet {

	static final boolean useConnectionPool = false;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		// 常にPOSTへ渡します。
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter writer;
			writer = response.getWriter();
			writer.println("<html><body>");
			writer.println("<pre>" + getDbData() + "</pre>");
			writer.println("<br><a href=\"/list\">更新</a>");
			writer.println("</body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String getDbData() {
		StringBuilder sb = new StringBuilder();
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;

		try {
			con = getConnection();
			stmt = con.createStatement();
			rs = stmt.executeQuery("select id, task from todo");

			while (rs.next()) {
				sb.append(rs.getString("id"));
				sb.append(" ; ");
				sb.append(rs.getString("task"));
				sb.append("\n");
			}
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
			try { if (rs   != null) rs.close();   } catch (Exception e) { e.printStackTrace(); }
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
