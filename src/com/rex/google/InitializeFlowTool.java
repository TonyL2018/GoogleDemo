package com.rex.google;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;

public class InitializeFlowTool {
	
	/** Application name. */
    private static final String APPLICATION_NAME =
        "Drive API Java Test";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
        System.getProperty("user.home"), ".credentials/drive-java-test");

    /** Global instance of the {@link FileDataStoreFactory}. */
    private static FileDataStoreFactory DATA_STORE_FACTORY;

    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY =
        JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    /** Global instance of the scopes required by this quickstart.
     *
     * If modifying these scopes, delete your previously saved credentials
     * at ~/.credentials/drive-java-quickstart
     */
    private static final List<String> SCOPES =
        Arrays.asList(DriveScopes.DRIVE);

    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
            DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }
    }
    
    private final static String REDIRECT_URI = "http://localhost:8080/GoogleDemo/oauth2callback";
	
	public static String getREDIRECT_URI() {
		return REDIRECT_URI;
	}
	
	private static GoogleAuthorizationCodeFlow flow;

	public static AuthorizationCodeFlow initializeFlow() throws ServletException, IOException {
		// TODO Auto-generated method stub
		InputStream in = InitializeFlowTool.class.getResourceAsStream("/client_secret.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));
		if(flow == null)
			flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
					clientSecrets, SCOPES).setDataStoreFactory(DATA_STORE_FACTORY)
							.setClientId("628772265348-e3gdqhsn9u1ta8ajgg63g1sdm4ilv86u.apps.googleusercontent.com")
							.setAccessType("offline").build();
		return flow;
	}

	public static Drive initializeDrive(Credential credential) {
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}
	
	public static Credential getValidCredential(String userId) {
		try {
			Credential credential = initializeFlow().loadCredential(userId);
			if (credential != null && credential.getAccessToken() != null)
				/*if (credential.getExpiresInSeconds() > 0)
					return credential;
				else*/
				if (credential.getExpiresInSeconds() < 3000)
					if (credential.refreshToken())
						return credential;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
