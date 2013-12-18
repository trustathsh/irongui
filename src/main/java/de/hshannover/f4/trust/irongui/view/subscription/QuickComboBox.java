package de.hshannover.f4.trust.irongui.view.subscription;




import java.awt.Component;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComboBox;
import javax.swing.MenuElement;
import javax.swing.MenuSelectionManager;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

public class QuickComboBox extends JComboBox implements MenuElement {

	private static final long serialVersionUID = 5676645360606860268L;

	public QuickComboBox() {
		//setPopupVisible(false);
		this.addPopupMenuListener(new PopupMenuListener() {
			
			@Override
			public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
				
				
			}
			
			@Override
			public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void popupMenuCanceled(PopupMenuEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public  void hidePopup() {
		System.err.println("hidePopup");
	}
	
	@Override
	public void processMouseEvent(MouseEvent event, MenuElement[] path,
			MenuSelectionManager manager) {
		if (event.getID() == MouseEvent.MOUSE_RELEASED) {
			for (Component c : getComponents()) {
				MouseEvent me = new MouseEvent(c, event.getID(),
						event.getWhen(), event.getModifiers(), event.getX(),
						event.getY(), event.getClickCount(),
						event.isPopupTrigger(), event.getButton());
				for (MouseListener ml : c.getMouseListeners()) {
					ml.mouseReleased(me);
				}
			}
		}
		processMouseEvent(event);
	}

	@Override
	public void processKeyEvent(KeyEvent event, MenuElement[] path,
			MenuSelectionManager manager) {
		processKeyEvent(event);
	}

	@Override
	public void menuSelectionChanged(boolean isIncluded) {
		// TODO Auto-generated method stub

	}

	@Override
	public MenuElement[] getSubElements() {		
		return new MenuElement[0];
	}

	@Override
	public Component getComponent() {
		return this;
	}

}
