package com.github.stefanbirkner.semanticwrapper.generator;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class StringWrapperTemplateTest {
    private final StringWrapperTemplate template = new StringWrapperTemplate();

    @Rule
    public final ExpectedException thrown = none();

    @Test
    public void canCreateWrapperClassForStringWithoutPackage() {
        Request request = generateWrapperClassForBasicType("Wrapper", "String");
        assertThat(template.canCreateWrapperForRequest(request), is(true));
    }

    @Test
    public void canCreateWrapperClassForStringWithPackage() {
        Request request = generateWrapperClassForBasicType("Wrapper", "java.lang.String");
        assertThat(template.canCreateWrapperForRequest(request), is(true));
    }

    @Test
    public void cannotCreateWrapperClassForStringWithWrongPackage() {
        Request request = generateWrapperClassForBasicType("Wrapper", "non.java.lang.String");
        assertThat(template.canCreateWrapperForRequest(request), is(false));
    }
}
