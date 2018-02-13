package com.rex.google;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeServlet;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

/**
 * @author REX-012
 *
 */
public class AuthorizationServlet extends AbstractAuthorizationCodeServlet {

	/**
	 * 
	 */
	public AuthorizationServlet() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.api.client.extensions.servlet.auth.oauth2.
	 * AbstractAuthorizationCodeServlet#initializeFlow()
	 */
	@Override
	protected AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
		// TODO Auto-generated method stub
		return InitializeFlowTool.initializeFlow();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.api.client.extensions.servlet.auth.oauth2.
	 * AbstractAuthorizationCodeServlet#getRedirectUri(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return InitializeFlowTool.getREDIRECT_URI();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.google.api.client.extensions.servlet.auth.oauth2.
	 * AbstractAuthorizationCodeServlet#getUserId(javax.servlet.http.
	 * HttpServletRequest)
	 */
	@Override
	protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
		// TODO Auto-generated method stub
		return "test";
	}

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			System.out.println("request credentials start!!!");
			this.service(req, resp);
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
