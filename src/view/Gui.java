package view;
import controller.*;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.File;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.Timer;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

@SuppressWarnings("serial")
public class Gui extends JFrame{
	
	private JButton New;
	private JFrame newDocumentFrame = new JFrame();
	private JTextArea field;
	private JScrollPane sp;
	private Controller controller = new Controller();
	private JButton Article;
	private JButton Report;
	private JButton Book;
	private JButton Letter;
	private JButton Empty;
	private Action action;
	private JButton Chapter;
	private JButton Section;
	private JButton Subsection;
	private JButton Subsubsection;
	private JButton Itemize;
	private JButton Enumerate;
	private JButton Table;
	private JButton Figure;
	private JButton Save;
	private JButton Load;
	private JCheckBox strategyEnabler;
	private JMenuBar bar;
	private JMenu menu;
	private JRadioButtonMenuItem volatileOption;
	private JRadioButtonMenuItem stableOption;
    private Timer timer;
    private boolean activateListener; 
    private int initiateOrChange;
	private JButton RollBack;
    
	public Gui(){
		super("CSE LaTeX Editor");
		
		setLayout(new FlowLayout());
		New = new JButton("New Document");
		add(New);
		try {
			Image myicon = ImageIO.read(new File(System.getProperty("user.dir") + "/src/view/icon.png"));
			setIconImage(myicon);
		} catch (IOException e) {
			e.printStackTrace();
		}

		//actions and new document buttons
		action = new Action();
		New.addActionListener(action);
		Article = new JButton("Article");
		Report = new JButton("Report");
		Book = new JButton("Book");
		Letter = new JButton("Letter");
		Empty = new JButton("<Empty>");
		
		//Command buttons
			//Chapter
		Chapter = new JButton("Add Chapter");
		add(Chapter);
		ChapterAction chapterAction = new ChapterAction();
		Chapter.addActionListener(chapterAction);
			
			//Section
		Section = new JButton("Add Section");
		add(Section);
		SectionAction sectionAction = new SectionAction();
		Section.addActionListener(sectionAction);
		
			//Subsection
		Subsection = new JButton("Add Subsection");
		add(Subsection);
		SubsectionAction subsectionAction = new SubsectionAction();
		Subsection.addActionListener(subsectionAction);
		
			//Subsubsection
		Subsubsection = new JButton("Add Subsubsection");
		add(Subsubsection);
		SubsubsectionAction subsubsectionAction = new SubsubsectionAction();
		Subsubsection.addActionListener(subsubsectionAction);
		
			//Itemize
		Itemize = new JButton("Add Itemize List");
		add(Itemize);
		ItemizeAction itemizeAction = new ItemizeAction();
		Itemize.addActionListener(itemizeAction);
		
			//Enumerate
		Enumerate = new JButton("Add Enumeration List");
		add(Enumerate);
		EnumerateAction enumerateAction = new EnumerateAction();
		Enumerate.addActionListener(enumerateAction);
		
			//Table
		Table = new JButton("Add Table");
		add(Table);
		TableAction tableAction = new TableAction();
		Table.addActionListener(tableAction);
		
			//Figure
		Figure = new JButton("Add Figure");
		add(Figure);
		FigureAction figureAction = new FigureAction();
		Figure.addActionListener(figureAction);
		
			//Save
		Save = new JButton("Save TeX File");
		add(Save);
		SaveAction saveAction = new SaveAction();
		Save.addActionListener(saveAction);
		
			//Load
		Load = new JButton("Load TeX File");
		add(Load);
		LoadAction loadAction = new LoadAction();
		Load.addActionListener(loadAction);

		
		ArticleAction articleAction = new ArticleAction();
		Article.addActionListener(articleAction);
		BookAction bookAction = new BookAction();
		Book.addActionListener(bookAction);
		ReportAction reportAction = new ReportAction();
		Report.addActionListener(reportAction);
		LetterAction letterAction = new LetterAction();
		Letter.addActionListener(letterAction);
		EmptyAction emptyAction = new EmptyAction();
		Empty.addActionListener(emptyAction);
		
	    ItemListener itemListener = new ItemListener() {
	    	public void itemStateChanged(ItemEvent e) {
	    	    Object source = e.getItemSelectable();

	    	    if (source == strategyEnabler) {
	    		    if (e.getStateChange() == ItemEvent.SELECTED) {
	    		    	activateListener=true;
	    		    	menu.setEnabled(true);
	    		    	volatileOption.setSelected(true);
	    		    	try {
							controller.enableVersions();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	    		    }else {
	    		    	activateListener=false;
	    		    	try {
							controller.disableVersions();
							initiateOrChange=0;
							
						} catch (IOException e1) {
							e1.printStackTrace();
						}
	    		    	menu.setEnabled(false);
	    		    }
	    	    }
	    	    if (source == volatileOption) {
	    		    if (e.getStateChange() == ItemEvent.SELECTED) {
	    		    	if(initiateOrChange>0) {
	    		    		try {
								controller.changeVersions();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
	    		    	}
	    		    	controller.setStrategyType("volatile");

	    		    		initiateOrChange++;
	    		    		stableOption.setSelected(false);
	    		    }else {
	    		    	if (stableOption.isSelected()==false) {
	    		    		volatileOption.setSelected(true);
	    		    	}
	    		    }
	    	    }
	    	    if (source == stableOption) {
		    		    if (e.getStateChange() == ItemEvent.SELECTED) {
		    		    	if(initiateOrChange>0) {
		    		    		try {
									controller.changeVersions();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
		    		    	}
		    		    	controller.setStrategyType("stable");
		    		    	initiateOrChange++; 	
		    		    	volatileOption.setSelected(false);
		    		    }else {
		    		    	if (volatileOption.isSelected()==false) {
		    		    		stableOption.setSelected(true);
		    		    	}
		    		    }
	    	    }
	    	}
	    };
	    RollBack = new JButton("Rollback");
	    RollBackAction rollbackAction = new RollBackAction();
	    RollBack.addActionListener(rollbackAction);
	    add(RollBack);

	    strategyEnabler = new JCheckBox("Enable auto save");
	    strategyEnabler.setMnemonic(KeyEvent.VK_C); 
	    strategyEnabler.setSelected(false);
	    add(strategyEnabler);
	    strategyEnabler.addItemListener(itemListener);

	    bar = new JMenuBar();
	    menu = new JMenu("Save Options");
	    menu.setMnemonic(KeyEvent.VK_O);
	    volatileOption = new JRadioButtonMenuItem("Volatile");
	    volatileOption.addItemListener(itemListener);
	    menu.add(volatileOption);
	    stableOption = new JRadioButtonMenuItem("Stable");
	    stableOption.addItemListener(itemListener);
	    menu.add(stableOption);
	    menu.setEnabled(false);
	    bar.add(menu);
	    add(bar);
		//text area 
		field = new JTextArea(80,80);
		sp = new JScrollPane(field);
		sp.setPreferredSize(new Dimension(850, 800));
		add(sp);
		DeferredDocumentListener listener = new DeferredDocumentListener(1000, new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	            	if(activateListener) {
	            		controller.setContents(field.getText());
	            		controller.setVersionID(controller.getVersionID()+1);
	            		controller.getStrategy().putVersion(controller.getDocument());
	                	timer.stop();
	            	}
	            }
        }, true);
		
		
        field.getDocument().addDocumentListener(listener);
        field.getDocument().addDocumentListener(listener);

	}	
    public class DeferredDocumentListener implements DocumentListener {

        public DeferredDocumentListener(int timeOut, ActionListener listener, boolean repeats) {
            timer = new Timer(timeOut, listener);
            timer.setRepeats(repeats);
        }

        public void insertUpdate(DocumentEvent e) {
            timer.start();
        }

        public void removeUpdate(DocumentEvent e) {
            timer.start();
        }

        public void changedUpdate(DocumentEvent e) {
            timer.start();
        }

    }

	
	private class RollBackAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			if(activateListener) {
				if(controller.getVersionID()==0){
					JOptionPane.showMessageDialog(null, "No previous versions found, make sure to type something first!", "Error", JOptionPane.ERROR_MESSAGE);
				}else{
					String rollbackNumber = JOptionPane.showInputDialog("Enter the version number you want to rollback to (1 - " + controller.getVersionID() +")");
			        try {
			            int rollbacknum = Integer.parseInt(rollbackNumber)-1;
						if( rollbacknum<0 || rollbacknum>controller.getVersionID()) {
							JOptionPane.showMessageDialog(null, "Version number was incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
						}else {
							controller.setRollback(rollbacknum);
							try {
								controller.rollbackToPrevious();
								field.setText(controller.getContents());
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
			        } catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null, "Version number must be a NUMBER!", "Error", JOptionPane.ERROR_MESSAGE);
			        }
				}
			}else {
				JOptionPane.showMessageDialog(null, "Can't rollback if autosave is disabled!", "Error", JOptionPane.ERROR_MESSAGE);
			}
						
		}
	}
    
