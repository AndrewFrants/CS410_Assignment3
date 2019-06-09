/**
 * 
 */
package Controller;

import UI.IFileEditor;

/**
 * @author andreyf
 *
 */
public class PrintCommand extends Command {

	public PrintCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
	}

	@Override
	public void execute() {
		fileEditor.print(this);
	}

}
