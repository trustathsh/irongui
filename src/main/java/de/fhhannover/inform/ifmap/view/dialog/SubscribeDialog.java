package de.fhhannover.inform.ifmap.view.dialog;

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


import java.util.ArrayList;
import java.util.Observable;

import javax.swing.JButton;

import de.fhhannover.inform.trust.ifmapj.IfmapJ;
import de.fhhannover.inform.trust.ifmapj.identifier.Identifier;
import de.fhhannover.inform.trust.ifmapj.identifier.IdentifierFactory;
import de.fhhannover.inform.trust.ifmapj.identifier.Identity;
import de.fhhannover.inform.trust.ifmapj.identifier.IdentityType;

public class SubscribeDialog extends Observable {
	
	private Subscription_GUI mGui;
	public JButton buttonSubscribe;
	private Identifier mIdentifier;
	private IdentifierFactory mIdentifierFactory;
	
	public SubscribeDialog(){
		mGui = new Subscription_GUI();		
		mIdentifierFactory = IfmapJ.createIdentifierFactory();
		mGui.setCallback(this);
		buttonSubscribe = mGui.btnSubscribe;
	}
	
	public void setVisible(boolean b){
		mGui.setVisible(b);			
	}	
	
	private void setIdentifier(Identifier ident){
		mIdentifier = ident;
		setChanged();
	    notifyObservers();
	}
	
	public void update(){
		if(mGui.rdbtnIpaddress.isSelected()){
			String adr;
			Identifier ident;
			String admin = mGui.ipAddressPanel.textFieldAdmin.getText().trim();
			if(mGui.ipAddressPanel.ipRadioButtonV4.isSelected()){
				adr = mGui.ipAddressPanel.textFieldIp4.getText().trim();
				if(!adr.equals("")){					
					ident =  mIdentifierFactory.createIp4(adr, admin);					
					setIdentifier(ident);
				}
			}
			else {
				adr = mGui.ipAddressPanel.textFieldIp6.getText().trim();
				if(!adr.equals("")){					
					ident =  mIdentifierFactory.createIp6(adr, admin);					
					setIdentifier(ident);
				}
			}							
		}
		else if(mGui.rdbtnMacaddress.isSelected()){
			String mac_val = mGui.macaddresspanel.value.getText().trim();
			String mac_admin = mGui.macaddresspanel.admin.getText().trim();
			if(!mac_val.equals("")){
				Identifier ident =  mIdentifierFactory.createMac(mac_val, mac_admin);				
				setIdentifier(ident);
			}
		}
		else if(mGui.rdbtnAccessrequest.isSelected()){
			String ar_name = mGui.accessrequestpanel.name.getText().trim();
			String ar_admin = mGui.accessrequestpanel.admin.getText().trim();
			if(!ar_name.equals("")){
				Identifier ident =  mIdentifierFactory.createAr(ar_name, ar_admin);
				setIdentifier(ident);
			}
		}
		else if(mGui.rdbtnDevice.isSelected()){
			String d_name = mGui.devicepanel.name.getText().trim();
			//String d_aik = mGui.devicepanel.aik.getText().trim();
			// AIK is deprecated
			if(!d_name.equals("")){
				Identifier ident =  mIdentifierFactory.createDev(d_name);				
				setIdentifier(ident);
			}
		}
		else if(mGui.rdbtnIdentity.isSelected()){
			String id_name = mGui.identitypanel.name.getText().trim();			
			if(!id_name.equals("")){
				IdentityType type = (IdentityType)mGui.identitypanel.type.getSelectedItem();
				//System.err.println(type.toString());
				String admin = mGui.identitypanel.admindomain.getText().trim();
				Identity ident;
				if(type == IdentityType.other) {
					ident =  mIdentifierFactory.createIdentity(id_name, admin, 
							mGui.identitypanel.othertypedefinition.getText().trim());
				}
				else {
					ident =  mIdentifierFactory.createIdentity(type, id_name, admin);
				}
				
				setIdentifier(ident);
			}
		}
	}
	
	public Identifier getIdentifier(){
		return mIdentifier;
	}
	
	public int getDepth(){
		int l = 0;
		try{
			l = Integer.parseInt(String.valueOf(mGui.jSpinner1.getValue()));
		}
		catch(NumberFormatException err){
			return l;
		}
		return l;
	}
	
	public int getSize(){
		int l = 0;
		try{
			l = Integer.parseInt(String.valueOf(mGui.jSpinner2.getValue()));
		}
		catch(NumberFormatException err){
			return l;
		}
		return l;
	}
	
	public String getFilter(){
		return mGui.jTextField1.getText();
	}
	
	public String getLinks(){
		return mGui.jTextField2.getText();
	}
	
	public String[] getTerminals(){
		ArrayList<String> arr = new ArrayList<String>();
		
		if(mGui.radioButton.isSelected())
			arr.add(mGui.radioButton.getText());
		if(mGui.radioButton_1.isSelected())
			arr.add(mGui.radioButton_1.getText());
		if(mGui.radioButton_2.isSelected())
			arr.add(mGui.radioButton_2.getText());
		if(mGui.radioButton_3.isSelected())
			arr.add(mGui.radioButton_3.getText());
		if(mGui.radioButton_4.isSelected())
			arr.add(mGui.radioButton_4.getText());				
		
		String[] arrString = new String[arr.size()];
		
		for(int i = 0; i < arr.size(); i++){
			arrString[i] = arr.get(i);
		}
		arr = null;
		return arrString;		
	}
	
	public void center(){
		mGui.setLocationRelativeTo(null);
	}
	
}
