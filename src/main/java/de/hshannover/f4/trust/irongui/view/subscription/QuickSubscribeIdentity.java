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
 * This file is part of irongui, version 0.4.3,
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
	public JTextField textFieldDomain;	
	public final ButtonGroup type = new ButtonGroup();
	private JLabel label_1;
	public JTextField textFieldOther;
	private JLabel label;
	public JTextField textFieldName;
	private JPanel panel;
	private JRadioButton radioButton_9;
	private JRadioButton radioButton_8;
	private JRadioButton radioButton_7;
	private JRadioButton radioButton_6;
	private JRadioButton radioButton_5;
	private JRadioButton radioButton_4;
	private JRadioButton radioButton;
	private JRadioButton radioButton_3;
	private JRadioButton radioButton_1;
	private JRadioButton radioButton_2;

	/**
	 * Create the panel.
	 */
	public QuickSubscribeIdentity() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 155, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "type", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.gridwidth = 2;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		radioButton_9 = new JRadioButton("aik-name");
		type.add(radioButton_9);
		radioButton_9.setActionCommand("aik-name");
		GridBagConstraints gbc_radioButton_9 = new GridBagConstraints();
		gbc_radioButton_9.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_9.gridx = 0;
		gbc_radioButton_9.gridy = 0;
		panel.add(radioButton_9, gbc_radioButton_9);
		
		radioButton_8 = new JRadioButton("distinguished-name");
		type.add(radioButton_8);
		radioButton_8.setActionCommand("distinguished-name");
		GridBagConstraints gbc_radioButton_8 = new GridBagConstraints();
		gbc_radioButton_8.anchor = GridBagConstraints.WEST;
		gbc_radioButton_8.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_8.gridx = 1;
		gbc_radioButton_8.gridy = 0;
		panel.add(radioButton_8, gbc_radioButton_8);
		
		radioButton_7 = new JRadioButton("dns-name");
		type.add(radioButton_7);
		radioButton_7.setActionCommand("dns-name");
		GridBagConstraints gbc_radioButton_7 = new GridBagConstraints();
		gbc_radioButton_7.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_7.gridx = 0;
		gbc_radioButton_7.gridy = 1;
		panel.add(radioButton_7, gbc_radioButton_7);
		
		radioButton_6 = new JRadioButton("email-address");
		type.add(radioButton_6);
		radioButton_6.setActionCommand("email-address");
		GridBagConstraints gbc_radioButton_6 = new GridBagConstraints();
		gbc_radioButton_6.anchor = GridBagConstraints.WEST;
		gbc_radioButton_6.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_6.gridx = 1;
		gbc_radioButton_6.gridy = 1;
		panel.add(radioButton_6, gbc_radioButton_6);
		
		radioButton_5 = new JRadioButton("hip-tip");
		type.add(radioButton_5);
		radioButton_5.setActionCommand("hip-hit");
		GridBagConstraints gbc_radioButton_5 = new GridBagConstraints();
		gbc_radioButton_5.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton_5.gridx = 0;
		gbc_radioButton_5.gridy = 2;
		panel.add(radioButton_5, gbc_radioButton_5);
		
		radioButton_4 = new JRadioButton("kerberos-principal");
		type.add(radioButton_4);
		radioButton_4.setActionCommand("kerberos-principal");
		GridBagConstraints gbc_radioButton_4 = new GridBagConstraints();
		gbc_radioButton_4.anchor = GridBagConstraints.WEST;
		gbc_radioButton_4.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_4.gridx = 1;
		gbc_radioButton_4.gridy = 2;
		panel.add(radioButton_4, gbc_radioButton_4);
		
		radioButton = new JRadioButton("other");
		type.add(radioButton);
		radioButton.setActionCommand("other");
		GridBagConstraints gbc_radioButton = new GridBagConstraints();
		gbc_radioButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioButton.insets = new Insets(0, 0, 5, 5);
		gbc_radioButton.gridx = 0;
		gbc_radioButton.gridy = 3;
		panel.add(radioButton, gbc_radioButton);
		
		radioButton_3 = new JRadioButton("sip-uri");
		type.add(radioButton_3);
		radioButton_3.setActionCommand("sip-uri");
		GridBagConstraints gbc_radioButton_3 = new GridBagConstraints();
		gbc_radioButton_3.anchor = GridBagConstraints.WEST;
		gbc_radioButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_radioButton_3.gridx = 1;
		gbc_radioButton_3.gridy = 3;
		panel.add(radioButton_3, gbc_radioButton_3);
		
		radioButton_1 = new JRadioButton("tel-uri");
		type.add(radioButton_1);
		radioButton_1.setActionCommand("tel-uri");
		GridBagConstraints gbc_radioButton_1 = new GridBagConstraints();
		gbc_radioButton_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_radioButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_radioButton_1.gridx = 0;
		gbc_radioButton_1.gridy = 4;
		panel.add(radioButton_1, gbc_radioButton_1);
		
		radioButton_2 = new JRadioButton("username");
		type.add(radioButton_2);
		radioButton_2.setActionCommand("username");
		GridBagConstraints gbc_radioButton_2 = new GridBagConstraints();
		gbc_radioButton_2.anchor = GridBagConstraints.WEST;
		gbc_radioButton_2.gridx = 1;
		gbc_radioButton_2.gridy = 4;
		panel.add(radioButton_2, gbc_radioButton_2);
		
		label = new JLabel("name");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.anchor = GridBagConstraints.EAST;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 0;
		gbc_label.gridy = 1;
		add(label, gbc_label);
		
		textFieldName = new JTextField();
		textFieldName.setColumns(10);
		GridBagConstraints gbc_textFieldName = new GridBagConstraints();
		gbc_textFieldName.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldName.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldName.gridx = 1;
		gbc_textFieldName.gridy = 1;
		add(textFieldName, gbc_textFieldName);
		
		label_1 = new JLabel("other-type-definition");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.anchor = GridBagConstraints.EAST;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 0;
		gbc_label_1.gridy = 2;
		add(label_1, gbc_label_1);
		
		textFieldOther = new JTextField();
		textFieldOther.setColumns(10);
		GridBagConstraints gbc_textFieldOther = new GridBagConstraints();
		gbc_textFieldOther.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldOther.insets = new Insets(0, 0, 5, 0);
		gbc_textFieldOther.gridx = 1;
		gbc_textFieldOther.gridy = 2;
		add(textFieldOther, gbc_textFieldOther);
		
		JLabel lblAdministrativedomain = new JLabel("administrative-domain");
		GridBagConstraints gbc_lblAdministrativedomain = new GridBagConstraints();
		gbc_lblAdministrativedomain.anchor = GridBagConstraints.EAST;
		gbc_lblAdministrativedomain.insets = new Insets(0, 0, 0, 5);
		gbc_lblAdministrativedomain.gridx = 0;
		gbc_lblAdministrativedomain.gridy = 3;
		add(lblAdministrativedomain, gbc_lblAdministrativedomain);
		
		textFieldDomain = new JTextField();
		GridBagConstraints gbc_textFieldDomain = new GridBagConstraints();
		gbc_textFieldDomain.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldDomain.gridx = 1;
		gbc_textFieldDomain.gridy = 3;
		add(textFieldDomain, gbc_textFieldDomain);
		textFieldDomain.setColumns(10);

	}

}
