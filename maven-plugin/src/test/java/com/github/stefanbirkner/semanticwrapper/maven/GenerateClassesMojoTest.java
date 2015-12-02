package com.github.stefanbirkner.semanticwrapper.maven;

import io.takari.maven.testing.TestResources;
import io.takari.maven.testing.executor.MavenExecutionResult;
import io.takari.maven.testing.executor.MavenRuntime;
import io.takari.maven.testing.executor.MavenVersions;
import io.takari.maven.testing.executor.junit.MavenJUnitTestRunner;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static io.takari.maven.testing.TestResources.assertFilesPresent;

@RunWith(MavenJUnitTestRunner.class)
@MavenVersions("3.3.9")
public class GenerateClassesMojoTest {
    @Rule
    public final TestResources resources = new TestResources();

    public final MavenRuntime mavenRuntime;

    public GenerateClassesMojoTest(MavenRuntime.MavenRuntimeBuilder builder) throws Exception {
        this.mavenRuntime = builder.build();
    }

    @Test
    public void example_runs_without_errors() throws Exception {
        File basedir = resources.getBasedir("example");
        MavenExecutionResult result = mavenRuntime
            .forProject(basedir)
            .execute("clean", "test");

        result.assertErrorFreeLog();
    }

    @Test
    public void java_file_is_created() throws Exception {
        File basedir = resources.getBasedir("example");
        mavenRuntime
            .forProject(basedir)
            .execute("clean", "test");

        for (File file : basedir.listFiles())
            System.out.println(file.getName());
        System.out.println(basedir.getAbsolutePath());
        assertFilesPresent(basedir, "target/generated-sources/semantic-wrapper-maven-plugin/test/ExampleNumber.java");
    }
}
