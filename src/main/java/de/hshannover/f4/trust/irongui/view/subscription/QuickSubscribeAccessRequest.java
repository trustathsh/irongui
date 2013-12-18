package de.hshannover.f4.trust.irongui.view.subscription;




import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class QuickSubscribeAccessRequest extends JPanel {
	private static final long serialVersionUID = -4756128279992163659L;
	public JTextField textFieldAdmin;
	public JTextField textFieldName;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public QuickSubscribeAccessRequest() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		setLayout(new BorderLayout(5, 5));
		
		panel = new JPanel();
		add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{119, 127, 58, 0};
		gbl_panel.rowHeights = new int[]{22, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblName = new JLabel("name");
		GridBagConstraints gbc_lblValue = new GridBagConstraints();
		gbc_lblValue.insets = new Insets(0, 0, 5, 5);
		gbc_lblValue.anchor = GridBagConstraints.EAST;
		gbc_lblValue.gridx = 0;
		gbc_lblValue.gridy = 0;
		panel.add(lblName, gbc_lblValue);
		
		textFieldName = new JTextField();
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.gridwidth = 2;
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 0;
		panel.add(textFieldName, gbc_textFieldName);
		textFieldName.setColumns(10);
		
		JLabel lblAdministrativedomain = new JLabel("administrative-domain");
		GridBagConstraints gbc_lblAdministrativedomain = new GridBagConstraints();
		gbc_lblAdministrativedomain.anchor = GridBagConstraints.EAST;
		gbc_lblAdministrativedomain.insets = new Insets(0, 0, 0, 5);
		gbc_lblAdministrativedomain.gridx = 0;
		gbc_lblAdministrativedomain.gridy = 1;
		panel.add(lblAdministrativedomain, gbc_lblAdministrativedomain);
		
		textFieldAdmin = new JTextField();
		GridBagConstraints gbc_textFieldAdmin = new GridBagConstraints();
		gbc_textFieldAdmin.gridwidth = 2;
		gbc_textFieldAdmin.insets = new Insets(0, 0, 0, 5);
		gbc_textFieldAdmin.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldAdmin.gridx = 1;
		gbc_textFieldAdmin.gridy = 1;
		panel.add(textFieldAdmin, gbc_textFieldAdmin);
		textFieldAdmin.setColumns(10);				

	}

}
