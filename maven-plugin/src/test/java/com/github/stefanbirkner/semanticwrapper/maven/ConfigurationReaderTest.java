package com.github.stefanbirkner.semanticwrapper.maven;

import static com.github.stefanbirkner.semanticwrapper.generator.Request.generateWrapperClassForBasicType;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.File;
import java.net.URISyntaxException;
import java.util.Collection;

import org.junit.Test;

import com.github.stefanbirkner.semanticwrapper.generator.Request;
import com.github.stefanbirkner.semanticwrapper.maven.ConfigurationReader;

public class ConfigurationReaderTest {
    private final ConfigurationReader configurationReader = new ConfigurationReader();

    @Test
    public void readsConfigurationWithoutSpaces() throws Exception {
        assertConfigurationIsParsedCorrect("configuration_without_spaces");
    }

    @Test
    public void readsConfigurationWithSpaces() throws Exception {
        assertConfigurationIsParsedCorrect("configuration_with_spaces");
    }

    private void assertConfigurationIsParsedCorrect(String configurationName) throws URISyntaxException {
        File configuration = new File(getClass().getResource(configurationName).toURI());
        Collection<Request> requests = configurationReader.requestsFromConfigurationFile(configuration);
        assertThat(requests,
                   containsInAnyOrder(equalTo(generateWrapperClassForBasicType("com.github.stefanbirkner.semanticwrapper.maven.test.Name",
                                                                               "String")),
                                      equalTo(generateWrapperClassForBasicType("com.github.stefanbirkner.semanticwrapper.maven.test.OrderNumber",
                                                                               "int"))));
    }
}
