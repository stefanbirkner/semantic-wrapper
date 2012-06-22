package com.github.stefanbirkner.semanticwrapper.generator;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ParsablePrimitiveDataTypeWrapperTemplateTest {
    private static final String ARBITRARY_TYPE = "arbitrary.Type";
    private static final Request ARBITRARY_INT_REQUEST = generateWrapperClassForBasicType(ARBITRARY_TYPE, "int");
    private final ParsablePrimitiveDataTypeWrapperTemplate template = new ParsablePrimitiveDataTypeWrapperTemplate();

    @Test
    public void canCreateWrapperClassForInt() {
        assertThat(template.canCreateWrapperForRequest(ARBITRARY_INT_REQUEST), is(true));
    }

    @Test
    public void cannotCreateWrapperClassForChar() {
        Request request = generateWrapperClassForBasicType("Wrapper", "char");
        assertThat(template.canCreateWrapperForRequest(request), is(false));
    }

    @Test
    public void createsStaticImport() {
        String imports = template.importsForRequest(ARBITRARY_INT_REQUEST).toString();
        assertThat(imports, is(equalTo("\nimport static java.lang.Integer.parseInt;\n")));
    }

    @Test
    public void createsAdditionalClassMethods() {
        String additionalClassMethods = template.additionalClassMethodsForRequest(ARBITRARY_INT_REQUEST).toString();
        assertThat(
                additionalClassMethods,
                is(equalTo("\n\tpublic static Type parseType(String text) {\n\t\treturn ((text == null) || text.isEmpty()) ? null : new Type(parseInt(text));\n\t}\n")));
    }
}
