/*
 * This file is generated by jOOQ.
 */
package org.jooq.schema.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record6;
import org.jooq.Row6;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.schema.tables.Relationships;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class RelationshipsRecord extends UpdatableRecordImpl<RelationshipsRecord> implements Record6<String, String, String, String, String, String> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.relationships.relationship_id</code>.
     */
    public void setRelationshipId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.relationships.relationship_id</code>.
     */
    public String getRelationshipId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.relationships.class_id_from</code>.
     */
    public void setClassIdFrom(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.relationships.class_id_from</code>.
     */
    public String getClassIdFrom() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.relationships.class_id_to</code>.
     */
    public void setClassIdTo(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.relationships.class_id_to</code>.
     */
    public String getClassIdTo() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.relationships.type</code>.
     */
    public void setType(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.relationships.type</code>.
     */
    public String getType() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.relationships.project_id</code>.
     */
    public void setProjectId(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.relationships.project_id</code>.
     */
    public String getProjectId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.relationships.label</code>.
     */
    public void setLabel(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.relationships.label</code>.
     */
    public String getLabel() {
        return (String) get(5);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record6 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row6<String, String, String, String, String, String> fieldsRow() {
        return (Row6) super.fieldsRow();
    }

    @Override
    public Row6<String, String, String, String, String, String> valuesRow() {
        return (Row6) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Relationships.RELATIONSHIPS.RELATIONSHIP_ID;
    }

    @Override
    public Field<String> field2() {
        return Relationships.RELATIONSHIPS.CLASS_ID_FROM;
    }

    @Override
    public Field<String> field3() {
        return Relationships.RELATIONSHIPS.CLASS_ID_TO;
    }

    @Override
    public Field<String> field4() {
        return Relationships.RELATIONSHIPS.TYPE;
    }

    @Override
    public Field<String> field5() {
        return Relationships.RELATIONSHIPS.PROJECT_ID;
    }

    @Override
    public Field<String> field6() {
        return Relationships.RELATIONSHIPS.LABEL;
    }

    @Override
    public String component1() {
        return getRelationshipId();
    }

    @Override
    public String component2() {
        return getClassIdFrom();
    }

    @Override
    public String component3() {
        return getClassIdTo();
    }

    @Override
    public String component4() {
        return getType();
    }

    @Override
    public String component5() {
        return getProjectId();
    }

    @Override
    public String component6() {
        return getLabel();
    }

    @Override
    public String value1() {
        return getRelationshipId();
    }

    @Override
    public String value2() {
        return getClassIdFrom();
    }

    @Override
    public String value3() {
        return getClassIdTo();
    }

    @Override
    public String value4() {
        return getType();
    }

    @Override
    public String value5() {
        return getProjectId();
    }

    @Override
    public String value6() {
        return getLabel();
    }

    @Override
    public RelationshipsRecord value1(String value) {
        setRelationshipId(value);
        return this;
    }

    @Override
    public RelationshipsRecord value2(String value) {
        setClassIdFrom(value);
        return this;
    }

    @Override
    public RelationshipsRecord value3(String value) {
        setClassIdTo(value);
        return this;
    }

    @Override
    public RelationshipsRecord value4(String value) {
        setType(value);
        return this;
    }

    @Override
    public RelationshipsRecord value5(String value) {
        setProjectId(value);
        return this;
    }

    @Override
    public RelationshipsRecord value6(String value) {
        setLabel(value);
        return this;
    }

    @Override
    public RelationshipsRecord values(String value1, String value2, String value3, String value4, String value5, String value6) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached RelationshipsRecord
     */
    public RelationshipsRecord() {
        super(Relationships.RELATIONSHIPS);
    }

    /**
     * Create a detached, initialised RelationshipsRecord
     */
    public RelationshipsRecord(String relationshipId, String classIdFrom, String classIdTo, String type, String projectId, String label) {
        super(Relationships.RELATIONSHIPS);

        setRelationshipId(relationshipId);
        setClassIdFrom(classIdFrom);
        setClassIdTo(classIdTo);
        setType(type);
        setProjectId(projectId);
        setLabel(label);
    }
}
