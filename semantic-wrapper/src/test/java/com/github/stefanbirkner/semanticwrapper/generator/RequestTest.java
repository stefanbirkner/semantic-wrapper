package com.github.stefanbirkner.semanticwrapper.generator;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.junit.rules.ExpectedException.none;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class RequestTest {
    private static final String NO_NAME = null;
    private static final String ARBITRARY_NAME = "idjfoierjgl";

    @Rule
    public final ExpectedException thrown = none();

    @Test
    public void cannotBeCreatedWithoutNameOfWrapperClass() {
        thrown.expectMessage("The name of the wrapper class is missing.");
        generateWrapperClassForBasicType(NO_NAME, ARBITRARY_NAME);
    }

    @Test
    public void cannotBeCreatedWithoutNameOfBasicType() {
        thrown.expectMessage("The name of the basic type is missing.");
        generateWrapperClassForBasicType(ARBITRARY_NAME, NO_NAME);
    }

    @Test
    public void extractsWrappersPackageName() {
        Request request = generateWrapperClassForBasicType("my.package.className", ARBITRARY_NAME);
        assertThat(request.nameOfWrappersPackage, is(equalTo("my.package")));
    }

    @Test
    public void extractsNoWrappersPackageNameForClassWithDefaultPackage() {
        Request request = generateWrapperClassForBasicType("className", ARBITRARY_NAME);
        assertThat(request.nameOfWrappersPackage, is(nullValue()));
    }

    @Test
    public void extractsWrappersClassName() {
        Request request = generateWrapperClassForBasicType("my.package.className", ARBITRARY_NAME);
        assertThat(request.nameOfWrappersClass, is(equalTo("className")));
    }

    @Test
    public void usesWrappersClassNameOfClassWithDefaultPackage() {
        Request request = generateWrapperClassForBasicType("className", ARBITRARY_NAME);
        assertThat(request.nameOfWrappersClass, is(equalTo("className")));
    }

    @Test
    public void extractsBasicTypesPackageName() {
        Request request = generateWrapperClassForBasicType(ARBITRARY_NAME, "my.package.className");
        assertThat(request.nameOfBasicTypesPackage, is(equalTo("my.package")));
    }

    @Test
    public void extractsNoBasicTypePackageNameForPrimitiveDataType() {
        Request request = generateWrapperClassForBasicType(ARBITRARY_NAME, "int");
        assertThat(request.nameOfBasicTypesPackage, is(nullValue()));
    }

    @Test
    public void extractsBasicTypeClassName() {
        Request request = generateWrapperClassForBasicType(ARBITRARY_NAME, "my.package.className");
        assertThat(request.nameOfBasicTypeOrItsClass, is(equalTo("className")));
    }

    @Test
    public void usesBasicTypeOfPrimitiveDataType() {
        Request request = generateWrapperClassForBasicType(ARBITRARY_NAME, "int");
        assertThat(request.nameOfBasicTypeOrItsClass, is(equalTo("int")));
    }

    @Test
    public void printsNiceString() {
        Request request = generateWrapperClassForBasicType("wrapper.class", "basic.type");
        assertThat(request,
                hasToString(equalTo("Generate the wrapper class wrapper.class for the basic type basic.type.")));
    }
}
