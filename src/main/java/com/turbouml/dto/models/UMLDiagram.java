package com.turbouml.dto.models;

import java.util.List;

/**
 * An entity to represent all of the data in a single diagram for a project
 * stores the data of the project from the project database table,
 * stores all class diagrams belonging to the project,
 * stores all relationships belonging to the project
 * stores all packages belonging to the project
 * stores the dimensions of the diagram canvas
 */
public class UMLDiagram
{
    public static final int MAX_X = 2000;
    public static final int MAX_Y = 2000;

    private Project project;
    private List<UMLClassDiagram> classDiagrams;
    private List<UMLRelationship> classRelationships;
    private List<UMLPackage> packages;
    private int maxX = MAX_X;
    private int maxY = MAX_Y;

    public void setMaxX(int maxX) {
        this.maxX = maxX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMaxY() {
        return maxY;
    }

    public void setMaxY(int maxY) {
        this.maxY = maxY;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public List<UMLRelationship> getClassRelationships() {
        return classRelationships;
    }

    public void setClassRelationships(List<UMLRelationship> classRelationships) {
        this.classRelationships = classRelationships;
    }

    public List<UMLPackage> getPackages() {
        return packages;
    }

    public void setPackages(List<UMLPackage> packages) {
        this.packages = packages;
    }

    public List<UMLClassDiagram> getClassDiagrams() {
        return classDiagrams;
    }

    public void setClassDiagrams(List<UMLClassDiagram> classDiagrams) {
        this.classDiagrams = classDiagrams;
    }
}
