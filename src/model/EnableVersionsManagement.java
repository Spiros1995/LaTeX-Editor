package model;

public class EnableVersionsManagement {
	private VersionsStrategyFactory strategyFactory;
	public VersionsStrategy getStrategy(String strategyVersion) {
		strategyFactory = new VersionsStrategyFactory(strategyVersion);
		return strategyFactory.createStrategy();
	}
}
