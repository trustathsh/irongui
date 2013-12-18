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
 * This file is part of irongui, version 0.4.2,
 * implemented by the Trust@HsH research group at the Hochschule Hannover.
 * %%
 * Copyright (C) 2010 - 2013 Trust@HsH
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
