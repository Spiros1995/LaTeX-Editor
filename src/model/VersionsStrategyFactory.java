package model;

public class VersionsStrategyFactory {
	private VersionsStrategy strategy;
	private String strategyType;
	
	public VersionsStrategyFactory(String strategyType) {
		this.strategyType = strategyType;
	}
	public VersionsStrategy createStrategy() {
		if (strategyType.equals("stable")) {
			strategy = new StableVersionsStrategy();
		}
		else if(strategyType.equals("volatile")) {
			strategy = new VolatileVersionsStrategy();
		}
		return strategy;
	}
}
