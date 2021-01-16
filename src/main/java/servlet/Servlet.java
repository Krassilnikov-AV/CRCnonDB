package servlet;

import org.apache.poi.xwpf.usermodel.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
import java.io.*;
import java.nio.file.*;

/**
 * Класс Servlet
 */
@MultipartConfig
@WebServlet("/servlet")
public class Servlet extends HttpServlet {
//	private static final String SERVER_PATH = "D:\\REPOSITORIES-2";   // при отсутствии выбора пути сервера

	String pathSave = "";
	String name = "";

	static boolean newdoc;


	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");       // распознавание русского текста

//		Part part=request.getPart("file");
//
//		pathSave = request.getParameter("path");
//		name = part.getSubmittedFileName();          // получить в классе чтения, создать в свойствах->читать и
//		// получать в необходимом классе для чтения
//		download(part.getInputStream(), name);


		if (request.getParameter("new_doc") != null) {
			try {
				XWPFDocument document = new XWPFDocument();
				try (FileOutputStream out = new FileOutputStream(new File("D:\\REPOSITORIES-2\\po.docx"))) {

					XWPFParagraph paragraph = document.createParagraph();
					XWPFRun run = paragraph.createRun();
					run.setText("Hello! My Word!");
					document.write(out);
				}
			} catch(Exception e) {
				System.out.println("Документ не создан");
				System.out.println(e);
			}
		}
		request.getRequestDispatcher("/index.html").forward(request, response);  // позволяет не выкидывать новую
		// страницу
	}



	private void download(InputStream fileStream, String name) {

		try (BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(
			Files.newOutputStream(Paths.get(pathSave + File.separator + name))
		)) {
			int read;
			byte[] readByte = new byte[1024];
			while ((read = fileStream.read(readByte)) != -1) {
				bufferedOutputStream.write(readByte, 0, read);
			}

			bufferedOutputStream.flush();     // загрузка на диск
		} catch (IOException e) {
			System.out.println("the file is corrupted!!!");
			e.printStackTrace();
		}
	}


//	ConnectionApp conApp = new ConnectionApp();
//	SQLQueryDate sqlQuery = new SQLQueryDate();
	//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		boolean delete = req.getParameter("delete") !=null;
//
//		if(delete) {
////			conApp.getNameURL();
//			try {
//				sqlQuery.deletedDataSQL();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			} catch (ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		req.getRequestDispatcher("/index.html").forward(req, resp);  // позволяет не выкидывать новую
//		// страницу
//	}
}