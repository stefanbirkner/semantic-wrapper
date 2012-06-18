package com.github.stefanbirkner.semanticwrapper.maven;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.IOUtils;
import com.github.stefanbirkner.semanticwrapper.generator.Request;

public class ConfigurationReader {
    public Collection<Request> requestsFromConfigurationFile(File configurationFile) {
        Set<Request> requests = new HashSet<Request>();
        for (String line : linesOfFile(configurationFile))
            if (isNotBlank(line))
                addRequest(requests, line);
        return requests;
    }

    private Iterable<String> linesOfFile(File file) {
        try {
            return IOUtils.readLines(new FileReader(file));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isNotBlank(String line) {
        return !line.trim().equals("");
    }

    private void addRequest(Set<Request> requests, String line) {
        Request request = parseRequest(line);
        requests.add(request);
    }

    private Request parseRequest(String line) {
        String[] parts = line.split(":");
        return generateWrapperClassForBasicType(parts[0].trim(), parts[1].trim());
    }
}
