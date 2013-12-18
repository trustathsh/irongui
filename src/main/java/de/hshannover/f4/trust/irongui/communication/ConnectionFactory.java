package de.hshannover.f4.trust.irongui.communication;




import de.hshannover.f4.trust.irongui.exception.ConnectionCreationException;

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
