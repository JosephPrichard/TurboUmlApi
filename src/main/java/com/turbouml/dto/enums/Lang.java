package com.turbouml.dto.enums;

public enum Lang
{
    JAVA, CSHARP, CPP, OTHER;

    public static Lang stringToEnum(String lang) {
        return switch (lang) {
            case "JAVA" -> JAVA;
            case "CSHARP" -> CSHARP;
            case "CPP" -> CPP;
            case "OTHER" -> OTHER;
            default -> throw new IllegalStateException("Missing switch case");
        };
    }
}
