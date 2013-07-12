package no.kantega.openaksess.search.solr.config;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.embedded.EmbeddedSolrServer;
import org.apache.solr.core.CoreContainer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import static no.kantega.openaksess.search.solr.config.SolrConfigInitializer.initSolrConfigIfAbsent;

@Configuration
public class SolrConfiguration {

    @Value("${appDir}/solr")
    private File solrHome;

    @Value("${disableUpdateSolrHome:false}")
    private boolean disableUpdateSolrHome;

    @Bean(destroyMethod = "shutdown")
    public SolrServer getSolrServer() throws IOException, SAXException, ParserConfigurationException, URISyntaxException {
        File solrConfigFile = initSolrConfigIfAbsent(solrHome, disableUpdateSolrHome);
        CoreContainer container = new CoreContainer(solrHome.getAbsolutePath(), solrConfigFile);

        return new EmbeddedSolrServer(container, "oacore");
    }
}