	private class ChapterAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter") || controller.getType().equals("article")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\chapter{ }", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class SectionAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\section{ }", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class SubsectionAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\subsection{ }", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class SubsubsectionAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\subsubsection{ }", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class ItemizeAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\begin{itemize}\n" + "\\item ...\n" + "\\item ...\n" + "\\end{itemize}", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class EnumerateAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\begin{enumerate}\n" + "\\item ...\n" + "\\item ...\n" + "\\end{enumerate}", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class TableAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\begin{table}\n" + "\\caption{....}\\label{...}\n" + "\\begin{tabular}{|c|c|c|}\n" + " \\hline\n" + "... &...&...\\\\\n" + 
						"... &...&...\\\\\n" + "... &...&...\\\\\n" + " \\hline\n" + "\\end{tabular}\n" + "\\end{table}", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class FigureAction implements ActionListener{

		public void actionPerformed(ActionEvent e) {

			if(controller.getType().equals("letter")) {
				JOptionPane.showMessageDialog(null, "Not available for this type of document!", "Error", JOptionPane.ERROR_MESSAGE);	
			}
			else {
				field.insert("\\begin{figure}\n" + "\\includegraphics[width=...,height=...]{...}\n" + "\\caption{....}\\label{...}\n" + "\\end{figure}", field.getCaretPosition());
				controller.setContents(field.getText());
			}
		}
	}
	
