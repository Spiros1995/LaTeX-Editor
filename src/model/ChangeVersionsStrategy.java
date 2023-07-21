package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ChangeVersionsStrategy {
	
	private VersionsStrategy strategy;
	private VersionsStrategyFactory strategyFactory;
	private String strategyVersion;
	private VersionsStrategy tempStrategy;

	public ChangeVersionsStrategy(VersionsStrategy strategy, String strategyVersion) {
		this.strategy=strategy;
		this.strategyVersion=strategyVersion;
	}
	
	public VersionsStrategy Swap() {
		if(strategyVersion.equals("volatile")){
			strategyFactory = new VersionsStrategyFactory("stable");
			tempStrategy = strategyFactory.createStrategy();
			for(int i=0;i<strategy.getEntireHistory().size();i++) {
				tempStrategy.putVersion(strategy.getEntireHistory().get(i));
			}
		}else {
			strategyFactory = new VersionsStrategyFactory("volatile");
			tempStrategy = strategyFactory.createStrategy();
			for(int i=0;i<strategy.getEntireHistory().size();i++) {
				tempStrategy.putVersion(strategy.getEntireHistory().get(i));
			}
			deleteTempSaves();	
		}
		return tempStrategy;
	}
	
	public void deleteTempSaves() {
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
