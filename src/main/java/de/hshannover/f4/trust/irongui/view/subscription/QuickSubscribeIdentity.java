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
 * This file is part of irongui, version 0.4.7,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2015 Trust@HsH
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

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class QuickSubscribeIdentity extends JPanel {
	private static final long serialVersionUID = 7669707523341935526L;
	public JTextField mTextFieldDomain;
	public final ButtonGroup mType = new ButtonGroup();
	private JLabel mLabel1;
	public JTextField mTextFieldOther;
	private JLabel mLabel;
	public JTextField mTextFieldName;
	private JPanel mPanel;
	private JRadioButton mRadioButton9;
	private JRadioButton mRadioButton8;
	private JRadioButton mRadioButton7;
	private JRadioButton mRadioButton6;
	private JRadioButton mRadioButton5;
	private JRadioButton mRadioButton4;
	private JRadioButton mRadioButton;
	private JRadioButton mRadioButton3;
	private JRadioButton mRadioButton1;
	private JRadioButton mRadioButton2;

	/**
	 * Create the panel.
	 */
	public QuickSubscribeIdentity() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {0, 155, 0 };
		gridBagLayout.rowHeights = new int[] {0, 0, 0, 0, 0 };
		gridBagLayout.columnWeights = new double[] {0.0, 0.0, Double.MIN_VALUE };
		gridBagLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		setLayout(gridBagLayout);

		mPanel = new JPanel();
		mPanel.setBorder(new TitledBorder(null, "type", TitledBorder.LEADING,
				TitledBorder.TOP, null, null));
		GridBagConstraints gbcPanel = new GridBagConstraints();
		gbcPanel.gridwidth = 2;
		gbcPanel.insets = new Insets(0, 0, 5, 5);
		gbcPanel.gridx = 0;
		gbcPanel.gridy = 0;
		add(mPanel, gbcPanel);
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[] {0, 0, 0 };
		gblPanel.rowHeights = new int[] {0, 0, 0, 0, 0, 0 };
		gblPanel.columnWeights = new double[] {0.0, 0.0, Double.MIN_VALUE };
		gblPanel.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		mPanel.setLayout(gblPanel);

		mRadioButton9 = new JRadioButton("aik-name");
		mType.add(mRadioButton9);
		mRadioButton9.setActionCommand("aik-name");
		GridBagConstraints gbcRadioButton_9 = new GridBagConstraints();
		gbcRadioButton_9.fill = GridBagConstraints.HORIZONTAL;
		gbcRadioButton_9.insets = new Insets(0, 0, 5, 5);
		gbcRadioButton_9.gridx = 0;
		gbcRadioButton_9.gridy = 0;
		mPanel.add(mRadioButton9, gbcRadioButton_9);

		mRadioButton8 = new JRadioButton("distinguished-name");
		mType.add(mRadioButton8);
		mRadioButton8.setActionCommand("distinguished-name");
		GridBagConstraints gbcRadioButton_8 = new GridBagConstraints();
		gbcRadioButton_8.anchor = GridBagConstraints.WEST;
		gbcRadioButton_8.insets = new Insets(0, 0, 5, 0);
		gbcRadioButton_8.gridx = 1;
		gbcRadioButton_8.gridy = 0;
		mPanel.add(mRadioButton8, gbcRadioButton_8);

		mRadioButton7 = new JRadioButton("dns-name");
		mType.add(mRadioButton7);
		mRadioButton7.setActionCommand("dns-name");
		GridBagConstraints gbcRadioButton_7 = new GridBagConstraints();
		gbcRadioButton_7.fill = GridBagConstraints.HORIZONTAL;
		gbcRadioButton_7.insets = new Insets(0, 0, 5, 5);
		gbcRadioButton_7.gridx = 0;
		gbcRadioButton_7.gridy = 1;
		mPanel.add(mRadioButton7, gbcRadioButton_7);

		mRadioButton6 = new JRadioButton("email-address");
		mType.add(mRadioButton6);
		mRadioButton6.setActionCommand("email-address");
		GridBagConstraints gbcRadioButton_6 = new GridBagConstraints();
		gbcRadioButton_6.anchor = GridBagConstraints.WEST;
		gbcRadioButton_6.insets = new Insets(0, 0, 5, 0);
		gbcRadioButton_6.gridx = 1;
		gbcRadioButton_6.gridy = 1;
		mPanel.add(mRadioButton6, gbcRadioButton_6);

		mRadioButton5 = new JRadioButton("hip-tip");
		mType.add(mRadioButton5);
		mRadioButton5.setActionCommand("hip-hit");
		GridBagConstraints gbcRadioButton_5 = new GridBagConstraints();
		gbcRadioButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbcRadioButton_5.insets = new Insets(0, 0, 5, 5);
		gbcRadioButton_5.gridx = 0;
		gbcRadioButton_5.gridy = 2;
		mPanel.add(mRadioButton5, gbcRadioButton_5);

		mRadioButton4 = new JRadioButton("kerberos-principal");
		mType.add(mRadioButton4);
		mRadioButton4.setActionCommand("kerberos-principal");
		GridBagConstraints gbcRadioButton_4 = new GridBagConstraints();
		gbcRadioButton_4.anchor = GridBagConstraints.WEST;
		gbcRadioButton_4.insets = new Insets(0, 0, 5, 0);
		gbcRadioButton_4.gridx = 1;
		gbcRadioButton_4.gridy = 2;
		mPanel.add(mRadioButton4, gbcRadioButton_4);

		mRadioButton = new JRadioButton("other");
		mType.add(mRadioButton);
		mRadioButton.setActionCommand("other");
		GridBagConstraints gbcRadioButton = new GridBagConstraints();
		gbcRadioButton.fill = GridBagConstraints.HORIZONTAL;
		gbcRadioButton.insets = new Insets(0, 0, 5, 5);
		gbcRadioButton.gridx = 0;
		gbcRadioButton.gridy = 3;
		mPanel.add(mRadioButton, gbcRadioButton);

		mRadioButton3 = new JRadioButton("sip-uri");
		mType.add(mRadioButton3);
		mRadioButton3.setActionCommand("sip-uri");
		GridBagConstraints gbcRadioButton_3 = new GridBagConstraints();
		gbcRadioButton_3.anchor = GridBagConstraints.WEST;
		gbcRadioButton_3.insets = new Insets(0, 0, 5, 0);
		gbcRadioButton_3.gridx = 1;
		gbcRadioButton_3.gridy = 3;
		mPanel.add(mRadioButton3, gbcRadioButton_3);

		mRadioButton1 = new JRadioButton("tel-uri");
		mType.add(mRadioButton1);
		mRadioButton1.setActionCommand("tel-uri");
		GridBagConstraints gbcRadioButton_1 = new GridBagConstraints();
		gbcRadioButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbcRadioButton_1.insets = new Insets(0, 0, 0, 5);
		gbcRadioButton_1.gridx = 0;
		gbcRadioButton_1.gridy = 4;
		mPanel.add(mRadioButton1, gbcRadioButton_1);

		mRadioButton2 = new JRadioButton("username");
		mType.add(mRadioButton2);
		mRadioButton2.setActionCommand("username");
		GridBagConstraints gbcRadioButton_2 = new GridBagConstraints();
		gbcRadioButton_2.anchor = GridBagConstraints.WEST;
		gbcRadioButton_2.gridx = 1;
		gbcRadioButton_2.gridy = 4;
		mPanel.add(mRadioButton2, gbcRadioButton_2);

		mLabel = new JLabel("name");
		GridBagConstraints gbcLabel = new GridBagConstraints();
		gbcLabel.anchor = GridBagConstraints.EAST;
		gbcLabel.insets = new Insets(0, 0, 5, 5);
		gbcLabel.gridx = 0;
		gbcLabel.gridy = 1;
		add(mLabel, gbcLabel);

		mTextFieldName = new JTextField();
		mTextFieldName.setColumns(10);
		GridBagConstraints gbcTextFieldName = new GridBagConstraints();
		gbcTextFieldName.insets = new Insets(0, 0, 5, 0);
		gbcTextFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldName.gridx = 1;
		gbcTextFieldName.gridy = 1;
		add(mTextFieldName, gbcTextFieldName);

		mLabel1 = new JLabel("other-type-definition");
		GridBagConstraints gbcLabel1 = new GridBagConstraints();
		gbcLabel1.anchor = GridBagConstraints.EAST;
		gbcLabel1.insets = new Insets(0, 0, 5, 5);
		gbcLabel1.gridx = 0;
		gbcLabel1.gridy = 2;
		add(mLabel1, gbcLabel1);

		mTextFieldOther = new JTextField();
		mTextFieldOther.setColumns(10);
		GridBagConstraints gbcTextFieldOther = new GridBagConstraints();
		gbcTextFieldOther.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldOther.insets = new Insets(0, 0, 5, 0);
		gbcTextFieldOther.gridx = 1;
		gbcTextFieldOther.gridy = 2;
		add(mTextFieldOther, gbcTextFieldOther);

		JLabel lblAdministrativedomain = new JLabel("administrative-domain");
		GridBagConstraints gbcLblAdministrativedomain = new GridBagConstraints();
		gbcLblAdministrativedomain.anchor = GridBagConstraints.EAST;
		gbcLblAdministrativedomain.insets = new Insets(0, 0, 0, 5);
		gbcLblAdministrativedomain.gridx = 0;
		gbcLblAdministrativedomain.gridy = 3;
		add(lblAdministrativedomain, gbcLblAdministrativedomain);

		mTextFieldDomain = new JTextField();
		GridBagConstraints gbcTextFieldDomain = new GridBagConstraints();
		gbcTextFieldDomain.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldDomain.gridx = 1;
		gbcTextFieldDomain.gridy = 3;
		add(mTextFieldDomain, gbcTextFieldDomain);
		mTextFieldDomain.setColumns(10);

	}

}
