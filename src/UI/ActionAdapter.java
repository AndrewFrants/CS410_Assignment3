/**
 * 
 */
package UI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Hashtable;

import javax.swing.AbstractButton;

import Controller.Command;

/**
 * @author andreyf
 * This adapter class convers UI actions to controller commands
 */
public class ActionAdapter implements ActionListener {

	private Hashtable<String, Command> actionRegistrations;
	
	public ActionAdapter() {
		
		actionRegistrations = new Hashtable<String, Command>();
	}
	
	public void registerAction(AbstractButton bt, Command cmd) {
		actionRegistrations.put(cmd.getTitle(), cmd);
		bt.addActionListener(this);
		bt.setActionCommand(cmd.getTitle());
	}
	
	@Override
	public void actionPerformed(ActionEvent action) {
		assert(actionRegistrations.containsKey(action.getActionCommand())); // action registration must be created
		actionRegistrations.get(action.getActionCommand()).execute();
	}

	
}
