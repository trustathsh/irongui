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

package de.hshannover.f4.trust.irongui.communication;

public class ConnectionParameter {

	ConnectionParameter() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return mName;
	}

	public void setName(String name) {
		this.mName = name;
	}

	public String getEndpoint() {
		return mEndpoint;
	}

	public void setEndpoint(String endpoint) {
		this.mEndpoint = endpoint;
	}

	public String getKeystore() {
		return mKeystore;
	}

	public void setKeystore(String keystore) {
		this.mKeystore = keystore;
	}

	public String getTruststore() {
		return mTruststore;
	}

	public void setTruststore(String truststore) {
		this.mTruststore = truststore;
	}

	public String getKeystorePass() {
		return mKeystorePass;
	}

	public void setKeystorePass(String keystorePass) {
		this.mKeystorePass = keystorePass;
	}

	public String getTruststorePass() {
		return mTruststorePass;
	}

	public void setTruststorePass(String truststorePass) {
		this.mTruststorePass = truststorePass;
	}

	public boolean isBasicAuthEnabled() {
		return mBasicAuthEnabled;
	}

	public void setBasicAuthEnabled(boolean basicAuthEnabled) {
		this.mBasicAuthEnabled = basicAuthEnabled;
	}

	public String getBasicauthUser() {
		return mBasicauthUser;
	}

	public void setBasicauthUser(String basicauthUser) {
		this.mBasicauthUser = basicauthUser;
	}

	public String getBasicauthPass() {
		return mBasicauthPass;
	}

	public void setBasicauthPass(String basicauthPass) {
		this.mBasicauthPass = basicauthPass;
	}

	public String getDumpFilter() {
		return mDumpFilter;
	}

	public void setDumpFilter(String dumpFilter) {
		this.mDumpFilter = dumpFilter;
	}

	public boolean isDump() {
		return mDump;
	}

	public boolean isAutoConnect() {
		return mAutoConnect;
	}

	public void setDump(boolean dump) {
		this.mDump = dump;
	}

	public void setAutoConnect(boolean autoConnect) {
		this.mAutoConnect = autoConnect;
	}

	public int getMaxPollSize() {
		return mMaxPollSize;
	}

	public void setMaxPollSize(int maxPollSize) {
		this.mMaxPollSize = maxPollSize;
	}

	@Override
	public ConnectionParameter clone() {
		ConnectionParameter param = new ConnectionParameter();
		param.setName(getName());
		param.setEndpoint(getEndpoint());
		param.setKeystore(getKeystore());
		param.setKeystorePass(getKeystorePass());
		param.setTruststore(getTruststore());
		param.setTruststorePass(getTruststorePass());
		param.setDump(isDump());
		param.setAutoConnect(isAutoConnect());
		param.setBasicAuthEnabled(isBasicAuthEnabled());
		param.setBasicauthUser(getBasicauthUser());
		param.setBasicauthPass(getBasicauthPass());
		param.setDumpFilter(getDumpFilter());
		param.setMaxPollSize(getMaxPollSize());
		return param;
	}

	@Override
	public String toString() {
		return mName;
	};

	private String mName;
	private String mEndpoint;
	private String mKeystore;
	private String mTruststore;
	private String mKeystorePass;
	private String mTruststorePass;
	private boolean mBasicAuthEnabled;
	private String mBasicauthUser;
	private String mBasicauthPass;
	private String mDumpFilter;
	private boolean mDump;
	private boolean mAutoConnect;
	private int mMaxPollSize;

}
