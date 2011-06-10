package no.kantega.publishing.spring;

import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.resource.loader.FileResourceLoader;
import org.springframework.web.servlet.view.velocity.VelocityConfigurer;

import java.io.File;

/**
 *
 */
public class CustomVelocityConfigurer extends VelocityConfigurer{

    private static final String PLUGIN_MACRO_LIBRARY = "no/kantega/publishing/api/view/velocity/plugin.vm";
    @Override
    protected void postProcessVelocityEngine(VelocityEngine velocityEngine) {

        final String resourceBases = System.getProperty("resourceBases");

        // For easy development reload of velocity templates
        if(resourceBases != null) {
            for(String base : resourceBases.split(File.pathSeparator)) {
                final String loaderName = "base_" + base.hashCode();
                velocityEngine.addProperty(VelocityEngine.RESOURCE_LOADER, loaderName);
                velocityEngine.setProperty(loaderName +".resource.loader.class", FileResourceLoader.class.getName());
                velocityEngine.setProperty(loaderName +".resource.loader.path", base);
                velocityEngine.setProperty(loaderName +".resource.loader.cache", "false");
                

            }
        }
        velocityEngine.addProperty(
                        VelocityEngine.VM_LIBRARY, PLUGIN_MACRO_LIBRARY);

        super.postProcessVelocityEngine(velocityEngine);

    }
}