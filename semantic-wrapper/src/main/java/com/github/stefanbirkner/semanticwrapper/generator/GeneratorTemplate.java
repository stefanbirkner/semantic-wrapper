package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.StringUtils.uncapitalize;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;

public abstract class GeneratorTemplate {
    public String createCodeForRequest(Request request) {
        String template = classTemplate();
        String code = template.replace("#package", packageLineForRequest(request));
        code = code.replace("#imports", importsForRequest(request));
        code = code.replace("#wrapper", request.nameOfWrappersClass);
        code = code.replace("#fieldName", uncapitalize(request.nameOfWrappersClass));
        code = code.replace("#basicTypeClass", basicTypeClassForRequest(request));
        code = code.replace("#basicType", request.nameOfBasicTypeOrItsClass);
        return code.replace("#nameOfValueMethod", nameOfValueMethodForRequest(request));
    }

    private String classTemplate() {
        try {
            InputStream is = getClass().getResourceAsStream("class.template");
            return IOUtils.toString(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private String packageLineForRequest(Request request) {
        boolean noPackage = request.nameOfWrappersPackage == null;
        return noPackage ? "" : "package " + request.nameOfWrappersPackage + ";";
    }

    protected abstract CharSequence importsForRequest(Request request);

    protected abstract CharSequence basicTypeClassForRequest(Request request);

    protected abstract CharSequence nameOfValueMethodForRequest(Request request);
}
