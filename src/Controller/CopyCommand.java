/**
 * 
 */
package Controller;

import UI.IFileEditor;

/**
 * @author andreyf
 *
 */
public class CopyCommand extends Command {

	public CopyCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
	}

	@Override
	public void execute() {

		fileEditor.copyToClipboard(this);
	}

}
