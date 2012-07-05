package com.github.stefanbirkner.semanticwrapper.generator;

public class StringWrapperTemplate extends ClassTemplate {
    @Override
    public boolean canCreateWrapperForRequest(Request request) {
        return "String".equals(request.nameOfBasicTypeOrItsClass)
                && ((request.nameOfBasicTypesPackage == null) || "java.lang".equals(request.nameOfBasicTypesPackage));
    }

    @Override
    protected CharSequence importsForRequest(Request request) {
        return "";
    }

    @Override
    protected CharSequence additionalClassMethodsForRequest(Request request) {
        return "";
    }

    @Override
    protected String basicTypeClassForRequest(Request request) {
        return "String";
    }

    @Override
    protected String nameOfValueMethodForRequest(Request request) {
        return "stringValue";
    }

    @Override
    protected CharSequence toStringTermForRequest(Request request) {
        return fieldNameForRequest(request);
    }
}
