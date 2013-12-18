package de.hshannover.f4.trust.irongui.view.subscription;




import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class AccessRequestPanel extends JPanel {

	private static final long serialVersionUID = 62504418071361286L;
	
	public JTextField name;
	public JTextField admin;
	/**
	 * Create the panel.
	 */
	public AccessRequestPanel() {
		GridBagLayout thisLayout = new GridBagLayout();
		this.setLayout(thisLayout);
		this.setPreferredSize(new java.awt.Dimension(432, 83));
		thisLayout.rowWeights = new double[] {0.1, 0.1};
		thisLayout.rowHeights = new int[] {7, 7};
		thisLayout.columnWeights = new double[] {0.0, 0.0};
		thisLayout.columnWidths = new int[] {156, 229};

		JLabel lblValue = new JLabel("name");
		lblValue.setHorizontalAlignment(SwingConstants.LEFT);
		this.add(lblValue, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));		
		name = new JTextField();
		this.add(name, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
		name.setColumns(20);
		
		JLabel lblAikname = new JLabel("administrativ-domain");
		this.add(lblAikname, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
		
		admin = new JTextField();
		this.add(admin, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.HORIZONTAL, new Insets(0, 5, 0, 5), 0, 0));
		admin.setColumns(20);

	}

}
