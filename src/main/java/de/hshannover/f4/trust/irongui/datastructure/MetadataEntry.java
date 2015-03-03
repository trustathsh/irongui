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

package de.hshannover.f4.trust.irongui.datastructure;

import java.util.HashMap;

public class MetadataEntry {
	private HashMap<String, String> mAttributes;
	private String mName;
	private String mValue;

	public MetadataEntry(String name, String value) {
		mName = name;
		mValue = value;
	}

	public void addAttribute(String key, String value) {
		if (mAttributes == null) {
			mAttributes = new HashMap<String, String>();
		}
		mAttributes.put(key, value);
	}

	public HashMap<String, String> getAttributes() {
		return mAttributes;
	}

	public boolean hasAttributes() {
		return (mAttributes == null || mAttributes.size() == 0) ? false : true;
	}

	public String getName() {
		return mName;
	}

	public String getValue() {
		return mValue;
	}

	@Override
	public String toString() {
		return mName + ":" + mValue;
	}

}
