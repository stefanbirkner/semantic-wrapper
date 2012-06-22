package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.Validate.notNull;

public class ParsablePrimitiveDataTypeWrapperTemplate extends ClassTemplate {
    private final String supportedType;
    private final String classForType;

    public ParsablePrimitiveDataTypeWrapperTemplate(String supportedType, String classForType) {
        this.supportedType = notNull(supportedType, "The supported type is missing.");
        this.classForType = notNull(classForType, "The class for type is missing.");
    }

    @Override
    protected CharSequence importsForRequest(Request request) {
        return "\nimport static java.lang." + classForType + "." + parseMethodForRequest(request) + ";\n";
    }

    @Override
    protected CharSequence additionalClassMethodsForRequest(Request request) {
        return "\n\tpublic static " + request.nameOfWrappersClass + " parse" + request.nameOfWrappersClass
                + "(String text) {\n\t\treturn ((text == null) || text.isEmpty()) ? null : new "
                + request.nameOfWrappersClass + "(" + parseMethodForRequest(request) + "(text));\n\t}\n";
    }

    private String parseMethodForRequest(Request request) {
        String capitalizedSupportedType = supportedType.substring(0, 1).toUpperCase() + supportedType.substring(1);
        return "parse" + capitalizedSupportedType;
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
