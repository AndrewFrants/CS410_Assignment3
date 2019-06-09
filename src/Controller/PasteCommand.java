/**
 * 
 */
package Controller;

import UI.IFileEditor;

/**
 * @author andreyf
 *
 */
public class PasteCommand extends Command {

	public PasteCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
	}

	@Override
	public void execute() {
		fileEditor.pasteFromClipboard(this);
	}

}
