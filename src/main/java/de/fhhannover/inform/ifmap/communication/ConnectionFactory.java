package de.fhhannover.inform.ifmap.communication;

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


import de.fhhannover.inform.ifmap.exception.ConnectionCreationException;

public class ConnectionFactory {

	public static Connection createConnection(ConnectionParameter params)
			throws ConnectionCreationException {
		if (params == null) {
			throw new ConnectionCreationException("params == null");
		}
		ConnectionParameter tmp = createConnectionParameter(params.getName(),
				params.getEndpoint(), params.getKeystore(),
				params.getKeystorePass(), params.getTruststore(),
				params.getTruststorePass(), params.isBasicAuthEnabled(),
				params.getBasicauthUser(), params.getBasicauthPass(),
				params.isAutoSubscribe(), params.getMaxPollSize());
		return new Connection(tmp);
	}

	public static ConnectionParameter createConnectionParameter(String name)
			throws ConnectionCreationException {
		ConnectionParameter params = new ConnectionParameter();
		params.setName(name);
		return params;
	}

	public static ConnectionParameter createConnectionParameter(String name,
			String url, String keyPath, String keyPass, String trustPath,
			String trustPass, boolean enableBasicAuth, String basicUser,
			String basicPass, boolean autoSubscribe, int maxPoll)
			throws ConnectionCreationException {
		/**
		 * @TODO do checks here (null, path, passwords)
		 */
		ConnectionParameter params = new ConnectionParameter();
		params.setName(name);
		params.setEndpoint(url);
		params.setKeystore(keyPath);
		params.setKeystorePass(keyPass);
		params.setTruststore(trustPath);
		params.setTruststorePass(trustPass);
		params.setBasicAuthEnabled(enableBasicAuth);
		params.setBasicauthUser(basicUser);
		params.setBasicauthPass(basicPass);
		params.setAutoSubscribe(autoSubscribe);
		params.setMaxPollSize(maxPoll);
		return params;
	}

}
