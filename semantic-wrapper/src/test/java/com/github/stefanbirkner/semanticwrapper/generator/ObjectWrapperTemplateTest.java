package com.github.stefanbirkner.semanticwrapper.generator;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ObjectWrapperTemplateTest {
    private final ObjectWrapperTemplate template = new ObjectWrapperTemplate();

    @Test
    public void canCreateWrapperForAnyClass() throws Exception {
        Request request = generateWrapperClassForBasicType("Wrapper", "AnyClass");
        assertThat(template.canCreateWrapperForRequest(request), is(true));
    }
}
