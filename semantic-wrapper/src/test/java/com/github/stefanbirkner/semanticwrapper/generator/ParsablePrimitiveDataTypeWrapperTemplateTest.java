package com.github.stefanbirkner.semanticwrapper.generator;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ParsablePrimitiveDataTypeWrapperTemplateTest {
    private static final String ARBITRARY_TYPE = "arbitrary.Type";
    private static final Request ARBITRARY_REQUEST = generateWrapperClassForBasicType(ARBITRARY_TYPE, ARBITRARY_TYPE);
    private final ParsablePrimitiveDataTypeWrapperTemplate intTemplate = new ParsablePrimitiveDataTypeWrapperTemplate("int",
            "Integer");

    @Rule
    public final ExpectedException thrown = ExpectedException.none();

    @Test
    public void cannotBeCreatedWithoutSupportedType() {
        thrown.expectMessage("The supported type is missing.");
        new ParsablePrimitiveDataTypeWrapperTemplate(null, ARBITRARY_TYPE);
    }

    @Test
    public void cannotBeCreatedWithoutClassForType() {
        thrown.expectMessage("The class for type is missing.");
        new ParsablePrimitiveDataTypeWrapperTemplate(ARBITRARY_TYPE, null);
    }

    @Test
    public void createsStaticImport() {
        String imports = intTemplate.importsForRequest(ARBITRARY_REQUEST).toString();
        assertThat(imports, is(equalTo("\nimport static java.lang.Integer.parseInt;\n")));
    }

    @Test
    public void createsAdditionalClassMethods() {
        String additionalClassMethods = intTemplate.additionalClassMethodsForRequest(ARBITRARY_REQUEST).toString();
        assertThat(
                additionalClassMethods,
                is(equalTo("\n\tpublic static Type parseType(String text) {\n\t\treturn ((text == null) || text.isEmpty()) ? null : new Type(parseInt(text));\n\t}\n")));
    }
}
