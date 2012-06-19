package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.StringUtils.uncapitalize;

public class ObjectWrapperGenerator extends GeneratorTemplate {
    @Override
    protected CharSequence importsForRequest(Request request) {
        return (request.nameOfBasicTypesPackage == null) ? "" : "\nimport " + request.nameOfBasicTypesPackage + "."
                + request.nameOfBasicTypeOrItsClass + ";\n";
    }

    @Override
    protected String basicTypeClassForRequest(Request request) {
        return request.nameOfBasicTypeOrItsClass;
    }

    @Override
    protected String nameOfValueMethodForRequest(Request request) {
        return uncapitalize(request.nameOfBasicTypeOrItsClass) + "Value";
    }
}
