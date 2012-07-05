package com.github.stefanbirkner.semanticwrapper.generator;

import static java.util.Arrays.asList;
import java.util.List;

/**
 * The {@code CodeGenerator} creates the source code of a class for a
 * {@link Request}.
 */
public class CodeGenerator {
    private static final List<? extends ClassTemplate> CLASS_TEMPLATES = asList(
            new ParsablePrimitiveDataTypeWrapperTemplate(), new CharWrapperTemplate(), new StringWrapperTemplate(),
            new ObjectWrapperTemplate());

    public String createCodeForRequest(Request request) {
        ClassTemplate template = templateForRequest(request);
        return template.createCodeForRequest(request);
    }

    private ClassTemplate templateForRequest(Request request) {
        for (ClassTemplate template : CLASS_TEMPLATES)
            if (template.canCreateWrapperForRequest(request))
                return template;
        throw new IllegalArgumentException("Cannot create wrapper class for request " + request + ".");
    }
}
