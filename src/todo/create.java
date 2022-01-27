package todo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

@WebServlet("/create")
public class create extends HttpServlet {
	
	static final boolean useConnectionPool = false;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String new_todo = request.getParameter("new_todo");
		
		boolean is_validated = validate.is_validated(new_todo);
		if (is_validated) {
			boolean insert_data =insertData(new_todo);
			if (insert_data) {				
				response.sendRedirect("/todo");
			} else {
				RequestDispatcher dispatch = request.getRequestDispatcher("WebContent/JSP/error.jsp");
				dispatch.forward(request, response);
			}
		} else {
			request.setAttribute("error_msg", "※0～60文字で入力してください。");
			RequestDispatcher dispatch = request.getRequestDispatcher("WebContent/JSP/add.jsp");
			dispatch.forward(request, response);
		}
	}

	private boolean insertData(String new_todo) {
		StringBuilder sb = new StringBuilder();
		Connection con = null;
		Statement stmt = null;
//		Integer rs = null;

		try {
			con = getConnection();
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
//			try { if (rs   != null) rs.close();   } catch (Exception e) { e.printStackTrace(); }
			try { if (stmt != null) stmt.close(); } catch (Exception e) { e.printStackTrace(); }
			try { if (con  != null) con.close();  } catch (Exception e) { e.printStackTrace(); }
		}
		return true;
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
