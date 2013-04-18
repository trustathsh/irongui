package de.fhhannover.inform.ifmap.util;

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
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

import de.fhhannover.inform.ifmap.datastructure.Db4oStorageImpl;
import de.fhhannover.inform.ifmap.datastructure.IdentifierData;
import de.fhhannover.inform.ifmap.datastructure.IfmapDataType;
import de.fhhannover.inform.ifmap.datastructure.Link;
import de.fhhannover.inform.ifmap.datastructure.Metadata;
import de.fhhannover.inform.ifmap.datastructure.MetadataEntry;
import de.fhhannover.inform.ifmap.exception.LinkConstructionException;
import de.fhhannover.inform.trust.Category;
import de.fhhannover.inform.trust.Feature;

public class IfmapUtils {
	private static Hashtable<String, Category> cats = new Hashtable<String, Category>();

	public static ArrayList<IfmapDataType> dbo2Ifmap(String file) {
		ArrayList<IfmapDataType> nodes = new ArrayList<IfmapDataType>();
		Db4oStorageImpl db4o = new Db4oStorageImpl(file);
        List<Feature> features = db4o.query(Feature.class);
        List<Category> categories = db4o.query(Category.class);
        for(Category c : categories) {
        	if(!cats.containsKey(c.getId())) {
        		IdentifierData parent = new IdentifierData(c.getId());
        		for(Category sub : c.getSubCategories()) {
        			IdentifierData identifier = new IdentifierData(sub.getId());
        			Link l;
					try {
						l = new Link("subCategory", parent, identifier);
						Metadata meta = new Metadata("subCategory");
						meta.addElement(new MetadataEntry("subCategory", ""));
						l.addOrReplaceMetadata(meta);
						nodes.add(l);
					} catch (LinkConstructionException e) {
						e.printStackTrace();
					}	
        		}	
        		cats.put(c.getId(), c);
        	}
        }
		return nodes;
	}
	
	private static List<IdentifierData> getCats(Category c) {
		List<IdentifierData> list = new ArrayList<IdentifierData>();
		
		return list;
	}
}
