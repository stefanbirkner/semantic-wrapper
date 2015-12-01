package com.github.stefanbirkner.semanticwrapper.maven;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static java.util.Collections.singleton;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileReader;

import org.apache.commons.io.IOUtils;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import com.github.stefanbirkner.semanticwrapper.generator.CodeGenerator;
import com.github.stefanbirkner.semanticwrapper.generator.Request;
import org.sonatype.plexus.build.incremental.BuildContext;

public class SourceCodeFileGeneratorTest {
    private static final String DUMMY_SOURCE_CODE = "dummy source code";
    private final CodeGenerator codeGenerator = mock(CodeGenerator.class);
    private final BuildContext buildContext = mock(BuildContext.class);

    @Rule
    public final TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void createsFileForRequest() throws Exception {
        Request request = generateWrapperClassForBasicType("package.TestClass", "int");
        when(codeGenerator.createCodeForRequest(request)).thenReturn(DUMMY_SOURCE_CODE);
        File folder = temporaryFolder.newFolder();
        SourceCodeFileGenerator generator = new SourceCodeFileGenerator(folder, codeGenerator, buildContext);
        generator.createSourceCodeFilesForRequests(singleton(request));
        File sourceCodeFile = new File(folder, "package/TestClass.java");
        assertThat(IOUtils.toString(new FileReader(sourceCodeFile)), is(equalTo(DUMMY_SOURCE_CODE)));
    }

    @Test
    public void informsBuildContextAboutGeneratedFiles() throws Exception {
        Request request = generateWrapperClassForBasicType("package.TestClass", "int");
        File folder = temporaryFolder.newFolder();
        SourceCodeFileGenerator generator = new SourceCodeFileGenerator(folder, codeGenerator, buildContext);
        generator.createSourceCodeFilesForRequests(singleton(request));
        File sourceCodeFile = new File(folder, "package/TestClass.java");
        verify(buildContext).refresh(sourceCodeFile);
    }
}
