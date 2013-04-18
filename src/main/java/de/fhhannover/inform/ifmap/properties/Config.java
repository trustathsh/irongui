package de.fhhannover.inform.ifmap.properties;

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


public enum Config {
	NAMESPACE_DEFAULT("irongui.default"),
	NAMESPACE_USER_DEFAULT("irongui.user"),
	COLOR_IDENTIFIER_DEFAULT(NAMESPACE_DEFAULT+".color.identifier"),
	COLOR_METADATA_DEFAULT(NAMESPACE_DEFAULT+".color.metadata"),	
	USER_CONNECTIONS_COUNT(NAMESPACE_USER_DEFAULT+".connectioncount"),
	USER_PUBLISHER_COUNT(NAMESPACE_USER_DEFAULT+".publishercount"),
	USER_CONNECTIONS_PREFIX(NAMESPACE_USER_DEFAULT+".connection."),	
	USER_PUBLISHER_PREFIX(NAMESPACE_DEFAULT+".publisher."),
	USER_PUBLISHER_NAME(".name"),
	USER_PUBLISHER_COLOR(".color"),
	USER_CONNECTIONS_NAME("name"),
	USER_CONNECTIONS_URL("url"),
	USER_CONNECTIONS_KEYSTORE("keystore"),
	USER_CONNECTIONS_KEYSTORE_PASSWORD("keystorepass"),
	USER_CONNECTIONS_TRUSTSTORE("truststore"),
	USER_CONNECTIONS_TRUSTSTORE_PASSWORD("truststorepass"),
	USER_CONNECTIONS_BASICAUTH_ENABLED("basicauth.enabled"),
	USER_CONNECTIONS_BASICAUTH_USER("basicauth.user"),
	USER_CONNECTIONS_BASICAUTH_PASS("basicauth.pass"),
	USER_CONNECTIONS_AUTO_SUBSCRIBE("dumping.enabled"),
	USER_CONNECTIONS_MAX_POLL_SIZE("maxpoll");
	
	private String value;
	
	private Config(String v){
		value = v;
	}
	
	@Override
	public String toString() {		
		return value;
	}
}
