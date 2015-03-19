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

package de.hshannover.f4.trust.irongui.properties;

import java.awt.Color;
import java.util.HashMap;

public class PublisherRepository {

	private HashMap<String, Color> mPublishers = new HashMap<String, Color>();
	private Color mIdentifierColor = new Color(153, 153, 255);
	private Color mMetadataColor = new Color(255, 153, 102);

	public synchronized void add(String publisher, Color color) {
		if (publisher != null && color != null) {
			mPublishers.put(publisher, color);
		}
	}

	public synchronized void add(HashMap<String, Color> publisher) {
		if (publisher != null) {
			for (String key : publisher.keySet()) {
				mPublishers.put(key, publisher.get(key));
			}
		}
	}

	public synchronized void clear() {
		mPublishers.clear();
	}

	public synchronized void remove(String publisher) {
		mPublishers.remove(publisher);
	}

	public synchronized Color getColor(String publisher) {
		return mPublishers.get(publisher);
	}

	public synchronized HashMap<String, Color> getPublisherAndColor() {
		return this.mPublishers;
	}

	public synchronized String[] getPublisher() {
		int length = mPublishers.size();
		String[] pubs = null;
		if (length > 0) {
			pubs = new String[length];
			int i = 0;
			for (String p : mPublishers.keySet()) {
				pubs[i++] = p;
			}
		}
		return pubs;
	}

	public synchronized Color getIdentifierColor() {
		return mIdentifierColor;
	}

	public synchronized void setIdentifierColor(Color mIdentifierColor) {
		this.mIdentifierColor = mIdentifierColor;
	}

	public synchronized Color getMetadataColor() {
		return mMetadataColor;
	}

	public synchronized void setMetadataColor(Color mMetadataColor) {
		this.mMetadataColor = mMetadataColor;
	}
}
