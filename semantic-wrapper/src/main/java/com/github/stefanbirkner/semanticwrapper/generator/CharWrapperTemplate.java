package com.github.stefanbirkner.semanticwrapper.generator;

public class CharWrapperTemplate extends ClassTemplate {

    @Override
    protected CharSequence importsForRequest(Request request) {
        return "";
    }

    @Override
    protected CharSequence additionalClassMethodsForRequest(Request request) {
        return "";
    }

    @Override
    protected CharSequence basicTypeClassForRequest(Request request) {
        return "Character";
    }

    @Override
    protected CharSequence nameOfValueMethodForRequest(Request request) {
        return "charValue";
    }
}
