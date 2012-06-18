package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code CodeGenerator} creates the source code of a class for a {@link Request}.
 */
public class CodeGenerator {
    private static final ObjectWrapperGenerator OBJECT_WRAPPER_GENERATOR = new ObjectWrapperGenerator();
    private final Map<String, SemanticWrapperGenerator> semanticWrapperGenerators =
        new HashMap<String, SemanticWrapperGenerator>();

    public CodeGenerator() {
        addGenerator("char", "Character");
        addGenerator("short", "Short");
        addGenerator("int", "Integer");
        addGenerator("long", "Long");
        addGenerator("byte", "Byte");
        addGenerator("boolean", "Boolean");
        addGenerator("float", "Float");
        addGenerator("double", "Double");
    }

    private void addGenerator(String supportedType, String classForType) {
        SemanticWrapperGenerator generator = new SemanticWrapperGenerator(supportedType, classForType);
        semanticWrapperGenerators.put(supportedType, generator);
    }

    public String createCodeForRequest(Request request) {
        GeneratorTemplate generator = generatorForType(request.nameOfBasicType);
        return generator.createCodeForRequest(request);
    }

    private GeneratorTemplate generatorForType(String type) {
        SemanticWrapperGenerator generator = semanticWrapperGenerators.get(type);
        return defaultIfNull(generator, OBJECT_WRAPPER_GENERATOR);
    }
}
