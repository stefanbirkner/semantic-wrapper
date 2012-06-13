package com.github.stefanbirkner.noprimitives.maven;

import static org.apache.commons.io.FileUtils.write;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

import com.github.stefanbirkner.noprimitives.generator.CodeGenerator;
import com.github.stefanbirkner.noprimitives.generator.Request;

public class SourceCodeFileGenerator {
    private final File baseDir;
    private final CodeGenerator codeGenerator;

    public SourceCodeFileGenerator(File baseDir, CodeGenerator codeGenerator) {
        this.baseDir = baseDir;
        this.codeGenerator = codeGenerator;
    }

    public void createSourceCodeFilesForRequests(Collection<Request> requests) {
        for (Request request : requests)
            createSourceCodeFilesForRequest(request);
    }

    private void createSourceCodeFilesForRequest(Request request) {
        String sourceCode = codeGenerator.createCodeForRequest(request);
        try {
            write(fileForRequest(request), sourceCode);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private File fileForRequest(Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(baseDir.getPath());
        sb.append("/");
        if (request.nameOfWrappersPackage != null) {
            sb.append(request.nameOfWrappersPackage.replace(".", "/"));
            sb.append("/");
        }
        sb.append(request.nameOfWrappersClass);
        sb.append(".java");
        return new File(sb.toString());
    }
}
