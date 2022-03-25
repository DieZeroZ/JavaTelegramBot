package GoogleTables;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.services.sheets.v4.Sheets;
import com.google.api.services.sheets.v4.SheetsScopes;
import com.google.api.services.sheets.v4.model.ValueRange;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GoogleTableAPI {
	private Sheets sheetsService;
	private String APPLICATION_NAME = "Google shit";
	private String SPREADSHEET_ID;
	
	private  Credential authorize() throws IOException, GeneralSecurityException, Exception
	{
		InputStream in = GoogleTableAPI.class.getResourceAsStream("/credential.json");
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JacksonFactory.getDefaultInstance(), new InputStreamReader(in));
		
		List<String> scopes = Arrays.asList(SheetsScopes.SPREADSHEETS);
		
		 GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	                GoogleNetHttpTransport.newTrustedTransport(),JacksonFactory.getDefaultInstance() , clientSecrets, scopes)
	                .setDataStoreFactory(new FileDataStoreFactory(new java.io.File("tokens")))
	                .setAccessType("offline")
	                .build();
	        Credential credential = new AuthorizationCodeInstalledApp(flow,new LocalServerReceiver()).authorize("user");
	        return credential;	
	}
	
	public  Sheets getSheetsService() throws Exception
	{
		Credential credential = authorize();
		return new Sheets.Builder(GoogleNetHttpTransport.newTrustedTransport(), JacksonFactory.getDefaultInstance(), credential)
				.setApplicationName(APPLICATION_NAME)
				.build();
	}
	
	
	public void getCommands(String range, String TableID) throws IOException,GeneralSecurityException
	{
		//sheetsService = getSheetsService();
		//SPREADSHEET_ID = TableID;
		
		
		// body = new ValueRange().setValues(Arrays.asList(Arrays.asList("updated")));
	}
}
