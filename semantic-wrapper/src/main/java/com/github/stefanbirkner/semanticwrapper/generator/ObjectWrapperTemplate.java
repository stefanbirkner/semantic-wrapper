package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.StringUtils.uncapitalize;

public class ObjectWrapperTemplate extends ClassTemplate {
    @Override
    protected CharSequence importsForRequest(Request request) {
        return (request.nameOfBasicTypesPackage == null) ? "" : "\nimport " + request.nameOfBasicTypesPackage + "."
                + request.nameOfBasicTypeOrItsClass + ";\n";
    }

    @Override
    protected CharSequence additionalClassMethodsForRequest(Request request) {
        return "";
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
