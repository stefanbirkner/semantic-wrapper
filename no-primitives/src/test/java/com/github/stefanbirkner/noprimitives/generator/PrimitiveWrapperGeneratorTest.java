package com.github.stefanbirkner.noprimitives.generator;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class PrimitiveWrapperGeneratorTest {
    private static final String ARBITRARY_TYPE = "arbitrary.type";
    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void cannotBeCreatedWithoutSupportedType() {
        thrown.expectMessage("The supported type is missing.");
        new PrimitiveWrapperGenerator(null, ARBITRARY_TYPE);
    }

    @Test
    public void cannotBeCreatedWithoutClassForType() {
        thrown.expectMessage("The class for type is missing.");
        new PrimitiveWrapperGenerator(ARBITRARY_TYPE, null);
    }
}
