package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;
import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CodeGenerator} creates the source code of a class for a
 * {@link Request}.
 */
public class CodeGenerator {
    private static final ObjectWrapperTemplate OBJECT_WRAPPER_TEMPLATE = new ObjectWrapperTemplate();
    private final Map<String, ClassTemplate> semanticWrapperTemplatesForTypes = new HashMap<String, ClassTemplate>();

    public CodeGenerator() {
        addGeneratorForTypeWithParseMethod("byte", "Byte");
        addCharWrapperTemplate();
        addGeneratorForTypeWithParseMethod("short", "Short");
        addGeneratorForTypeWithParseMethod("int", "Integer");
        addGeneratorForTypeWithParseMethod("long", "Long");
        addGeneratorForTypeWithParseMethod("byte", "Byte");
        addGeneratorForTypeWithParseMethod("boolean", "Boolean");
        addGeneratorForTypeWithParseMethod("float", "Float");
        addGeneratorForTypeWithParseMethod("double", "Double");

    }

    private void addCharWrapperTemplate() {
        semanticWrapperTemplatesForTypes.put("char", new CharWrapperTemplate());
    }

    private void addGeneratorForTypeWithParseMethod(String supportedType, String classForType) {
        ParsablePrimitiveDataTypeWrapperTemplate template = new ParsablePrimitiveDataTypeWrapperTemplate(supportedType, classForType);
        semanticWrapperTemplatesForTypes.put(supportedType, template);
    }

    public String createCodeForRequest(Request request) {
        ClassTemplate template = templateForType(request.nameOfBasicTypeOrItsClass);
        return template.createCodeForRequest(request);
    }

    private ClassTemplate templateForType(String type) {
        ClassTemplate template = semanticWrapperTemplatesForTypes.get(type);
        return defaultIfNull(template, OBJECT_WRAPPER_TEMPLATE);
    }
}
