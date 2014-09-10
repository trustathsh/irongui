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



package de.hshannover.f4.trust.irongui.event;




import java.util.ArrayList;

import de.hshannover.f4.trust.irongui.communication.Connection;
import de.hshannover.f4.trust.irongui.datastructure.PollResultContainer;

public class EventService {

	private ArrayList<StatusChangedReceiver> mStatusReceiver = new ArrayList<StatusChangedReceiver>();
	private ArrayList<IdentifierChangedReceiver> mIdentifierReceiver = new ArrayList<IdentifierChangedReceiver>();

	public void addStatusListener(StatusChangedReceiver r) {
		if (!mStatusReceiver.contains(r))
			mStatusReceiver.add(r);
	}

	public void addUpdateListener(IdentifierChangedReceiver r) {
		if (!mIdentifierReceiver.contains(r))
			mIdentifierReceiver.add(r);
	}

	public synchronized void notifyRepositoryChanged(Connection con, PollResultContainer prc) {
		if (prc != null) {
			for (IdentifierChangedReceiver r : mIdentifierReceiver) {
				r.processNewPollResult(con, prc.getNew(), prc.getUpdate(),
						prc.getDelete());
			}
		}
	}

	public synchronized void notifyStatusChangedReceiver(Connection con, String evt) {
		if (evt != null) {
			for (StatusChangedReceiver r : mStatusReceiver) {
				r.processStatusEvent(con, evt);
			}
		}
	}

}
