package com.github.stefanbirkner.noprimitives.generator;

import static org.apache.commons.lang3.Validate.notNull;

public class PrimitiveWrapperGenerator extends GeneratorTemplate {
    private final String supportedType;
    private final String classForType;

    public PrimitiveWrapperGenerator(String supportedType, String classForType) {
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
