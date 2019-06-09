package UI;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.Position;
import javax.swing.text.StyledDocument;

import Controller.Command;
import Controller.CopyCommand;
import Controller.NewCommand;
import Controller.OpenFileCommand;
import Controller.PasteCommand;
import Controller.PrintCommand;
import Controller.ReplaceCommand;
import Controller.SaveCommand;
import Controller.UndoCommand;

public class SimpleNotePad extends JFrame implements IFileEditor {
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File");
    JMenu editMenu = new JMenu("Edit");
    JTextPane fileEditor = new JTextPane();
    JMenuItem newFileMenuItem = new JMenuItem("New File");
    JMenuItem saveFileMenuItem = new JMenuItem("Save File");
    JMenuItem openFileMenuItem = new JMenuItem("Open File");
    JMenu openRecentMenuItem = new JMenu("Recent");
    JMenuItem printFileMenuItem = new JMenuItem("Print File");
    JMenuItem replaceTextMenuItem = new JMenuItem("Replace Text");
    JMenuItem undoMenuItem = new JMenuItem("Undo");
    JMenuItem copyMenuItem = new JMenuItem("Copy");
    JMenuItem pasteMenuItem = new JMenuItem("Paste");
    ActionAdapter actionAdapter;
    
    public SimpleNotePad() {
    	actionAdapter = new ActionAdapter();
        setTitle("A Simple Notepad Tool - CS410 - Refactored by Andrew Frantsuzov");
        fileMenu.add(newFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(saveFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(openFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(printFileMenuItem);
        fileMenu.addSeparator();
        fileMenu.add(openRecentMenuItem);
        
        editMenu.add(undoMenuItem);
        editMenu.add(copyMenuItem);
        editMenu.add(pasteMenuItem);
        editMenu.add(replaceTextMenuItem);
        
        OpenFileCommand openCommand = new OpenFileCommand("Open Command", this);
        RecentFilesMenuItemUI rfmi = new RecentFilesMenuItemUI(openRecentMenuItem, openCommand);
        openCommand.addRecentFiles(rfmi);
        
        actionAdapter.registerAction(newFileMenuItem, new NewCommand("New File", this));
        actionAdapter.registerAction(saveFileMenuItem, new SaveCommand("Save File", this));
        actionAdapter.registerAction(printFileMenuItem, new PrintCommand("Print File", this));
        actionAdapter.registerAction(copyMenuItem, new CopyCommand("Copy Text", this));
        actionAdapter.registerAction(pasteMenuItem, new PasteCommand("Paset Text", this));
        actionAdapter.registerAction(undoMenuItem, new UndoCommand("Undo Command", this));
        actionAdapter.registerAction(openFileMenuItem, openCommand);
        actionAdapter.registerAction(replaceTextMenuItem, new ReplaceCommand("Replace Command", this));
        

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        setJMenuBar(menuBar);
        add(new JScrollPane(fileEditor));
        setPreferredSize(new Dimension(600,600));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }
    
	@Override
    public void print(Command cmd) {
        try{
            PrinterJob pjob = PrinterJob.getPrinterJob();
            pjob.setJobName("Sample Command Pattern");
            pjob.setCopies(1);
            pjob.setPrintable(new Printable() {
                public int print(Graphics pg, PageFormat pf, int pageNum) {
                    if (pageNum>0)
                        return Printable.NO_SUCH_PAGE;
                    pg.drawString(fileEditor.getText(), 500, 500);
                    paint(pg);
                    return Printable.PAGE_EXISTS;
                }
            });

            if (pjob.printDialog() == false)
                return;
            pjob.print();
        } catch (PrinterException pe) {
        	cmd.notifyCommaindFailed("Failed to print");
        }
    }

	@Override
	public void setText(Command cmd, String txt) {
		fileEditor.setText(txt);
		
	}

	@Override
	public String getText(Command cmd) {
		return fileEditor.getText();
	}

	@Override
	public void copyToClipboard(Command cmd) {
        fileEditor.copy();
		
	}

	@Override
	public void pasteFromClipboard(Command cmd) {
        fileEditor.paste();
	}

	@Override
	public void replaceText(Command cmd, String replaceTxt) {
		
		String text = fileEditor.getText();
		
		String ltext = text.substring(0, fileEditor.getSelectionStart());
		String rtext = text.substring(fileEditor.getSelectionEnd(), text.length());
		
		
		fileEditor.setText(ltext + replaceTxt + rtext);
	}
}