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
 * This file is part of irongui, version 0.4.8,
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

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

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
public class IpAddressPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = -1460817304520223857L;
	public JRadioButton mIpRadioButtonV4;
	public JRadioButton mIpRadioButtonV6;
	public JTextField mTextFieldIp4;
	public JTextField mTextFieldIp6;
	public JTextField mTextFieldAdmin;
	private ButtonGroup mButtonGroup1;
	private JLabel mLblAdministrativedomain;

	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new IpAddressPanel());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public IpAddressPanel() {
		super();
		initGui();
	}

	private void initGui() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0 };
			thisLayout.rowHeights = new int[] {7, 7, 0 };
			thisLayout.columnWeights = new double[] {0.0, 1.0 };
			thisLayout.columnWidths = new int[] {-1, 183 };
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(373, 109));
			{
				mIpRadioButtonV4 = new JRadioButton();
				this.add(mIpRadioButtonV4, new GridBagConstraints(0, 0, 1, 1,
						0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
				mIpRadioButtonV4.setText("IPv4");
				mIpRadioButtonV4.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						mTextFieldIp4.setEnabled(true);
						mTextFieldIp6.setEnabled(false);
					}
				});
				getButtonGroup1().add(mIpRadioButtonV4);
			}
			{
				mIpRadioButtonV6 = new JRadioButton();
				mIpRadioButtonV6.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						mTextFieldIp4.setEnabled(false);
						mTextFieldIp6.setEnabled(true);
					}
				});
				this.add(mIpRadioButtonV6, new GridBagConstraints(0, 1, 1, 1,
						0.0, 0.0, GridBagConstraints.EAST,
						GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
				mIpRadioButtonV6.setText("IPv6");
				getButtonGroup1().add(mIpRadioButtonV6);
			}
			{
				mTextFieldIp4 = new JTextField();
				this.add(mTextFieldIp4, new GridBagConstraints(1, 0, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0),
						0, 0));
				mTextFieldIp4.addMouseListener(new MouseAdapter() {
					public void mouseExited(MouseEvent evt) {
						mTextFieldIp4
								.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

					public void mouseEntered(MouseEvent evt) {
						mTextFieldIp4.setCursor(new Cursor(Cursor.TEXT_CURSOR));
					}
				});
			}
			{
				mTextFieldIp6 = new JTextField();
				this.add(mTextFieldIp6, new GridBagConstraints(1, 1, 1, 1, 0.0,
						0.0, GridBagConstraints.CENTER,
						GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0),
						0, 0));
			}
			{
				mLblAdministrativedomain = new JLabel("administrative-domain");
				GridBagConstraints gbcLblAdministrativedomain = new GridBagConstraints();
				gbcLblAdministrativedomain.anchor = GridBagConstraints.EAST;
				gbcLblAdministrativedomain.insets = new Insets(0, 0, 0, 5);
				gbcLblAdministrativedomain.gridx = 0;
				gbcLblAdministrativedomain.gridy = 2;
				add(mLblAdministrativedomain, gbcLblAdministrativedomain);
			}
			{
				mTextFieldAdmin = new JTextField();
				GridBagConstraints gbcTextFieldAdmin = new GridBagConstraints();
				gbcTextFieldAdmin.fill = GridBagConstraints.HORIZONTAL;
				gbcTextFieldAdmin.gridx = 1;
				gbcTextFieldAdmin.gridy = 2;
				add(mTextFieldAdmin, gbcTextFieldAdmin);
				mTextFieldAdmin.setColumns(10);
			}
			mIpRadioButtonV4.doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private ButtonGroup getButtonGroup1() {
		if (mButtonGroup1 == null) {
			mButtonGroup1 = new ButtonGroup();
		}
		return mButtonGroup1;
	}

}
