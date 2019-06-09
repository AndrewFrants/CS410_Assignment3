/**
 * 
 */
package Controller;

import UI.IFileEditor;

/**
 * @author andreyf
 *
 */
public class NewCommand extends Command {

	public NewCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
	}

	@Override
	public void execute() {
		this.fileEditor.setText(this, "");
	}

}
