package model;

import java.io.IOException;

public class EnableVersionsManagementCommand implements Command{
	private VersionsStrategy strategy;
	private String strategyType;
	private EnableVersionsManagement enabler = new EnableVersionsManagement();
	public EnableVersionsManagementCommand(String strategyType) {
		this.strategyType=strategyType;
	}
	
	public void execute() throws IOException {
		strategy = enabler.getStrategy(strategyType);
	}
	
	public VersionsStrategy getStrategy() {
		return strategy; 
	}

}
