package model;

public class RollbackToPreviousVersion {
	VersionsStrategy strategy;
	int rollbackNum;

	public RollbackToPreviousVersion(VersionsStrategy strategy, int rollbackNum) {
		this.strategy=strategy;
		this.rollbackNum=rollbackNum;
	}
	int j=1;
	public void RollItBackBaby() {
		int size = strategy.getEntireHistory().size()-1;
    	for(int i=rollbackNum; i<size;i++) {
    		strategy.removeDocument();
    		j++;
    	}
	}

}
