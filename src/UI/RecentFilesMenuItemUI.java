package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuItem;

import Controller.OpenFileCommand;

/*
 * This class is a UI extention for the Recent Files menu options
 */
public class RecentFilesMenuItemUI implements ActionListener, IRecentFileOpener {

	private List<JMenuItem> existingList;
	private JMenuItem recentFilesMenu;
	private OpenFileCommand openFileCommand;
	
	public RecentFilesMenuItemUI(JMenu recentFileMenuItem, OpenFileCommand openFileCommand) {
		this.recentFilesMenu = recentFileMenuItem;
		this.existingList = new ArrayList<JMenuItem>();
		this.openFileCommand = openFileCommand;
		this.updateUI(null);
	}
	
	public void updateUI(List<Path> recentFilePaths) {
		
		for (JMenuItem file : existingList) {
			recentFilesMenu.remove(file);
		}
		
		if (recentFilePaths != null) {
			for (Path path : recentFilePaths) {
				JMenuItem menuItem = new JMenuItem(path.getFileName().toString());
				existingList.add(menuItem);
				recentFilesMenu.add(menuItem);
				menuItem.addActionListener(this);
				menuItem.setActionCommand(path.toAbsolutePath().toString());
			}
		}
		
		if (recentFilePaths == null ||
		    recentFilePaths.isEmpty()) {
			recentFilesMenu.setEnabled(false);
		}
		else
		{
			recentFilesMenu.setEnabled(true);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		Path openFilePath = Paths.get(arg0.getActionCommand());
		openFileCommand.openFile(openFilePath);
	}

	@Override
	public void updateRecentPaths(List<Path> recentFiles) {
		this.updateUI(recentFiles);
		
	}
}
