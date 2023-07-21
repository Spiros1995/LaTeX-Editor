package model;

import java.io.IOException;

public class ChangeVersionsStrategyCommand implements Command{

	private VersionsStrategy strategy;
	private ChangeVersionsStrategy changeVersions;
	private String strategyVersion;
	public ChangeVersionsStrategyCommand(VersionsStrategy strategy, String strategyVersion) {
		this.strategy=strategy;
		this.strategyVersion=strategyVersion;
		changeVersions = new ChangeVersionsStrategy(strategy,strategyVersion);
	}

	public void execute() throws IOException {
		strategy = changeVersions.Swap();
	}
	
	public VersionsStrategy getStrategy() {
		return strategy; 
	}

}
