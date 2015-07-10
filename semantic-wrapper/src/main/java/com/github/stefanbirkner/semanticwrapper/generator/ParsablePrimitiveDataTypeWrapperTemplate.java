package com.github.stefanbirkner.semanticwrapper.generator;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ParsablePrimitiveDataTypeWrapperTemplate extends ClassTemplate {
    private static final Map<String, String> CLASSES_FOR_PRIMITIVE_DATA_TYPES = new HashMap<String, String>() {
        {
            put("byte", "Byte");
            put("short", "Short");
            put("int", "Integer");
            put("long", "Long");
            put("byte", "Byte");
            put("boolean", "Boolean");
            put("float", "Float");
            put("double", "Double");
        }
    };

    @Override
    public boolean canCreateWrapperForRequest(Request request) {
        return CLASSES_FOR_PRIMITIVE_DATA_TYPES.containsKey(request.nameOfBasicTypeOrItsClass);
    }

    @Override
    protected CharSequence importsForRequest(Request request) {
        return "\nimport static java.lang." + classForRequest(request) + "." + parseMethodForRequest(request) + ";\n";
    }

    @Override
    protected CharSequence additionalClassMethodsForRequest(Request request) {
        return "\n\tpublic static " + request.nameOfWrappersClass + " parse" + request.nameOfWrappersClass
                + "(String text) {\n\t\treturn ((text == null) || text.isEmpty()) ? null : new "
                + request.nameOfWrappersClass + "(" + parseMethodForRequest(request) + "(text));\n\t}\n";
    }

    private String parseMethodForRequest(Request request) {
        String capitalizedSupportedType = request.nameOfBasicTypeOrItsClass.substring(0, 1).toUpperCase(Locale.US)
                + request.nameOfBasicTypeOrItsClass.substring(1);
        return "parse" + capitalizedSupportedType;
    }

    @Override
    protected String basicTypeClassForRequest(Request request) {
        return classForRequest(request);
    }

    @Override
    protected String nameOfValueMethodForRequest(Request request) {
        return request.nameOfBasicTypeOrItsClass + "Value";
    }

    private String classForRequest(Request request) {
        return CLASSES_FOR_PRIMITIVE_DATA_TYPES.get(request.nameOfBasicTypeOrItsClass);
    }
}
