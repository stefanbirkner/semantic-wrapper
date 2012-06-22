package com.github.stefanbirkner.semanticwrapper.generator;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class CharWrapperTemplateTest {
    private final CharWrapperTemplate template = new CharWrapperTemplate();

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void canCreateWrapperClassForChar() {
        Request request = generateWrapperClassForBasicType("Wrapper", "char");
        assertThat(template.canCreateWrapperForRequest(request), is(true));
    }

    @Test
    public void cannotCreateWrapperClassForNonChar() {
        Request request = generateWrapperClassForBasicType("Wrapper", "short");
        assertThat(template.canCreateWrapperForRequest(request), is(false));
    }

    @Test
    public void failsToCreateWrapperForNonCharRequest() {
        thrown.expect(IllegalArgumentException.class);
        Request request = generateWrapperClassForBasicType("Wrapper", "short");
        template.createCodeForRequest(request);
    }
}
