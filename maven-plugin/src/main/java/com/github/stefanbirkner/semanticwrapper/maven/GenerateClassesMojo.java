package com.github.stefanbirkner.semanticwrapper.maven;

import java.io.File;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import com.github.stefanbirkner.semanticwrapper.generator.CodeGenerator;
import com.github.stefanbirkner.semanticwrapper.generator.Request;

/**
 * Create classes using the Semantic Wrapper code generator.
 * 
 * @goal generate
 * @phase generate-sources
 * @requiresProject true
 */
public class GenerateClassesMojo extends AbstractMojo {
    private static final ConfigurationReader CONFIGURATION_READER = new ConfigurationReader();
    private static final CodeGenerator CODE_GENERATOR = new CodeGenerator();

    /**
     * @parameter default-value="${project}"
     * @required
     * @readonly
     */
    private MavenProject project;

    /**
     * Location of the output files.
     * 
     * @parameter default-value=
     *            "${project.build.directory}/generated-sources/semantic-wrapper-maven-plugin/"
     * @required
     */
    private File outputDirectory;

    /**
     * Location of the configuration files.
     * 
     * @parameter default-value="src/main/semantic-wrapper/"
     * @required
     */
    private File configurationDirectory;

    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        Collection<Request> requests = requestsFromConfigurationFiles();
        SourceCodeFileGenerator generator = new SourceCodeFileGenerator(outputDirectory, CODE_GENERATOR);
        generator.createSourceCodeFilesForRequests(requests);
        project.addCompileSourceRoot(outputDirectory.getAbsolutePath());
    }

    private Collection<Request> requestsFromConfigurationFiles() {
        Set<Request> requests = new HashSet<Request>();
        for (File configurationFile : configurationDirectory.listFiles())
            requests.addAll(CONFIGURATION_READER.requestsFromConfigurationFile(configurationFile));
        return requests;
    }

}