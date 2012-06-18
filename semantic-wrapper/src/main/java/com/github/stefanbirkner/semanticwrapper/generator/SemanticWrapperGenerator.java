package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.Validate.notNull;

public class SemanticWrapperGenerator extends GeneratorTemplate {
    private final String supportedType;
    private final String classForType;

    public SemanticWrapperGenerator(String supportedType, String classForType) {
        this.supportedType = notNull(supportedType, "The supported type is missing.");
        this.classForType = notNull(classForType, "The class for type is missing.");
    }

    @Override
    protected String basicTypeClassForRequest(Request request) {
        return classForType;
    }

    @Override
    protected String nameOfValueMethodForRequest(Request request) {
        return supportedType + "Value";
    }
}