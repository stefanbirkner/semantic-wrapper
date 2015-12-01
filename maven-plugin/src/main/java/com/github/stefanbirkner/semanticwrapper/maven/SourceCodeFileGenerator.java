package com.github.stefanbirkner.semanticwrapper.maven;

import static org.apache.commons.io.FileUtils.write;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.github.stefanbirkner.semanticwrapper.generator.CodeGenerator;
import com.github.stefanbirkner.semanticwrapper.generator.Request;
import org.sonatype.plexus.build.incremental.BuildContext;

public class SourceCodeFileGenerator {
    private final File baseDir;
    private final CodeGenerator codeGenerator;
    private BuildContext buildContext;

    public SourceCodeFileGenerator(File baseDir, CodeGenerator codeGenerator, BuildContext buildContext) {
        this.baseDir = baseDir;
        this.codeGenerator = codeGenerator;
        this.buildContext = buildContext;
    }

    public void createSourceCodeFilesForRequests(Collection<Request> requests) {
        for (Request request : requests)
            createSourceCodeFilesForRequest(request);
    }

    private void createSourceCodeFilesForRequest(Request request) {
        String sourceCode = codeGenerator.createCodeForRequest(request);
        try {
            File file = fileForRequest(request);
            write(file, sourceCode);
            buildContext.refresh(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File fileForRequest(Request request) {
        StringBuilder sb = new StringBuilder();
        if (request.nameOfWrappersPackage != null) {
            sb.append(request.nameOfWrappersPackage.replace(".", "/"));
            sb.append("/");
        }
        sb.append(request.nameOfWrappersClass);
        sb.append(".java");
        return new File(baseDir, sb.toString());
    }
}
