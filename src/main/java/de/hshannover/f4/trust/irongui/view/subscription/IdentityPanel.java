/*
 * #%L
 * =====================================================
 *   _____                _     ____  _   _       _   _
 *  |_   _|_ __ _   _ ___| |_  / __ \| | | | ___ | | | |
 *    | | | '__| | | / __| __|/ / _` | |_| |/ __|| |_| |
 *    | | | |  | |_| \__ \ |_| | (_| |  _  |\__ \|  _  |
 *    |_| |_|   \__,_|___/\__|\ \__,_|_| |_||___/|_| |_|
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
 * Website: http://trust.f4.hs-hannover.de/
 * 
 * This file is part of irongui, version 0.4.6,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2014 Trust@HsH
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

package de.hshannover.f4.trust.irongui.view.subscription;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import de.hshannover.f4.trust.ifmapj.identifier.IdentityType;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class IdentityPanel extends JPanel {
	private static final long serialVersionUID = -5752766552392967794L;
	public JComboBox mType;
	public JTextField mName;
	private JLabel mLabel;
	public JTextField mAdmindomain;
	private JLabel mLblName;
	private JLabel mLblType;
	private JLabel mLblOthertypedefinition;
	public JTextField mOthertypedefinition;

	/**
	 * Create the panel.
	 */
	public IdentityPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWeights = new double[] {0.0, 1.0 };

		setLayout(gridBagLayout);
		GridBagConstraints gbcLblName = new GridBagConstraints();
		gbcLblName.anchor = GridBagConstraints.EAST;
		gbcLblName.insets = new Insets(0, 0, 5, 5);
		gbcLblName.gridx = 0;
		gbcLblName.gridy = 0;
		add(getLblName(), gbcLblName);
		GridBagConstraints gbcName = new GridBagConstraints();
		gbcName.insets = new Insets(0, 0, 5, 0);
		gbcName.fill = GridBagConstraints.HORIZONTAL;
		gbcName.gridx = 1;
		gbcName.gridy = 0;
		add(getIdentityName(), gbcName);
		GridBagConstraints gbcLblType = new GridBagConstraints();
		gbcLblType.insets = new Insets(0, 0, 5, 5);
		gbcLblType.anchor = GridBagConstraints.EAST;
		gbcLblType.gridx = 0;
		gbcLblType.gridy = 1;
		add(getLblType(), gbcLblType);
		GridBagConstraints gbcType = new GridBagConstraints();
		gbcType.anchor = GridBagConstraints.WEST;
		gbcType.insets = new Insets(0, 0, 5, 0);
		gbcType.gridx = 1;
		gbcType.gridy = 1;
		add(getType(), gbcType);
		GridBagConstraints gbcLblOthertypedefinition = new GridBagConstraints();
		gbcLblOthertypedefinition.anchor = GridBagConstraints.EAST;
		gbcLblOthertypedefinition.insets = new Insets(0, 0, 5, 5);
		gbcLblOthertypedefinition.gridx = 0;
		gbcLblOthertypedefinition.gridy = 2;
		add(getLblOthertypedefinition(), gbcLblOthertypedefinition);
		GridBagConstraints gbcOthertypedefinition = new GridBagConstraints();
		gbcOthertypedefinition.insets = new Insets(0, 0, 5, 0);
		gbcOthertypedefinition.fill = GridBagConstraints.HORIZONTAL;
		gbcOthertypedefinition.gridx = 1;
		gbcOthertypedefinition.gridy = 2;
		add(getOthertypedefinition(), gbcOthertypedefinition);
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.anchor = GridBagConstraints.NORTHEAST;
		gbcLabel.insets = new Insets(0, 0, 0, 5);
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 3;
		add(getLabel(), gbcLabel);
		GridBagConstraints gbcAdmindomain = new GridBagConstraints();
		gbcAdmindomain.anchor = GridBagConstraints.NORTH;
		gbcAdmindomain.fill = GridBagConstraints.HORIZONTAL;
		gbcAdmindomain.gridx = 1;
		gbcAdmindomain.gridy = 3;
		add(getAdmindomain(), gbcAdmindomain);
	}

	private JComboBox getType() {
		if (mType == null) {
			ComboBoxModel comboBoxModel = new DefaultComboBoxModel(
					new IdentityType[] {IdentityType.aikName,
							IdentityType.distinguishedName,
							IdentityType.dnsName, IdentityType.emailAddress,
							IdentityType.kerberosPrincipal,
							IdentityType.userName, IdentityType.sipUri,
							IdentityType.telUri, IdentityType.hipHit,
							IdentityType.other });
			comboBoxModel.setSelectedItem(IdentityType.aikName);
			mType = new JComboBox();
			mType.setModel(comboBoxModel);
		}
		return mType;
	}

	private JTextField getIdentityName() {
		if (mName == null) {
			mName = new JTextField();
		}
		return mName;
	}

	private JLabel getLabel() {
		if (mLabel == null) {
			mLabel = new JLabel();
			mLabel.setText("administrative-domain");
		}
		return mLabel;
	}

	private JTextField getAdmindomain() {
		if (mAdmindomain == null) {
			mAdmindomain = new JTextField();
		}
		return mAdmindomain;
	}

	private JLabel getLblName() {
		if (mLblName == null) {
			mLblName = new JLabel("name");
		}
		return mLblName;
	}

	private JLabel getLblType() {
		if (mLblType == null) {
			mLblType = new JLabel("type");
		}
		return mLblType;
	}

	private JLabel getLblOthertypedefinition() {
		if (mLblOthertypedefinition == null) {
			mLblOthertypedefinition = new JLabel("other-type-definition");
		}
		return mLblOthertypedefinition;
	}

	private JTextField getOthertypedefinition() {
		if (mOthertypedefinition == null) {
			mOthertypedefinition = new JTextField();
			mOthertypedefinition.setColumns(10);
		}
		return mOthertypedefinition;
	}
}
