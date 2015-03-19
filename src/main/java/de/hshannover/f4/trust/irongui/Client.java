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

package de.hshannover.f4.trust.irongui;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import de.hshannover.f4.trust.irongui.communication.ConnectionRepository;
import de.hshannover.f4.trust.irongui.control.IfmapFacade;
import de.hshannover.f4.trust.irongui.properties.PropertiesRepository;
import de.hshannover.f4.trust.irongui.properties.PublisherRepository;
import de.hshannover.f4.trust.irongui.properties.SynchronisationService;
import de.hshannover.f4.trust.irongui.view.ViewController;

public class Client {

	public static final String VERSION = "${project.version}";

	private final ViewController mViewController;
	private final IfmapFacade mIfmapFacade;

	public Client() {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			UIManager.put("PopupMenu.consumeEventOnClose", Boolean.TRUE);
			// UIManager.put("Button.showMnemonics", Boolean.TRUE);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		PropertiesRepository mPropertiesRep = new PropertiesRepository();
		ConnectionRepository mConnectionRep = new ConnectionRepository();
		PublisherRepository mPublisherRep = new PublisherRepository();
		SynchronisationService mSyncService = new SynchronisationService(
				mPropertiesRep, mConnectionRep, mPublisherRep);
		mSyncService.init();
		mIfmapFacade = new IfmapFacade(mSyncService);
		mViewController = new ViewController(mIfmapFacade);
	}

	public void start() {
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
