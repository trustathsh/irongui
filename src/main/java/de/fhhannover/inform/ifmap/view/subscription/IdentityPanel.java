package de.fhhannover.inform.ifmap.view.subscription;

/*
 * #%L
 * ====================================================
 *   _____                _     ____  _____ _   _ _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \|  ___| | | | | | |
 *    | | | '__| | | / __| __|/ / _` | |_  | |_| | |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _| |  _  |  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_|   |_| |_|_| |_|
 *                             \____/
 * 
 * =====================================================
 * 
 * Hochschule Hannover 
 * (University of Applied Sciences and Arts, Hannover)
 * Faculty IV, Dept. of Computer Science
 * Ricklinger Stadtweg 118, 30459 Hannover, Germany
 * 
 * Email: trust@f4-i.fh-hannover.de
 * Website: http://trust.inform.fh-hannover.de/
 * 
 * This file is part of irongui, version 0.4.1, implemented by the Trust@FHH 
 * research group at the Hochschule Hannover, a program to visualize the content
 * of a MAP Server (MAPS), a crucial component within the TNC architecture.
 * 
 * The development was started within the bachelor
 * thesis of Tobias Ruhe at Hochschule Hannover (University of
 * Applied Sciences and Arts Hannover). irongui is now maintained
 * and extended within the ESUKOM research project. More information
 * can be found at the Trust@FHH website.
 * %%
 * Copyright (C) 2010 - 2013 Trust@FHH
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.fhhannover.inform.trust.ifmapj.identifier.IdentityType;


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
public class IdentityPanel extends JPanel {
	private static final long serialVersionUID = -5752766552392967794L;
	public JComboBox type;
	public JTextField name;
	private JLabel label;
	public JTextField admindomain;
	private JLabel lblName;
	private JLabel lblType;
	private JLabel lblOthertypedefinition;
	public JTextField othertypedefinition;
	/**
	 * Create the panel.
	 */
	public IdentityPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[]{0.0, 1.0};
		
		setLayout(gridBagLayout);
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.insets = new Insets(0, 0, 5, 5);
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		add(getLblName(), gbc_lblName);
		GridBagConstraints gbc_name = new GridBagConstraints();
		gbc_name.insets = new Insets(0, 0, 5, 0);
		gbc_name.fill = GridBagConstraints.HORIZONTAL;
		gbc_name.gridx = 1;
		gbc_name.gridy = 0;
		add(getIdentityName(), gbc_name);
		GridBagConstraints gbc_lblType = new GridBagConstraints();
		gbc_lblType.insets = new Insets(0, 0, 5, 5);
		gbc_lblType.anchor = GridBagConstraints.EAST;
		gbc_lblType.gridx = 0;
		gbc_lblType.gridy = 1;
		add(getLblType(), gbc_lblType);
		GridBagConstraints gbc_type = new GridBagConstraints();
		gbc_type.anchor = GridBagConstraints.WEST;
		gbc_type.insets = new Insets(0, 0, 5, 0);
		gbc_type.gridx = 1;
		gbc_type.gridy = 1;
		add(getType(), gbc_type);
		GridBagConstraints gbc_lblOthertypedefinition = new GridBagConstraints();
		gbc_lblOthertypedefinition.anchor = GridBagConstraints.EAST;
		gbc_lblOthertypedefinition.insets = new Insets(0, 0, 5, 5);
		gbc_lblOthertypedefinition.gridx = 0;
		gbc_lblOthertypedefinition.gridy = 2;
		add(getLblOthertypedefinition(), gbc_lblOthertypedefinition);
		GridBagConstraints gbc_othertypedefinition = new GridBagConstraints();
		gbc_othertypedefinition.insets = new Insets(0, 0, 5, 0);
		gbc_othertypedefinition.fill = GridBagConstraints.HORIZONTAL;
		gbc_othertypedefinition.gridx = 1;
		gbc_othertypedefinition.gridy = 2;
		add(getOthertypedefinition(), gbc_othertypedefinition);
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.NORTHEAST;
		gbc_label.insets = new Insets(0, 0, 0, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 3;
		add(getLabel(), gbc_label);
		GridBagConstraints gbc_admindomain = new GridBagConstraints();
		gbc_admindomain.anchor = GridBagConstraints.NORTH;
		gbc_admindomain.fill = GridBagConstraints.HORIZONTAL;
		gbc_admindomain.gridx = 1;
		gbc_admindomain.gridy = 3;
		add(getAdmindomain(), gbc_admindomain);
	}
	
	private JComboBox getType() {
			if(type == null) {
				ComboBoxModel comboBoxModel = 
					new DefaultComboBoxModel(
							new IdentityType[] { IdentityType.aikName, IdentityType.distinguishedName,
									IdentityType.dnsName, IdentityType.emailAddress, IdentityType.kerberosPrincipal,
									IdentityType.userName, IdentityType.sipUri, IdentityType.telUri, 
									IdentityType.hipHit, IdentityType.other});						
				comboBoxModel.setSelectedItem(IdentityType.aikName);
				type = new JComboBox();
				type.setModel(comboBoxModel);
			}
			return type;
	}
	private JTextField getIdentityName() {
		if (name == null) {
			name = new JTextField();
		}
		return name;
	}
	private JLabel getLabel() {
		if (label == null) {
			label = new JLabel();
			label.setText("administrative-domain");
		}
		return label;
	}
	private JTextField getAdmindomain() {
		if (admindomain == null) {
			admindomain = new JTextField();
		}
		return admindomain;
	}
	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("name");
		}
		return lblName;
	}
	private JLabel getLblType() {
		if (lblType == null) {
			lblType = new JLabel("type");
		}
		return lblType;
	}
	private JLabel getLblOthertypedefinition() {
		if (lblOthertypedefinition == null) {
			lblOthertypedefinition = new JLabel("other-type-definition");
		}
		return lblOthertypedefinition;
	}
	private JTextField getOthertypedefinition() {
		if (othertypedefinition == null) {
			othertypedefinition = new JTextField();
			othertypedefinition.setColumns(10);
		}
		return othertypedefinition;
	}
}
