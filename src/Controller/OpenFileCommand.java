/**
 * 
 */
package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;

import UI.IFileEditor;
import UI.IRecentFileOpener;

/**
 * @author andreyf
 *
 */
public class OpenFileCommand extends Command {

	private List<Path> recentFiles;
	private IRecentFileOpener recentFileOpener;
	
	public OpenFileCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
		this.recentFiles = new ArrayList<Path>(5);
	}

	public void addRecentFiles(IRecentFileOpener recentFileOpener) {

		this.recentFileOpener = recentFileOpener;
	}
	
	@Override
	public void execute() {
        File fileToRead = null;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(null);
        if (returnVal == JFileChooser.OPEN_DIALOG)
        	fileToRead = fc.getSelectedFile();
        
        this.openFile(fileToRead.toPath());
	}
	
	public void openFile(Path path) {
		
		StringBuilder fileContents = new StringBuilder();

        BufferedReader bfReader = null;
        
		try {
        	bfReader = Files.newBufferedReader(path);
        	
        	String line = null;
        	do {
        		line = bfReader.readLine();
        		if (line != null) {
        			fileContents.append(line + "\n");
        		}
        	} while(line != null);
        	
        	this.addToRecentFiles(path);
        }
        catch (IOException ex) {
        	this.notifyCommaindFailed("There was an error opening the file: " + path.toAbsolutePath());
        }
        finally {
        	if (bfReader != null) {
        		try {
					bfReader.close();
				} catch (IOException e) {
					// File was probably not opened in the first place
				}
        	}
        }
        
		this.fileEditor.setText(this, fileContents.toString());
	}
	
	private void addToRecentFiles(Path path) {
		if (recentFiles.size() > 4) {
			recentFiles.remove(4); // last item
		}
		
		if (!recentFiles.contains(path)) {
			recentFiles.add(path);
			
			if (this.recentFileOpener != null) {
				this.recentFileOpener.updateRecentPaths(recentFiles);
			}
		}
	}

}
