/**
 * 
 */
package Controller;

import javax.swing.JOptionPane;

import UI.IFileEditor;

/**
 * @author andreyf
 * This class is the shared interface for command
 * Using the Strategy Pattern
 */
public abstract class Command {

	private String title;
	protected IFileEditor fileEditor;
	
	public Command(String title, IFileEditor fileEditor) {
		this.title = title;
		this.fileEditor = fileEditor;
	}
	
	public String getTitle() {
		return title;
	}
	
	public abstract void execute();
	
	public void notifyCommaindFailed(String error) {
        JOptionPane.showMessageDialog(null,
        		error, this.getTitle(),
                JOptionPane.ERROR_MESSAGE);
	}
	
	public void notifyCommaindInfo(String info) {
        JOptionPane.showMessageDialog(null,
        		info, this.getTitle(),
                JOptionPane.INFORMATION_MESSAGE);
	}
}
