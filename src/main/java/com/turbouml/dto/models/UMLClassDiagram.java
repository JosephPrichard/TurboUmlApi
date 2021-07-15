package com.turbouml.dto.models;

import java.util.List;

/**
 * An entity to represent all of the data in a single class on the diagram canvas,
 * stores the data of the class from the classes database table,
 * stores all the methods belonging to the class from the methods table,
 * stores all the fields belonging to the class from the fields table
 */
public class UMLClassDiagram
{
    private UMLClass umlClass;
    private List<UMLMethod> methods;
    private List<UMLField> fields;

    public UMLClass getUmlClass() {
        return umlClass;
    }

    public void setUmlClass(UMLClass umlClass) {
        this.umlClass = umlClass;
    }

    public List<UMLMethod> getMethods() {
        return methods;
    }

    public void setMethods(List<UMLMethod> methods) {
        this.methods = methods;
    }

    public List<UMLField> getFields() {
        return fields;
    }

    public void setFields(List<UMLField> fields) {
        this.fields = fields;
    }
}
