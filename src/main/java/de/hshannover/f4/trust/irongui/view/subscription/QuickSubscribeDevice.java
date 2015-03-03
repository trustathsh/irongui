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

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class QuickSubscribeDevice extends JPanel {
	private static final long serialVersionUID = 5529174098936914L;
	public JTextField mTextFieldName;
	private JPanel mPanel;

	/**
	 * Create the panel.
	 */
	public QuickSubscribeDevice() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		setLayout(new BorderLayout(5, 5));

		mPanel = new JPanel();
		add(mPanel, BorderLayout.CENTER);
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[] { 69, 136 };

		gblPanel.columnWeights = new double[] { 0.0, 0.0 };
		gblPanel.rowWeights = new double[] { 0.0 };
		mPanel.setLayout(gblPanel);

		JLabel lblName = new JLabel("name");
		GridBagConstraints gbcLblName = new GridBagConstraints();
		gbcLblName.anchor = GridBagConstraints.EAST;
		gbcLblName.insets = new Insets(0, 0, 0, 5);
		gbcLblName.gridx = 0;
		gbcLblName.gridy = 0;
		mPanel.add(lblName, gbcLblName);

		mTextFieldName = new JTextField();
		GridBagConstraints gbcTextFieldName = new GridBagConstraints();
		gbcTextFieldName.anchor = GridBagConstraints.WEST;
		gbcTextFieldName.gridx = 1;
		gbcTextFieldName.gridy = 0;
		mPanel.add(mTextFieldName, gbcTextFieldName);
		mTextFieldName.setColumns(10);

	}

}
