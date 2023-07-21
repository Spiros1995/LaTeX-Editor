package model;

import java.io.IOException;

public class RollbackToPreviousVersionCommand implements Command{
	VersionsStrategy strategy;
	int rollbackNum;
	RollbackToPreviousVersion rollBackToPreviousVersion;
	public RollbackToPreviousVersionCommand(VersionsStrategy strategy, int rollbackNum) {
		this.strategy=strategy;
		this.rollbackNum=rollbackNum;
		rollBackToPreviousVersion = new RollbackToPreviousVersion(strategy,rollbackNum);
	}

	public void execute() throws IOException {
		rollBackToPreviousVersion.RollItBackBaby();
	}

}
