package de.fhhannover.inform.ifmap.datastructure;

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
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.ObjectSet;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.config.UuidSupport;

import de.fhhannover.inform.trust.Feature;

/**
 * 
 * @author Tobias
 */
public class Db4oStorageImpl {

	private String mPath = "./features.db4o";
	private ObjectContainer mDb4o;

	public Db4oStorageImpl(String path) {
		if (path != null) {
			mPath = path;
		}
	}

	private EmbeddedConfiguration config() {
		EmbeddedConfiguration conf = Db4oEmbedded.newConfiguration();
		conf.common().updateDepth(10);
		conf.common().add(new UuidSupport());
		return conf;
	}		

	private ObjectContainer open(String path) {
		return Db4oEmbedded.openFile(config(), path);
	}	

	public <E> List<E> query(Class<E> c) {
		ObjectSet<E> result;
		mDb4o = open(mPath);
		result = mDb4o.queryByExample(c);
		List<E> arr = new ArrayList<E>();
		if (result != null && !result.isEmpty()) {
			while (result.hasNext()) {
				arr.add(result.next());
			}
		}
		mDb4o.close();
		return arr;
	}

	public void close() {
		mDb4o.close();
	}
}
