/**
 * 
 */
package Controller;

import UI.IFileEditor;

/**
 * @author andreyf
 *
 */
public class UndoCommand extends Command {

	public UndoCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
	}

	@Override
	public void execute() {
		this.notifyCommaindFailed("This command is not implemented yet. Send feedback if you'd like us to add it!");
	}

}
