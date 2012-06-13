package com.github.stefanbirkner.noprimitives.generator;

import static com.github.stefanbirkner.noprimitives.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

public class CodeGeneratorTest {
	private final CodeGenerator generator = new CodeGenerator();

	@Test
	public void createsStringWrapper() throws Exception {
		Request request = generateWrapperClassForBasicType(
				"com.github.stefanbirkner.noprimitives.generator.Name",
				"String");
		assertCodeGeneratedForRequest("Name.java", request);
	}

	@Test
	public void createsIntWrapper() throws Exception {
		Request request = generateWrapperClassForBasicType(
				"com.github.stefanbirkner.noprimitives.generator.OrderNumber",
				"int");
		assertCodeGeneratedForRequest("OrderNumber.java", request);
	}

	@Test
	public void createsWrapperForClassWithoutPackage() throws Exception {
		Request request = generateWrapperClassForBasicType("Name", "String");
		assertCodeGeneratedForRequest("/Name.java", request);
	}

	private void assertCodeGeneratedForRequest(String resourceName,
			Request request) throws IOException {
		InputStream is = getClass().getResourceAsStream(resourceName);
		String expectedCode = IOUtils.toString(is);
		String generatedCode = generator.createCodeForRequest(request);
		assertEquals(generatedCode, expectedCode);
		assertThat(generatedCode, is(equalTo(expectedCode)));
	}
}
