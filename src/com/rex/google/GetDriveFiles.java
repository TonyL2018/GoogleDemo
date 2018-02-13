package com.rex.google;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

/**
 * Servlet implementation class GetDriveFiles
 */
@WebServlet("/GetDriveFiles")
public class GetDriveFiles extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String getUserId(HttpServletRequest req) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "test";
	}
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetDriveFiles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			System.out.println("request credentials start!!!");
			Credential credential = InitializeFlowTool.getValidCredential(this.getUserId(request));

			if (credential != null) {
				System.out.println(credential.getExpiresInSeconds());
				response.setHeader("Content-type", "text/html;charset=UTF-8");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append("!!!Credentials are already obtained!!!<br/>");
				Drive service = InitializeFlowTool.initializeDrive(credential);
				FileList result = service.files().list().setPageSize(25).setFields("nextPageToken, files(id, name)")
						.execute();
				List<File> files = result.getFiles();
				if (files == null || files.size() == 0) {
					System.out.println("No files found.");
				} else {
					response.getWriter().append("Files:<br/>");
					for (File file : files) {
						// System.out.printf("%s (%s)\n", file.getName(),
						// file.getId());
						response.getWriter().append("File Name:::" + file.getName()).append("&nbsp")
								.append("File ID:::" + file.getId()).append("<br/>");
					}
				}
			} else {
				response.sendRedirect("/GoogleDemo/authorizationServlet");
			}
			System.out.println("request credentials end!!!");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
