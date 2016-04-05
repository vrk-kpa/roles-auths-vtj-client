package fi.vm.kapa.rova.config;

import fi.vm.kapa.rova.soap.handlers.XroadHeaderHandler;
import fi.vrk.xml.ws.vtj.vtjkysely._1.ISoSoAdapterService60;
import org.apache.cxf.clustering.LoadDistributorFeature;
import org.apache.cxf.clustering.RandomStrategy;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

/**
 * Configures soap client
 * Created by Juha Korkalainen on 5.4.2016.
 */
@Configuration
public class SoapConfiguration {

    @Value("${xroad_endpoint}")
    String xroadEndpoint;

    @Autowired
    private XroadHeaderHandler xroadHeaderHandler;


    @Bean
    ISoSoAdapterService60 vtjClient() {
        return (ISoSoAdapterService60) jaxWsProxyFactoryBean().create();
    }

    private JaxWsProxyFactoryBean jaxWsProxyFactoryBean() {
        JaxWsProxyFactoryBean factory = new JaxWsProxyFactoryBean();

        // load distribution
        LoadDistributorFeature loadDistributorFeature = new LoadDistributorFeature();
        RandomStrategy ldStrategy = new RandomStrategy();
        ldStrategy.setAlternateAddresses(getEndpoints());
        loadDistributorFeature.setStrategy(ldStrategy);
        factory.getFeatures().add(loadDistributorFeature);

        factory.getHandlers().add(xroadHeaderHandler);
        factory.setServiceClass(ISoSoAdapterService60.class);

        return factory;
    }

    private List<String> getEndpoints() {
        String[] endpoints = xroadEndpoint.split(",");
        List<String> list = Arrays.asList(endpoints);
        return list;
    }

}
