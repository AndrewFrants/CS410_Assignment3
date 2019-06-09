/**
 * 
 */
package UI;

import Controller.Command;

/**
 * @author andreyf
 * This the the UI interface required to full
 * fill the features required by this application
 * It may be implemented by any UI which supports
 * the file editor functionality
 */
public interface IFileEditor {

	void setText(Command cmd, String txt);
	String getText(Command cmd);
	void copyToClipboard(Command cmd);
	void pasteFromClipboard(Command cmd);
	void print(Command cmd);
	void replaceText(Command cmd, String replaceTxt);
}
