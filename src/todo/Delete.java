package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/delete")
public class Delete extends HttpServlet {
	static final boolean useConnectionPool = false;
	
	/**
	 * DBからTODOを削除しTODO一覧画面を表示します
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String id = request.getParameter("id");
		DeleteData dltdt = new DeleteData();
		
		if (dltdt.deleteData(id)) {
			response.sendRedirect("/todo");
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("WebContent/JSP/error.jsp");
			dispatch.forward(request, response);
		}
	}
}
