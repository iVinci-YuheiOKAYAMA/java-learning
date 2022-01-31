package todo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Member;

@WebServlet("/todo")
public class TodoList extends HttpServlet {

	static final boolean useConnectionPool = false;

	/**
	 * "/todo"へのGETリクエストをdoPost()へ渡します
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * DBからデータを取得しTODO一覧画面を教示します
	 * @param request リクエスト
	 * @param response レスポンス
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			GetData getdata = new GetData();
			List<Member> list = new ArrayList<Member>();
			
			request.setCharacterEncoding("UTF8");
			
			if (getdata.getDbData(list)) {
				request.setAttribute("list", list);
				RequestDispatcher dispatch = request.getRequestDispatcher("/WebContent/JSP/list.jsp");
				dispatch.forward(request, response);
				return;
			} else {
				RequestDispatcher dispatch = request.getRequestDispatcher("WebContent/JSP/error.jsp");
				dispatch.forward(request, response);
			}
	}
}