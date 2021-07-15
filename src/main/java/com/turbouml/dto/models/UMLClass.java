package com.turbouml.dto.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.turbouml.dto.enums.Access;

/**
 * Represents an entity on the classes database table
 */
public class UMLClass
{
    private String classId;
    private String contentName;
    private Access access;
    private String[] stereotypes;
    private String projectId;
    @JsonProperty("xPos")
    private int xPos;
    @JsonProperty("yPos")
    private int yPos;

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getContentName() {
        return contentName;
    }

    public void setContentName(String contentName) {
        this.contentName = contentName;
    }

    public Access getAccess() {
        return access;
    }

    public void setAccess(Access access) {
        this.access = access;
    }

    public String[] getStereotypes() {
        return stereotypes;
    }

    public void setStereotypes(String[] stereotypes) {
        this.stereotypes = stereotypes;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public int getXPos() {
        return xPos;
    }

    public void setXPos(int xPos) {
        this.xPos = xPos;
    }

    public int getYPos() {
        return yPos;
    }

    public void setYPos(int yPos) {
        this.yPos = yPos;
    }
}