	private class SaveAction implements ActionListener{

	  public void actionPerformed(ActionEvent e) {
	        JFileChooser c = new JFileChooser();
	        int rVal = c.showSaveDialog(Gui.this);
	        if (rVal == JFileChooser.APPROVE_OPTION) {
	            controller.setFileName(c.getSelectedFile().getName());
	            controller.setFilePath(c.getCurrentDirectory().toString());
	      		try {
	      			controller.setContents(field.getText());
					controller.save();
					JOptionPane.showMessageDialog(null, "File " + c.getSelectedFile().getName() + " created succesfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
	        }
	  }
		
	}
	
	private class LoadAction implements ActionListener{

		  public void actionPerformed(ActionEvent e) {
		        JFileChooser c = new JFileChooser();
		        int rVal = c.showOpenDialog(Gui.this);
		        if (rVal == JFileChooser.APPROVE_OPTION) {
		            controller.setFileName(c.getSelectedFile().getName());
		            String[] split = c.getSelectedFile().getName().split("\\.");
		            String ext = split[split.length - 1];
		            //checks if file is .tex file
		            if(ext.equals("tex")){
		            	//checks if file exists
		            	File tempFile = new File(c.getCurrentDirectory() + "/" + c.getSelectedFile().getName());
		            	if(tempFile.exists()){
				      		try {
				      			//makes new clear file and calls load
					            controller.setFilePath(c.getCurrentDirectory().toString());
				      			controller.setType("empty");
				      			controller.factoryMethod();
								controller.load();
								field.setText(controller.getContents());
								JOptionPane.showMessageDialog(null, "File " + c.getSelectedFile().getName() + " loaded succesfully!", "Success!", JOptionPane.INFORMATION_MESSAGE);
							} catch (IOException e1) {
								e1.printStackTrace();
							}
		            	}else{
							JOptionPane.showMessageDialog(null, "You can't load files that don't exist!", "Error", JOptionPane.ERROR_MESSAGE);	
		            	}
		            }else{
						JOptionPane.showMessageDialog(null, "You can only load .tex files!", "Error", JOptionPane.ERROR_MESSAGE);	
		            }
		        }
		  }		
	}

	private class Action implements ActionListener{

		@SuppressWarnings("static-access")
		public void actionPerformed(ActionEvent e) {
			newDocumentFrame.setDefaultCloseOperation(newDocumentFrame.DISPOSE_ON_CLOSE);
			newDocumentFrame.setLayout(new FlowLayout());
			newDocumentFrame.setSize(250, 250);
			newDocumentFrame.setVisible(true);
			
			
			newDocumentFrame.add(Article);
			newDocumentFrame.add(Book);
			newDocumentFrame.add(Report);
			newDocumentFrame.add(Letter);
			newDocumentFrame.add(Empty);
		}
		
	}	
		//Action for Article
		private class ArticleAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				controller.setType("article");
				controller.factoryMethod();

				String author = JOptionPane.showInputDialog("Enter author name: ");
				String copyright = JOptionPane.showInputDialog("Enter copyrights: ");

				controller.setAuthor(author);
				controller.setCopyright(copyright);

				field.setText(controller.getContents());
				
				newDocumentFrame.dispose();
			}
			
		}
		
