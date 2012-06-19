package com.github.stefanbirkner.semanticwrapper.generator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.github.stefanbirkner.semanticwrapper.generator.PrimitiveDataTypeWrapperGenerator;

public class PrimitiveDataTypeWrapperGeneratorTest {
    private static final String ARBITRARY_TYPE = "arbitrary.type";
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void cannotBeCreatedWithoutSupportedType() {
        thrown.expectMessage("The supported type is missing.");
        new PrimitiveDataTypeWrapperGenerator(null, ARBITRARY_TYPE);
    }

    @Test
    public void cannotBeCreatedWithoutClassForType() {
        thrown.expectMessage("The class for type is missing.");
        new PrimitiveDataTypeWrapperGenerator(ARBITRARY_TYPE, null);
    }
}
