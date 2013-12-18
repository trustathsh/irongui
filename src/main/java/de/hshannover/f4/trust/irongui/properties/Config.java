package de.hshannover.f4.trust.irongui.properties;




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
