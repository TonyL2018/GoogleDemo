package com.rex.google;


import java.io.IOException;
import java.util.Arrays;

import com.google.api.client.http.FileContent;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

public class FileUpload {
	
	/*java.io.File filePath = new java.io.File("files/report.csv");
	FileContent mediaContent = new FileContent("text/csv", filePath);
	File file = driveService.files().create(fileMetadata, mediaContent)
	    .setFields("id")
	    .execute();
	System.out.println("File ID: " + file.getId());*/
	
	private static File insertFile(Drive service, String title, String description,
		      String parentId, String mimeType, String filename) {
		    // File's metadata.
		    /*File body = new File();
		    body.setTitle(title);
		    body.setDescription(description);
		    body.setMimeType(mimeType);

		    // Set the parent folder.
		    if (parentId != null && parentId.length() > 0) {
		      body.setParents(
		          Arrays.asList(new ParentReference().setId(parentId)));
		    }

		    // File's content.
		    java.io.File fileContent = new java.io.File(filename);
		    FileContent mediaContent = new FileContent(mimeType, fileContent);
		    try {
		      File file = service.files().insert(body, mediaContent).execute();

		      // Uncomment the following line to print the File ID.
		      // System.out.println("File ID: " + file.getId());

		      return file;
		    } catch (IOException e) {
		      System.out.println("An error occurred: " + e);
		      return null;
		    }*/
			return null;
		  }

}
