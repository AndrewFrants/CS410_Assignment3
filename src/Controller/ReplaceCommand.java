/**
 * 
 */
package Controller;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import UI.IFileEditor;

/**
 * @author andreyf
 *
 */
public class ReplaceCommand extends Command {

	public ReplaceCommand(String title, IFileEditor fileEditor) {
		super(title, fileEditor);
	}

	@Override
	public void execute() {

        JTextField replaceTxt = new JTextField("");
        JPanel panel = new JPanel(new GridLayout(0, 1));

        panel.add(new JLabel("Replace or insert with: "));
        panel.add(replaceTxt);

        int result = JOptionPane.showConfirmDialog(null, panel, this.getTitle(),
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
        		this.fileEditor.replaceText(this, replaceTxt.getText());
        	}
	}
}
