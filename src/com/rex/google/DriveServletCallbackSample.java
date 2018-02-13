package com.rex.google;

import java.io.IOException;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.servlet.auth.oauth2.AbstractAuthorizationCodeCallbackServlet;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Servlet implementation class DriveServletCallbackSample
 */
@WebServlet("/DriveServletCallbackSample")
public class DriveServletCallbackSample extends AbstractAuthorizationCodeCallbackServlet {

	  @Override
	  protected void onSuccess(HttpServletRequest req, HttpServletResponse resp, Credential credential)
	      throws ServletException, IOException {
	    resp.sendRedirect("/");
	  }

	  @Override
	  protected void onError(
	      HttpServletRequest req, HttpServletResponse resp, AuthorizationCodeResponseUrl errorResponse)
	      throws ServletException, IOException {
	    // handle error
	  }

	  @Override
	  protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
	    GenericUrl url = new GenericUrl(req.getRequestURL().toString());
	    url.setRawPath("/oauth2callback");
	    return url.build();
	  }

	  @Override
	  protected AuthorizationCodeFlow initializeFlow() throws IOException {
	    /*return new GoogleAuthorizationCodeFlow.Builder(
	        new NetHttpTransport(), JacksonFactory.getDefaultInstance()
	        "[[ENTER YOUR CLIENT ID]]", "[[ENTER YOUR CLIENT SECRET]]",
	        Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
	        DATA_STORE_FACTORY).setAccessType("offline").build();*/
		  return null;
	  }

	  @Override
	  protected String getUserId(HttpServletRequest req) throws ServletException, IOException {
	    // return user ID
		  return null;
	  }

}
