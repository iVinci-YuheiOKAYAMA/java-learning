package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {
	static final boolean useConnectionPool = false;

	/**
	 * "/add"へのGETリクエストをdoPost()へ渡します
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * add.jspを呼び出しTODO入力画面を表示します
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF8");

		RequestDispatcher dispatch = request.getRequestDispatcher("WebContent/JSP/add.jsp");
		dispatch.forward(request, response);
	}
}
