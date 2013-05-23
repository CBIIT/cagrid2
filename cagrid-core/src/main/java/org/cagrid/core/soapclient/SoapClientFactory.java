package org.cagrid.core.soapclient;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;
import javax.xml.ws.BindingProvider;

import org.apache.cxf.Bus;
import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.jsse.TLSParameterJaxBUtils;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.configuration.security.TrustManagersType;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.frontend.ClientProxy;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;

public class SoapClientFactory {

	public static <T> T configureSoapClient(T port,
			String url) {
		BindingProvider bp = (BindingProvider) port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,
				url);
		return port;
	}

	public static <T> T configureSoapClient(T port,
			String url, KeyStoreType truststore, KeyManagersType keyManager)
			throws GeneralSecurityException, IOException {
		port = configureSoapClient(port, url);

		Client client = ClientProxy.getClient(port);
		Bus bus = client.getBus();
		Configurer baseConf = bus.getExtension(Configurer.class);
		SSLConfigurer sslConf = new SSLConfigurer(baseConf, truststore,
				keyManager);
		bus.setExtension(sslConf, Configurer.class);

		return port;
	}

	static class SSLConfigurer implements Configurer {
		private final Configurer parentConfigurer;
		private final TrustManager[] trustManagers;
		private final KeyManager[] keyManagers;

		public SSLConfigurer(Configurer parentConfigurer,
				KeyStoreType truststore, KeyManagersType keyManager)
				throws GeneralSecurityException, IOException {
			this.parentConfigurer = parentConfigurer;

			TrustManagersType trustManagersType = new TrustManagersType();
			trustManagersType.setKeyStore(truststore);
			trustManagers = TLSParameterJaxBUtils
					.getTrustManagers(trustManagersType);

			if (keyManager != null) {
				keyManagers = TLSParameterJaxBUtils.getKeyManagers(keyManager);
			} else {
				keyManagers = null;
			}
		}

		@Override
		public void configureBean(Object beanInstance) {
			configureBean(null, beanInstance);
		}

		@Override
		public void configureBean(String name, Object beanInstance) {
			if (beanInstance instanceof HTTPConduit) {
				HTTPConduit http = (HTTPConduit) beanInstance;
				TLSClientParameters tls = new TLSClientParameters();
				tls.setTrustManagers(trustManagers);
				tls.setKeyManagers(keyManagers);
				tls.setDisableCNCheck(true);
				tls.setCipherSuitesFilter(getCipherSuites());
				http.setTlsClientParameters(tls);
				HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
				httpClientPolicy.setConnectionTimeout(36000);
				httpClientPolicy.setAllowChunking(false);
				httpClientPolicy.setReceiveTimeout(120000);
				http.setClient(httpClientPolicy);
			} else {
				parentConfigurer.configureBean(name, beanInstance);
			}
		}

		private FiltersType getCipherSuites() {
			FiltersType filters = new FiltersType();
			filters.getInclude().add(".*_WITH_3DES_.*");
			filters.getInclude().add(".*_WITH_DES_.*");
			filters.getExclude().add(".*_WITH_NULL_.*");
			filters.getExclude().add(".*_DH_anon_.*");
			return filters;
		}
	}
}
