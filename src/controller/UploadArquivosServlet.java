package controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;


@WebServlet("/upload")
public class UploadArquivosServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//Definir a pasta de upload aqui (não recomendo usar a pasta do próprio projeto)
	private static String UPLOAD_DIRECTORY = "C:\\temp";
	
    public UploadArquivosServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<String> resp = new ArrayList<String>();
		List<String> erro = new ArrayList<String>();
		List<FileItem> items; //Aqui podem vir arquivos e input texts, selects, etc.
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
			for (FileItem item : items) { //Para cada componente do html que veio por request
				if (item.isFormField()) {
					//Aqui se pegamos os campos, selects, radios, e etc para montar eventuais objetos
				} else { //Aqui pegamos os arquivos
					InputStream inputStream = null;
					OutputStream outputStream = null;
					try {
						String filename = FilenameUtils.getName(item
								.getName());
						inputStream = item.getInputStream();
						File dir = new File(UPLOAD_DIRECTORY);
						if (!dir.exists()){//Se o diretorio não existir, cria ele
							dir.mkdir();
						}
						File arquivo = new File(UPLOAD_DIRECTORY,
								filename);

						if (arquivo.exists()) { //Esse if substitui o arquivo, caso ele já exista na pasta
							arquivo.delete();
						}
						//Escrita do arquivo, o upload de fato;
						outputStream = new FileOutputStream(arquivo);
						
						int read = 0;
						byte[] bytes = new byte[1024];

						while ((read = inputStream.read(bytes)) != -1) {
							outputStream.write(bytes, 0, read);
						}
						resp.add(filename);
					} catch (Exception e){
						erro.add(e.getMessage());
					} finally {
						if (inputStream != null) {
							try {
								inputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
						if (outputStream != null) {
							try {
								outputStream.flush();
								outputStream.close();
							} catch (IOException e) {
								e.printStackTrace();
							}

						}
					}
				}
			}
		} catch (FileUploadException e) {
			erro.add(e.getMessage());
		} finally {
			String url = "/envio.jsp"; //url de retorno do servlet
			request.setAttribute("erro", erro);
			request.setAttribute("message", resp);
			getServletContext().getRequestDispatcher(url).forward(
					request, response);
		}
		
	}
}
