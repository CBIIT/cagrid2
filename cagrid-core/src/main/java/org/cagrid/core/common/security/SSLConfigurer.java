package org.cagrid.core.common.security;

import org.apache.cxf.configuration.Configurer;
import org.apache.cxf.configuration.jsse.TLSClientParameters;
import org.apache.cxf.configuration.jsse.TLSParameterJaxBUtils;
import org.apache.cxf.configuration.security.FiltersType;
import org.apache.cxf.configuration.security.KeyManagersType;
import org.apache.cxf.configuration.security.KeyStoreType;
import org.apache.cxf.configuration.security.TrustManagersType;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.cxf.transports.http.configuration.HTTPClientPolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.KeyManager;
import javax.net.ssl.TrustManager;

public class SSLConfigurer implements Configurer {

    private final Configurer baseConf;
    private Logger log;
    private X509Credential credential;
    private KeyStoreType truststore;
    private KeyManagersType keystore;
    private KeyManager keyManager;
    private TrustManager[] trustManagers;
    private KeyManager[] km;

    public SSLConfigurer(Configurer baseConf) {
        log = LoggerFactory.getLogger(this.getClass().getName());
        this.baseConf = baseConf;
    }

    public X509Credential getCredential() {
        return credential;
    }

    public void setCredential(X509Credential credential) {
        this.credential = credential;
        km = null;
    }

    public KeyStoreType getTruststore() {
        return truststore;
    }

    public void setTruststore(KeyStoreType truststore) {
        this.truststore = truststore;
        trustManagers = null;
    }

    public KeyManagersType getKeystore() {
        return keystore;
    }

    public void setKeystore(KeyManagersType keystore) {
        this.keystore = keystore;
        km = null;
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

                if (this.trustManagers != null) {
                    try {
                        tls.setTrustManagers(getTrustManagers());
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }
                }


            if (getTruststore() != null) {
                try {
                    tls.setTrustManagers(getTrustManagers());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            if ((getCredential() != null) || (getKeystore() != null)) {
                try {
                    tls.setKeyManagers(getKeyManager());
                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
            tls.setDisableCNCheck(true);
            tls.setCipherSuitesFilter(getCipherSuites());
            http.setTlsClientParameters(tls);
            HTTPClientPolicy httpClientPolicy = new HTTPClientPolicy();
            httpClientPolicy.setConnectionTimeout(36000);
            httpClientPolicy.setAllowChunking(false);
            httpClientPolicy.setReceiveTimeout(120000);
            http.setClient(httpClientPolicy);

        } else {
            baseConf.configureBean(name, beanInstance);
        }
    }

    private KeyManager[] getKeyManager() throws Exception {
        if (km == null) {
            if (getCredential() != null) {
                km = new KeyManager[1];
                km[0] = credential.getKeyManager();
            } else if (getKeystore() != null) {
                km = TLSParameterJaxBUtils.getKeyManagers(this.keystore);
            }
        }
        return km;
    }


    public void setKm(KeyManager[] km) {
        this.km = km;
    }

    public TrustManager[] getTrustManagers() throws Exception {
        if (trustManagers == null) {
            if (getTruststore() != null) {
                TrustManagersType type = new TrustManagersType();
                type.setKeyStore(getTruststore());
                trustManagers = TLSParameterJaxBUtils.getTrustManagers(type);
            }
        }
        return trustManagers;
    }

    public void setTrustManagers(TrustManager[] trustManagers) {
        this.trustManagers = trustManagers;
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
