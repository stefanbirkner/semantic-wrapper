package com.github.stefanbirkner.noprimitives.generator;

import static org.apache.commons.lang3.StringUtils.substringAfterLast;
import static org.apache.commons.lang3.StringUtils.substringBeforeLast;
import static org.apache.commons.lang3.Validate.notNull;
import static org.apache.commons.lang3.builder.EqualsBuilder.reflectionEquals;
import static org.apache.commons.lang3.builder.HashCodeBuilder.reflectionHashCode;

/**
 * A single {@code Request} for the {@link CodeGenerator}. It should generate a wrapper class, which wraps an object of
 * the basic type.
 */
public class Request {
    public final String nameOfWrappersPackage;
    public final String nameOfWrappersClass;
    public final String nameOfBasicType;

    public static Request generateWrapperClassForBasicType(String nameOfWrapperClass, String nameOfBasicType) {
        return new Request(nameOfWrapperClass, nameOfBasicType);
    }

    private Request(String nameOfWrapperClass, String nameOfBasicType) {
        notNull(nameOfWrapperClass, "The name of the wrapper class is missing.");
        this.nameOfWrappersPackage = packageNameOfClass(nameOfWrapperClass);
        this.nameOfWrappersClass = nameOfClass(nameOfWrapperClass);
        this.nameOfBasicType = notNull(nameOfBasicType, "The name of the basic type is missing.");
    }

    private String packageNameOfClass(String className) {
        if (classHasPackage(className))
            return substringBeforeLast(className, ".");
        else
            return null;
    }

    private String nameOfClass(String className) {
        if (classHasPackage(className))
            return substringAfterLast(className, ".");
        else
            return className;
    }

    private boolean classHasPackage(String nameOfWrapperClass) {
        return nameOfWrapperClass.contains(".");
    }

    @Override
    public int hashCode() {
        return reflectionHashCode(this);
    }

    @Override
    public boolean equals(Object other) {
        return reflectionEquals(this, other);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Generate the wrapper class ");
        if (nameOfWrappersPackage != null) {
            sb.append(nameOfWrappersPackage);
            sb.append(".");
        }
        sb.append(nameOfWrappersClass);
        sb.append(" for the basic type ");
        sb.append(nameOfBasicType);
        sb.append(".");
        return sb.toString();
    }
}
