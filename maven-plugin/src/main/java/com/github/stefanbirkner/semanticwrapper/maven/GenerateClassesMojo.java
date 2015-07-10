package com.github.stefanbirkner.semanticwrapper.maven;

import static java.util.Collections.emptyList;
import static org.apache.maven.plugins.annotations.LifecyclePhase.GENERATE_SOURCES;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.github.stefanbirkner.semanticwrapper.generator.CodeGenerator;
import com.github.stefanbirkner.semanticwrapper.generator.Request;

/**
 * Create classes using the Semantic Wrapper code generator.
 */
@Mojo(name = "generate", defaultPhase = GENERATE_SOURCES, requiresProject = true)
public class GenerateClassesMojo extends AbstractMojo {
    private static final ConfigurationReader CONFIGURATION_READER = new ConfigurationReader();
    private static final CodeGenerator CODE_GENERATOR = new CodeGenerator();
    private static final Collection<Request> NO_REQUESTS = emptyList();

    @Parameter(defaultValue = "${project}", required = true, readonly = true)
    private MavenProject project;

    /**
     * Location of the output files.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-sources/semantic-wrapper-maven-plugin/", required = true)
    private File outputDirectory;

    /**
     * Location of the configuration files.
     */
    @Parameter(defaultValue = "src/main/semantic-wrapper/", required = true)
    private File configurationDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Collection<Request> requests = requestsFromConfigurationFiles();
        SourceCodeFileGenerator generator = new SourceCodeFileGenerator(outputDirectory, CODE_GENERATOR);
        generator.createSourceCodeFilesForRequests(requests);
        project.addCompileSourceRoot(outputDirectory.getAbsolutePath());
    }

    private Collection<Request> requestsFromConfigurationFiles() {
        if (configurationDirectory.exists())
            return requestsFromConfigurationFiles(configurationDirectory.listFiles());
        else
            return noRequests("The directory of the configuration files is missing.");
    }

    private Collection<Request> requestsFromConfigurationFiles(File[] files) {
        if (files == null)
            return noRequests(configurationDirectory.getAbsolutePath() + " is not a directory.");
        if (files.length == 0)
            return noRequests("The directory " + configurationDirectory.getAbsolutePath()
                + " has no configuration files.");
        else
            return requestsForConfigurationFiles(files);
    }

    private Collection<Request> requestsForConfigurationFiles(File[] configurationFiles) {
        Set<Request> requests = new HashSet<Request>();
        for (File configurationFile : configurationFiles)
            requests.addAll(CONFIGURATION_READER.requestsFromConfigurationFile(configurationFile));
        return requests;
    }

    private Collection<Request> noRequests(String logMessage) {
        getLog().warn(logMessage);
        return NO_REQUESTS;
    }
}
