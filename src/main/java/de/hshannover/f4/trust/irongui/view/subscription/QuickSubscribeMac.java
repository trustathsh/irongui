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

public class QuickSubscribeMac extends JPanel {

	private static final long serialVersionUID = -5421064893157178828L;
	public JTextField mTextFieldDomain;
	public JTextField mTextFieldValue;
	private JPanel mPanel;

	/**
	 * Create the panel.
	 */
	public QuickSubscribeMac() {
		setBorder(new MatteBorder(1, 1, 1, 1, (Color) null));
		setLayout(new BorderLayout(5, 5));

		mPanel = new JPanel();
		add(mPanel, BorderLayout.CENTER);
		GridBagLayout gblPanel = new GridBagLayout();
		gblPanel.columnWidths = new int[] {119, 127, 58, 0 };
		gblPanel.rowHeights = new int[] {22, 0, 0 };
		gblPanel.columnWeights = new double[] {0.0, 0.0, 0.0,
				Double.MIN_VALUE };
		gblPanel.rowWeights = new double[] {0.0, 0.0, Double.MIN_VALUE };
		mPanel.setLayout(gblPanel);

		JLabel lblValue = new JLabel("value");
		GridBagConstraints gbcLblValue = new GridBagConstraints();
		gbcLblValue.insets = new Insets(0, 0, 5, 5);
		gbcLblValue.anchor = GridBagConstraints.EAST;
		gbcLblValue.gridx = 0;
		gbcLblValue.gridy = 0;
		mPanel.add(lblValue, gbcLblValue);

		mTextFieldValue = new JTextField();
		GridBagConstraints gbcTextFieldValue = new GridBagConstraints();
		gbcTextFieldValue.gridwidth = 2;
		gbcTextFieldValue.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldValue.insets = new Insets(0, 0, 5, 5);
		gbcTextFieldValue.gridx = 1;
		gbcTextFieldValue.gridy = 0;
		mPanel.add(mTextFieldValue, gbcTextFieldValue);
		mTextFieldValue.setColumns(10);

		JLabel lblAdministrativedomain = new JLabel("administrative-domain");
		GridBagConstraints gbcLblAdministrativedomain = new GridBagConstraints();
		gbcLblAdministrativedomain.anchor = GridBagConstraints.EAST;
		gbcLblAdministrativedomain.insets = new Insets(0, 0, 0, 5);
		gbcLblAdministrativedomain.gridx = 0;
		gbcLblAdministrativedomain.gridy = 1;
		mPanel.add(lblAdministrativedomain, gbcLblAdministrativedomain);

		mTextFieldDomain = new JTextField();
		GridBagConstraints gbcTextFieldDomain = new GridBagConstraints();
		gbcTextFieldDomain.gridwidth = 2;
		gbcTextFieldDomain.insets = new Insets(0, 0, 0, 5);
		gbcTextFieldDomain.fill = GridBagConstraints.HORIZONTAL;
		gbcTextFieldDomain.gridx = 1;
		gbcTextFieldDomain.gridy = 1;
		mPanel.add(mTextFieldDomain, gbcTextFieldDomain);
		mTextFieldDomain.setColumns(10);

	}

}
