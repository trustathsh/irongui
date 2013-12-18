package de.hshannover.f4.trust.irongui.communication;




public class ConnectionParameter {

	ConnectionParameter() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getKeystore() {
		return keystore;
	}

	public void setKeystore(String keystore) {
		this.keystore = keystore;
	}

	public String getTruststore() {
		return truststore;
	}

	public void setTruststore(String truststore) {
		this.truststore = truststore;
	}

	public String getKeystorePass() {
		return keystorePass;
	}

	public void setKeystorePass(String keystorePass) {
		this.keystorePass = keystorePass;
	}

	public String getTruststorePass() {
		return truststorePass;
	}

	public void setTruststorePass(String truststorePass) {
		this.truststorePass = truststorePass;
	}

	public boolean isBasicAuthEnabled() {
		return basicAuthEnabled;
	}

	public void setBasicAuthEnabled(boolean basicAuthEnabled) {
		this.basicAuthEnabled = basicAuthEnabled;
	}

	public String getBasicauthUser() {
		return basicauthUser;
	}

	public void setBasicauthUser(String basicauthUser) {
		this.basicauthUser = basicauthUser;
	}

	public String getBasicauthPass() {
		return basicauthPass;
	}

	public void setBasicauthPass(String basicauthPass) {
		this.basicauthPass = basicauthPass;
	}

	public String getDumpFilter() {
		return dumpFilter;
	}

	public void setDumpFilter(String dumpFilter) {
		this.dumpFilter = dumpFilter;
	}

	public boolean isAutoSubscribe() {
		return autoSubscribe;
	}

	public void setAutoSubscribe(boolean autoSubscribe) {
		this.autoSubscribe = autoSubscribe;
	}

	public int getMaxPollSize() {
		return maxPollSize;
	}

	public void setMaxPollSize(int maxPollSize) {
		this.maxPollSize = maxPollSize;
	}
	
	public ConnectionParameter clone() {
		ConnectionParameter param = new ConnectionParameter();
		param.setName(getName());
		param.setEndpoint(getEndpoint());
		param.setKeystore(getKeystore());
		param.setKeystorePass(getKeystorePass());
		param.setTruststore(getTruststore());
		param.setTruststorePass(getTruststorePass());
		param.setAutoSubscribe(isAutoSubscribe());
		param.setBasicAuthEnabled(isBasicAuthEnabled());
		param.setBasicauthUser(getBasicauthUser());
		param.setBasicauthPass(getBasicauthPass());
		param.setDumpFilter(getDumpFilter());
		param.setMaxPollSize(getMaxPollSize());
		return param;
	}

	@Override
	public String toString() {
		return name;
	};

	private String name;
	private String endpoint;
	private String keystore;
	private String truststore;
	private String keystorePass;
	private String truststorePass;
	private boolean basicAuthEnabled;
	private String basicauthUser;
	private String basicauthPass;
	private String dumpFilter;
	private boolean autoSubscribe;
	private int maxPollSize;	

}
