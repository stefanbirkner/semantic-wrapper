package com.github.stefanbirkner.semanticwrapper.maven;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.apache.commons.io.FileUtils.readLines;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.github.stefanbirkner.semanticwrapper.generator.Request;

public class ConfigurationReader {
    private static final String PREFIX_COMMENT = "#";

    public Collection<Request> requestsFromConfigurationFile(File configurationFile) {
        Iterable<String> rawLines = linesOfFile(configurationFile);
        Iterable<String> linesWithoutComments = removeCommentsFromLines(rawLines);
        Iterable<String> parsableLines = removeBlankLines(linesWithoutComments);
        return parseRequests(parsableLines);
    }

    private Iterable<String> linesOfFile(File file) {
        try {
            return readLines(file, "UTF-8");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Iterable<String> removeCommentsFromLines(Iterable<String> lines) {
        List<String> linesWithoutComments = new ArrayList<String>();
        for (String line : lines)
            linesWithoutComments.add(removeCommentFromLine(line));
        return linesWithoutComments;
    }

    private String removeCommentFromLine(String line) {
        if (line.contains(PREFIX_COMMENT))
            return line.substring(0, line.indexOf(PREFIX_COMMENT));
        else
            return line;
    }

    private Iterable<String> removeBlankLines(Iterable<String> lines) {
        List<String> nonBlankLines = new ArrayList<String>();
        for (String line : lines)
            if (isNotBlank(line))
                nonBlankLines.add(line);
        return nonBlankLines;
    }

    private boolean isNotBlank(String line) {
        return !line.trim().equals("");
    }

    private Collection<Request> parseRequests(Iterable<String> lines) {
        Set<Request> requests = new HashSet<Request>();
        for (String line : lines)
            addRequest(requests, line);
        return requests;
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
