package todo;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/create")
public class Create extends HttpServlet {
	
	static final boolean useConnectionPool = false;
	
	/**
	 * TODO入力画面から入力値を取得し、DBに登録した後TODO一覧画面を表示します
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		request.setCharacterEncoding("UTF-8");
		String new_todo = request.getParameter("new_todo");
		boolean is_validated = validate.is_validated(new_todo);
		if (!is_validated) {
			request.setAttribute("error_msg", "※1～60文字で入力してください。");
			request.setAttribute("value", new_todo);
			RequestDispatcher dispatch = request.getRequestDispatcher("WebContent/JSP/add.jsp");
			dispatch.forward(request, response);
			return;
		}
		InsertData istdt = new InsertData();
		boolean insert_data =istdt.insertData(new_todo);
		if (insert_data) {				
			response.sendRedirect("/todo");
		} else {
			RequestDispatcher dispatch = request.getRequestDispatcher("WebContent/JSP/error.jsp");
			dispatch.forward(request, response);
		}
	}
}
