package no.kantega.publishing.webdav;

import com.bradmcevoy.http.ResourceFactory;
import com.bradmcevoy.http.Resource;
import no.kantega.commons.log.Log;
import no.kantega.publishing.spring.RootContext;
import no.kantega.publishing.webdav.resourcehandlers.AksessWebDavResourceHandler;

import java.util.Collection;

/**
 *
 */
public class AksessResourceFactory implements ResourceFactory {
    private final static String ROOT = "/webdav";
    private final static String CONTENT = "/content";
    private final static String MULTIMEDIA = "/multimedia";

    public Resource getResource(String host, String path) {
        Log.debug(this.getClass().getName(), "Get resource: " + path);

        path = path.substring(path.indexOf(ROOT) +  + ROOT.length(), path.length());

        Collection<AksessWebDavResourceHandler> resourceHandlers = RootContext.getInstance().getBeansOfType(AksessWebDavResourceHandler.class).values();
        for (AksessWebDavResourceHandler resourceHandler : resourceHandlers) {
            if (resourceHandler.canHandlePath(path)) {
                return resourceHandler.getResourceFromPath(path);
            }
        }

        return null;
    }

}
