package com.github.stefanbirkner.semanticwrapper.generator;

import static org.apache.commons.lang3.StringUtils.uncapitalize;
import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

public abstract class ClassTemplate {
    public abstract boolean canCreateWrapperForRequest(Request request);

    public String createCodeForRequest(Request request) {
        if (canCreateWrapperForRequest(request))
            return createCodeForValidRequest(request);
        else
            throw new IllegalArgumentException("Cannot create wrapper for request " + request + ".");
    }

    private String createCodeForValidRequest(Request request) {
        String code = rawTemplate().replace("#package", packageLineForRequest(request));
        code = code.replace("#imports", importsForRequest(request));
        code = code.replace("#additionalClassMethods", additionalClassMethodsForRequest(request));
        code = code.replace("#wrapper", request.nameOfWrappersClass);
        code = code.replace("#fieldName", fieldNameForRequest(request));
        code = code.replace("#basicTypeClass", basicTypeClassForRequest(request));
        code = code.replace("#basicType", request.nameOfBasicTypeOrItsClass);
        code = code.replace("#nameOfValueMethod", nameOfValueMethodForRequest(request));
        return code.replace("#toStringTerm", toStringTermForRequest(request));
    }

    private String rawTemplate() {
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

    protected abstract CharSequence additionalClassMethodsForRequest(Request request);

    protected String fieldNameForRequest(Request request) {
        return uncapitalize(request.nameOfWrappersClass);
    }

    protected abstract CharSequence basicTypeClassForRequest(Request request);

    protected abstract CharSequence nameOfValueMethodForRequest(Request request);

    protected CharSequence toStringTermForRequest(Request request) {
        return fieldNameForRequest(request) + ".toString()";
    }
}
