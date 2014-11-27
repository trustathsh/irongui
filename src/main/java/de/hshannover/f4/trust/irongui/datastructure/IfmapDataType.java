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
 * This file is part of irongui, version 0.4.6,
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



package de.hshannover.f4.trust.irongui.datastructure;




import java.util.ArrayList;

public abstract class IfmapDataType {

	private ArrayList<Metadata> mMetadata;
	private String mSubscriptionName;

	public IfmapDataType() {
		mMetadata = new ArrayList<Metadata>();
	}

	public void addOrReplaceMetadata(Metadata m) {
		if (mMetadata.contains(m)) {
			Metadata oldMeta = mMetadata.get(mMetadata.indexOf(m));
			if (oldMeta.getTimestamp().equals(m.getTimestamp())) {
				return;
			}
		}
		for (int i = 0; i < mMetadata.size(); i++) {
			Metadata meta = mMetadata.get(i);
			if (meta.getName().equalsIgnoreCase(m.getName())) {
				if (m.isSingleValue()) {
					mMetadata.remove(meta);
				}
			}
		}

		mMetadata.add(m);
	}

	public boolean remove(Metadata meta) {
		return mMetadata.remove(meta);
	}

	public ArrayList<Metadata> getMetadataAsCopy() {
		// TODO make a real copy here
		return this.getMetadata();
	}

	public ArrayList<Metadata> getMetadata() {
		return mMetadata;
	}
	
	public String getSubscriptionName() {
		return mSubscriptionName;
	}

	public void setSubscriptionName(String mSubscriptionName) {
		this.mSubscriptionName = mSubscriptionName;
	}

	public abstract boolean equals(Object o);

	public abstract int hashCode();
	// public abstract E clone();

}
