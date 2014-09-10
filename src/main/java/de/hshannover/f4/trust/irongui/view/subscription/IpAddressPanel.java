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
 * This file is part of irongui, version 0.4.4,
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
public class IpAddressPanel extends javax.swing.JPanel {
	private static final long serialVersionUID = -1460817304520223857L;
	public JRadioButton ipRadioButtonV4;
	public JRadioButton ipRadioButtonV6;
	public JTextField textFieldIp4;
	public JTextField textFieldIp6;
	public JTextField textFieldAdmin;
	private ButtonGroup buttonGroup1;	
	private JLabel lblAdministrativedomain;
	
	
	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
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
		initGUI();
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			thisLayout.rowWeights = new double[] {0.0, 0.0, 0.0};
			thisLayout.rowHeights = new int[] {7, 7, 0};
			thisLayout.columnWeights = new double[] {0.0, 1.0};
			thisLayout.columnWidths = new int[] {-1, 183};
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(373, 109));
			{
				ipRadioButtonV4 = new JRadioButton();
				this.add(ipRadioButtonV4, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
				ipRadioButtonV4.setText("IPv4");				
				ipRadioButtonV4.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						textFieldIp4.setEnabled(true);
						textFieldIp6.setEnabled(false);						
					}
				});				
				getButtonGroup1().add(ipRadioButtonV4);				
			}
			{
				ipRadioButtonV6 = new JRadioButton();
				ipRadioButtonV6.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						textFieldIp4.setEnabled(false);
						textFieldIp6.setEnabled(true);											
					}
				});
				this.add(ipRadioButtonV6, new GridBagConstraints(0, 1, 1, 1, 0.0, 0.0, GridBagConstraints.EAST, GridBagConstraints.NONE, new Insets(0, 0, 5, 5), 0, 0));
				ipRadioButtonV6.setText("IPv6");
				getButtonGroup1().add(ipRadioButtonV6);
			}
			{
				textFieldIp4 = new JTextField();				
				this.add(textFieldIp4, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
				textFieldIp4.addMouseListener(new MouseAdapter() {
					public void mouseExited(MouseEvent evt) {
						textFieldIp4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));						
					}
					public void mouseEntered(MouseEvent evt) {
						textFieldIp4.setCursor(new Cursor(Cursor.TEXT_CURSOR));						
					}
				});
			}
			{
				textFieldIp6 = new JTextField();
				this.add(textFieldIp6, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 5, 0), 0, 0));
			}
			{
				lblAdministrativedomain = new JLabel("administrative-domain");
				GridBagConstraints gbc_lblAdministrativedomain = new GridBagConstraints();
				gbc_lblAdministrativedomain.anchor = GridBagConstraints.EAST;
				gbc_lblAdministrativedomain.insets = new Insets(0, 0, 0, 5);
				gbc_lblAdministrativedomain.gridx = 0;
				gbc_lblAdministrativedomain.gridy = 2;
				add(lblAdministrativedomain, gbc_lblAdministrativedomain);
			}
			{
				textFieldAdmin = new JTextField();
				GridBagConstraints gbc_textFieldAdmin = new GridBagConstraints();
				gbc_textFieldAdmin.fill = GridBagConstraints.HORIZONTAL;
				gbc_textFieldAdmin.gridx = 1;
				gbc_textFieldAdmin.gridy = 2;
				add(textFieldAdmin, gbc_textFieldAdmin);
				textFieldAdmin.setColumns(10);
			}
			ipRadioButtonV4.doClick();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ButtonGroup getButtonGroup1() {
		if(buttonGroup1 == null) {
			buttonGroup1 = new ButtonGroup();
		}
		return buttonGroup1;
	}

}
