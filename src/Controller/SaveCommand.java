/**
 * 
 */
package Controller;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import UI.IFileEditor;

/**
 * @author andreyf
 *
 */
public class SaveCommand extends Command {

	public SaveCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
	}

	@Override
	public void execute() {
        File fileToWrite = null;
        PrintWriter out = null;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showSaveDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION)
            fileToWrite = fc.getSelectedFile();
        try {
        	if (fileToWrite != null) {
	            out = new PrintWriter(new FileWriter(fileToWrite));
	            out.println(this.fileEditor.getText(this));
	            JOptionPane.showMessageDialog(null, "File is saved successfully.");
        	}
            
        } catch (IOException ex) {
        	this.notifyCommaindFailed("There was a problem saving the file, file not saved.");
        }
        finally {
        	if (out != null) {
        		out = null;
        	}
        }
	}
}
