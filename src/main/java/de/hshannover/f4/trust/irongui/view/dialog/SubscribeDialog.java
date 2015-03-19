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

package de.hshannover.f4.trust.irongui.view.dialog;

import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;

import de.hshannover.f4.trust.ifmapj.IfmapJ;
import de.hshannover.f4.trust.ifmapj.identifier.Identifier;
import de.hshannover.f4.trust.ifmapj.identifier.IdentifierFactory;
import de.hshannover.f4.trust.ifmapj.identifier.Identity;
import de.hshannover.f4.trust.ifmapj.identifier.IdentityType;

public class SubscribeDialog extends Observable {

	private Subscription_GUI mGui;
	public JButton mButtonSubscribe;
	private Identifier mIdentifier;
	private IdentifierFactory mIdentifierFactory;

	public SubscribeDialog() {
		mGui = new Subscription_GUI();
		mIdentifierFactory = IfmapJ.createIdentifierFactory();
		mGui.setCallback(this);
		mButtonSubscribe = mGui.btnSubscribe;
	}

	public void setVisible(boolean b) {
		mGui.setVisible(b);
	}

	private void setIdentifier(Identifier ident) {
		mIdentifier = ident;
		setChanged();
		notifyObservers();
	}

	public void update() {
		if (mGui.mRdbtnIpaddress.isSelected()) {
			String adr;
			Identifier ident;
			String admin = mGui.mIpAddressPanel.mTextFieldAdmin.getText().trim();
			if (mGui.mIpAddressPanel.mIpRadioButtonV4.isSelected()) {
				adr = mGui.mIpAddressPanel.mTextFieldIp4.getText().trim();
				if (!adr.equals("")) {
					ident = mIdentifierFactory.createIp4(adr, admin);
					setIdentifier(ident);
				}
			} else {
				adr = mGui.mIpAddressPanel.mTextFieldIp6.getText().trim();
				if (!adr.equals("")) {
					ident = mIdentifierFactory.createIp6(adr, admin);
					setIdentifier(ident);
				}
			}
		} else if (mGui.mRdbtnMacaddress.isSelected()) {
			String macVal = mGui.mMacaddresspanel.mValue.getText().trim();
			String macAdmin = mGui.mMacaddresspanel.mAdmin.getText().trim();
			if (!macVal.equals("")) {
				Identifier ident = mIdentifierFactory.createMac(macVal,
						macAdmin);
				setIdentifier(ident);
			}
		} else if (mGui.rdbtnAccessrequest.isSelected()) {
			String arName = mGui.mAccessrequestpanel.mName.getText().trim();
			String arAdmin = mGui.mAccessrequestpanel.mAdmin.getText().trim();
			if (!arName.equals("")) {
				Identifier ident = mIdentifierFactory.createAr(arName,
						arAdmin);
				setIdentifier(ident);
			}
		} else if (mGui.rdbtnDevice.isSelected()) {
			String dName = mGui.mDevicepanel.mName.getText().trim();
			// String d_aik = mGui.devicepanel.aik.getText().trim();
			// AIK is deprecated
			if (!dName.equals("")) {
				Identifier ident = mIdentifierFactory.createDev(dName);
				setIdentifier(ident);
			}
		} else if (mGui.rdbtnIdentity.isSelected()) {
			String idName = mGui.mIdentitypanel.mName.getText().trim();
			if (!idName.equals("")) {
				IdentityType type = (IdentityType) mGui.mIdentitypanel.mType
						.getSelectedItem();
				// System.err.println(type.toString());
				String admin = mGui.mIdentitypanel.mAdmindomain.getText().trim();
				Identity ident;
				if (type == IdentityType.other) {
					ident = mIdentifierFactory.createIdentity(idName, admin,
							mGui.mIdentitypanel.mOthertypedefinition.getText()
									.trim());
				} else {
					ident = mIdentifierFactory.createIdentity(type, idName,
							admin);
				}

				setIdentifier(ident);
			}
		}
	}

	public Identifier getIdentifier() {
		return mIdentifier;
	}

	public int getDepth() {
		int l = 0;
		try {
			l = Integer.parseInt(String.valueOf(mGui.mJSpinner1.getValue()));
		} catch (NumberFormatException err) {
			return l;
		}
		return l;
	}

	public int getSize() {
		int l = 0;
		try {
			l = Integer.parseInt(String.valueOf(mGui.mJSpinner2.getValue()));
		} catch (NumberFormatException err) {
			return l;
		}
		return l;
	}

	public String getFilter() {
		return mGui.mJTextField1.getText();
	}

	public String getLinks() {
		return mGui.mJTextField2.getText();
	}

	public String[] getTerminals() {
		ArrayList<String> arr = new ArrayList<String>();

		if (mGui.radioButton.isSelected()) {
			arr.add(mGui.radioButton.getText());
		}
		if (mGui.radioButton_1.isSelected()) {
			arr.add(mGui.radioButton_1.getText());
		}
		if (mGui.radioButton_2.isSelected()) {
			arr.add(mGui.radioButton_2.getText());
		}
		if (mGui.radioButton_3.isSelected()) {
			arr.add(mGui.radioButton_3.getText());
		}
		if (mGui.radioButton_4.isSelected()) {
			arr.add(mGui.radioButton_4.getText());
		}

		String[] arrString = new String[arr.size()];

		for (int i = 0; i < arr.size(); i++) {
			arrString[i] = arr.get(i);
		}
		arr = null;
		return arrString;
	}

	public void center() {
		mGui.setLocationRelativeTo(null);
	}

}
