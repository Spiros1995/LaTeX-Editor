package model;

import java.io.IOException;

public class DisableVersionsManagementCommand implements Command{
	VersionsStrategy strategy;
	private DisableVersionsManagement disabler = new DisableVersionsManagement(strategy);
	public DisableVersionsManagementCommand(VersionsStrategy strategy) {
		this.strategy=strategy;
	}

	public void execute() throws IOException {
		disabler.deleteEverything();
	}

}
