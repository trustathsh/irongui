package de.hshannover.f4.trust.irongui.view.component;




import java.awt.Component;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.util.ResourceHelper;

@SuppressWarnings("serial")
public class ConnectionListRenderer extends JLabel implements ListCellRenderer {

	private ImageIcon redIcon = ResourceHelper.getImage("bullet_red.png");
	
	private ImageIcon greenIcon = ResourceHelper.getImage("bullet_green.png");
	
//	private ImageIcon yellowIcon = new ImageIcon(getClass().getClassLoader()
//			.getResource("ball_yellow_small.png"));
	
	public ConnectionListRenderer() {
		setOpaque(true);	
	}
	
	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		//int selectedIndex = ((Integer)value).intValue();
		Connection con = (Connection)value;
		if(con.isConnected()){
			setIcon(greenIcon);
		}
		else {
			setIcon(redIcon);
		}
		setText(value.toString());
		if (isSelected) {
            setBackground(list.getSelectionBackground());
            setForeground(list.getSelectionForeground());
        } else {
            setBackground(list.getBackground());
            setForeground(list.getForeground());
        }
		setFont(list.getFont());
		return this;
	}

}
