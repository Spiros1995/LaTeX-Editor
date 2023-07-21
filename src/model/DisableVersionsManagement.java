package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class DisableVersionsManagement {
	
	VersionsStrategy strategy;

	public DisableVersionsManagement(VersionsStrategy strategy) {
		this.strategy=strategy;
	}

	public void deleteEverything() {
		//delete tempSaves folder and contents
    	Path path = Paths.get(System.getProperty("user.dir") + "/src/tempSaves");
    	File file = new File(System.getProperty("user.dir") + "/src/tempSaves");
    	if (file.exists()) {
	    	try {
				deleteDirectoryRecursion(path);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
    	}
	}
	
	public void deleteDirectoryRecursion(Path path) throws IOException {
		if (Files.isDirectory(path, LinkOption.NOFOLLOW_LINKS)) {
			try (DirectoryStream<Path> entries = Files.newDirectoryStream(path)) {
				for (Path entry : entries) {
					deleteDirectoryRecursion(entry);
				}
			}
		}
		Files.delete(path);
	}

}