		//Action for Book
		private class BookAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				controller.setType("book");
				controller.factoryMethod();
				String author = JOptionPane.showInputDialog("Enter author name: ");
				String copyright = JOptionPane.showInputDialog("Enter copyrights: ");

				controller.setAuthor(author);
				controller.setCopyright(copyright);	
				
				field.setText(controller.getContents());

				newDocumentFrame.dispose();

			}
			
		}
		
		//Action for Report
		private class ReportAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				controller.setType("report");
				controller.factoryMethod();
				String author = JOptionPane.showInputDialog("Enter author name: ");
				String copyright = JOptionPane.showInputDialog("Enter copyrights: ");

				controller.setAuthor(author);
				controller.setCopyright(copyright);	
				
				field.setText(controller.getContents());
				
				newDocumentFrame.dispose();

			}
			
		}
		
		//Action for Letter
		private class LetterAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				controller.setType("letter");
				controller.factoryMethod();
				String author = JOptionPane.showInputDialog("Enter author name: ");
				String copyright = JOptionPane.showInputDialog("Enter copyrights: ");

				controller.setAuthor(author);
				controller.setCopyright(copyright);	
				field.setText(controller.getContents());
				
				newDocumentFrame.dispose();

			}
			
		}
		
		//Action for Empty
		private class EmptyAction implements ActionListener {

			public void actionPerformed(ActionEvent e) {
				controller.setType("empty");
				controller.factoryMethod();
				String author = JOptionPane.showInputDialog("Enter author name: ");
				String copyright = JOptionPane.showInputDialog("Enter copyrights: ");

				controller.setAuthor(author);
				controller.setCopyright(copyright);

				field.setText(controller.getContents());

				newDocumentFrame.dispose();

			}
			
		}

	
	public static void main(String[] args) {
		Gui g = new Gui();
		g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		g.setSize(950,925);
		g.setVisible(true);
	}
}
