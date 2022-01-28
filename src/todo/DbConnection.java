package todo;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DbConnection {
	/**
	 * DBとの接続を取得します。
	 * @return DBとのConnection
	 * @throws NamingException
	 * @throws SQLException
	 * @throws ClassNotFoundException
	 */
	Connection getConnection() throws NamingException, SQLException, ClassNotFoundException {
		final String dataSourceName = "java:comp/env/jdbc/postgresql";

		Connection con = null;

		System.out.println("コネクションプールよりDB接続を取得します。");
		Context context = new InitialContext();
		DataSource ds = (DataSource) context.lookup(dataSourceName);
		con = ds.getConnection();

		return con;
	}
}
