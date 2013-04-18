package de.fhhannover.inform.ifmap;

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


import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.fhhannover.inform.ifmap.communication.ConnectionRepository;
import de.fhhannover.inform.ifmap.control.IfmapFacade;
import de.fhhannover.inform.ifmap.properties.PropertiesRepository;
import de.fhhannover.inform.ifmap.properties.PublisherRepository;
import de.fhhannover.inform.ifmap.properties.SynchronisationService;
import de.fhhannover.inform.ifmap.view.ViewController;

public class Client {

	private final ViewController mViewController;
	private final IfmapFacade mIfmapFacade;
	
	public Client() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("PopupMenu.consumeEventOnClose", Boolean.TRUE);
			//UIManager.put("Button.showMnemonics", Boolean.TRUE);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PropertiesRepository mPropertiesRep = new PropertiesRepository();
		ConnectionRepository mConnectionRep = new ConnectionRepository();
		PublisherRepository mPublisherRep = new PublisherRepository();
		SynchronisationService mSyncService = new SynchronisationService(mPropertiesRep, 
				mConnectionRep, mPublisherRep);
		mSyncService.init();
		mIfmapFacade = new IfmapFacade(mSyncService);
		mViewController = new ViewController(mIfmapFacade);		
	}
	
	public void start(){		
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				mViewController.showMainFrame();
			}
		});
	}
	
	public static void main(String[] args) {
		Client client = new Client();
		client.start();
	}
}
