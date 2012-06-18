package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.StringUtils.uncapitalize;

public class ObjectWrapperGenerator extends GeneratorTemplate {
    @Override
    protected String basicTypeClassForRequest(Request request) {
        return request.nameOfBasicType;
    }

    @Override
    protected String nameOfValueMethodForRequest(Request request) {
        return uncapitalize(request.nameOfBasicType) + "Value";
    }
}
