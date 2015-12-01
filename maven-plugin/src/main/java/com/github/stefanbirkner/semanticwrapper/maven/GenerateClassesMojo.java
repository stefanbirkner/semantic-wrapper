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
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

import com.github.stefanbirkner.semanticwrapper.generator.CodeGenerator;
import com.github.stefanbirkner.semanticwrapper.generator.Request;
import org.codehaus.plexus.util.Scanner;
import org.sonatype.plexus.build.incremental.BuildContext;

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

    @Component
    private BuildContext buildContext;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Collection<Request> requests = requestsFromConfigurationFiles();
        SourceCodeFileGenerator generator = new SourceCodeFileGenerator(
            outputDirectory, CODE_GENERATOR, buildContext);
        generator.createSourceCodeFilesForRequests(requests);
        project.addCompileSourceRoot(outputDirectory.getAbsolutePath());
    }

    private Collection<Request> requestsFromConfigurationFiles() {
        if (configurationDirectory.exists())
            return requestsFromConfigurationFiles(getChangedFiles());
        else
            return noRequests("The directory of the configuration files is missing.");
    }

    private String[] getChangedFiles() {
        Scanner scanner = buildContext.newScanner(configurationDirectory);
        scanner.scan();
        return scanner.getIncludedFiles();
    }

    private Collection<Request> requestsFromConfigurationFiles(String[] files) {
        if (files.length == 0)
            return noRequests("The directory " + configurationDirectory.getAbsolutePath()
                + " has no configuration files.");
        else
            return requestsForConfigurationFiles(files);
    }

    private Collection<Request> requestsForConfigurationFiles(String[] configurationFiles) {
        Set<Request> requests = new HashSet<>();
        for (String configurationFile : configurationFiles)
            requests.addAll(
                CONFIGURATION_READER.requestsFromConfigurationFile(
                    new File(configurationDirectory, configurationFile)));
        return requests;
    }

    private Collection<Request> noRequests(String logMessage) {
        getLog().warn(logMessage);
        return NO_REQUESTS;
    }
}
