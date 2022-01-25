package todo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/add")
public class Add extends HttpServlet {
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
			writer.println("<html>");
			writer.println("<head>");
			writer.println("<link rel='stylesheet' type='text/CSS' href='WebContent/CSS/style.css'>");
			writer.println("<link href='https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC' crossorigin='anonymous'>");
			writer.println("</head>");
			writer.println("<body><p>TODO入力画面</p>");
			writer.println("<form action='/create' method='post'><label>新規TODO</label><textarea type='text' id='new_todo' name='new_todo' placeholder='新しいTODOを入力してください。'></textarea>");
			writer.println("<button type=\"button\" href='/' class=\"btn btn-danger\" onclick=\"location.href='/'\">キャンセル</button>");
			writer.println("<button id='btn' class=\"btn btn-success\">登録</button>");
			writer.println("</form>");
			writer.println("<script type='text/javascript' src='WebContent/JS/main.js'></script>");
			writer.println("</body></html>");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
