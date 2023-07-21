package model;
import java.io.IOException;

import controller.Controller;

public class LoadCommand implements Command{
	private Load load = new Load();
	private Controller controller;
	public LoadCommand(Controller controller) {
		this.controller=controller;
	}
	public void execute() throws IOException{
		load.loadFile(controller);
		load.loadInfo(controller);
	}
}
