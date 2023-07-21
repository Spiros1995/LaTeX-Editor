//Onoma		Epwnymo		AM
//Spyridwn	Kaloudis	2447
//Dimos		Gkitsakis	2425
//Mwlos		Ioannis		2500

package controller;

import java.io.IOException;
import java.util.HashMap;

import model.ChangeVersionsStrategyCommand;
import model.Command;
import model.DisableVersionsManagementCommand;
import model.Document;
import model.DocumentFactory;
import model.EnableVersionsManagementCommand;
import model.LoadCommand;
import model.RollbackToPreviousVersionCommand;
import model.SaveCommand;
import model.VersionsStrategy;

public class Controller {
	private Document document;
	private DocumentFactory factory;
	private String fileName;
	private String path;
	private String strategyVersion;
	private VersionsStrategy strategy;
	private EnableVersionsManagementCommand enableVersions;
	private DisableVersionsManagementCommand disableVersions;
	private ChangeVersionsStrategyCommand changeStrategy;
	private int rollbackNum;
	private HashMap<String, Command> commandsMap;

	
	public Controller(){
		document = new Document();
		factory = new DocumentFactory();
		commandsMap = new HashMap<String, Command>();
	}
	
	//Constructor for load
	public Controller(Document document2, DocumentFactory factory2, String fileName2, String path2, String strategyVersion) {
		fileName=fileName2;
		path=path2;
		document=document2;
		factory=factory2;
		this.strategyVersion=strategyVersion;
	}

	//Setters
	public void setType(String type){
		document.setType(type);
	}
	
	public void setAuthor(String author) {
		document.setAuthor(author);
	}
	
	public void setCopyright(String copyright) {
		document.setCopyright(copyright);
	}

	public void setContents(String text) {
		document.setContents(text);
	}

	public void setFileName(String name){
		fileName=name;
	}
	
	public void setFilePath(String path){
		this.path=path;
	}

	public void setDate(String date) {
		document.setDate(date);
	}

	public void setVersionID(int parseInt) {
		document.setVersionID(parseInt);
	}	
	
	public void setRollback(int num) {
		rollbackNum=num;
	}
	
	public void setStrategyType(String strategyVersion) {
		this.strategyVersion = strategyVersion;
	}
	
	public void setStrategy(VersionsStrategy strategy) {
		this.strategy = strategy;
	}
	
	public String getStrategyType() {
		return strategyVersion;
	}

	//Set up a new Document
	public void factoryMethod() {
		document = factory.factoryMethod(document.getType());
	}

	//Getters
	public String getContents() {
		return document.getContents();
	}
	
	public String getType() {
		return document.getType();
	}

	public String getAuthor() {
		return document.getAuthor();
	}	
	
	public String getFileName(){
		return fileName;
	}
	
	public String getFilePath(){
		return path;
	}
	
	public String getDate() {
		return document.getDate();
	}

	public int getVersionID() {
		return document.getVersionID();
	}

	public String getCopyright() {
		return document.getCopyright();
	}
	
	public VersionsStrategy getStrategy() {
		return strategy;
	}

	public Document getDocument() {
		return document;
	}
	
	//Add contents at the end
	public void addContents(String st) {
		document.addContents(st);
	}

	//Get copy of controller to access a clone of all fields
	public Controller getController(){
		return new Controller(this.document,this.factory,this.fileName,this.path,this.strategyVersion);
	}
	
	//Update all commands with the correct-updated fields needed for each one
	public void updateCommands() {
		commandsMap.clear();
		SaveCommand save = new SaveCommand(document,fileName,path);
		commandsMap.put("save", save);
		LoadCommand load = new LoadCommand(getController());
		commandsMap.put("load", load);
		enableVersions = new EnableVersionsManagementCommand(strategyVersion);
		commandsMap.put("enableVersions", enableVersions);
		disableVersions = new DisableVersionsManagementCommand(strategy);
		commandsMap.put("disableVersions", disableVersions);
		changeStrategy = new ChangeVersionsStrategyCommand(strategy, strategyVersion);
		commandsMap.put("changeStrategy", changeStrategy);
		RollbackToPreviousVersionCommand rollback = new RollbackToPreviousVersionCommand(strategy, rollbackNum);
		commandsMap.put("rollback", rollback);
	}
	
	//Execute a command using it's key
	public void issueCommand(String commandKey) throws IOException {
		commandsMap.get(commandKey).execute();
	}

	//Add new commands for extensibility
	public void addCommand(String commandKey, Command command){
		commandsMap.put(commandKey, command);
	}
		
	//Commands
	public void save() throws IOException {
		updateCommands();
		issueCommand("save");
	}

	public void load() throws IOException {
		updateCommands();
		issueCommand("load");
	}
	
	public void enableVersions() throws IOException {
		updateCommands();
		issueCommand("enableVersions");
		setStrategy(enableVersions.getStrategy());
	}
	
	public void disableVersions() throws IOException {
		updateCommands();
		issueCommand("disableVersions");
		setVersionID(0);
	}
	
	public void changeVersions() throws IOException {
		updateCommands();
		issueCommand("changeStrategy");
		setStrategy(changeStrategy.getStrategy());
	}

	public void rollbackToPrevious() throws IOException {
		updateCommands();
		issueCommand("rollback");
		document = strategy.getVersion();
	}
	
}
