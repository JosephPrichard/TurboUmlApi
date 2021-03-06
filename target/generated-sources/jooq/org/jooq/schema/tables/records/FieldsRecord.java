/*
 * This file is generated by jOOQ.
 */
package org.jooq.schema.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record8;
import org.jooq.Row8;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.schema.tables.Fields;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class FieldsRecord extends UpdatableRecordImpl<FieldsRecord> implements Record8<String, String, String, String, Integer, String, String, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.fields.field_id</code>.
     */
    public void setFieldId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.fields.field_id</code>.
     */
    public String getFieldId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.fields.content_name</code>.
     */
    public void setContentName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.fields.content_name</code>.
     */
    public String getContentName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.fields.data_type</code>.
     */
    public void setDataType(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.fields.data_type</code>.
     */
    public String getDataType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.fields.class_id</code>.
     */
    public void setClassId(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.fields.class_id</code>.
     */
    public String getClassId() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.fields.content_order</code>.
     */
    public void setContentOrder(Integer value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.fields.content_order</code>.
     */
    public Integer getContentOrder() {
        return (Integer) get(4);
    }

    /**
     * Setter for <code>public.fields.project_id</code>.
     */
    public void setProjectId(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.fields.project_id</code>.
     */
    public String getProjectId() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.fields.access</code>.
     */
    public void setAccess(String value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.fields.access</code>.
     */
    public String getAccess() {
        return (String) get(6);
    }

    /**
     * Setter for <code>public.fields.static</code>.
     */
    public void setStatic(Boolean value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.fields.static</code>.
     */
    public Boolean getStatic() {
        return (Boolean) get(7);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record8 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, String, String, String, Integer, String, String, Boolean> fieldsRow() {
        return (Row8) super.fieldsRow();
    }

    @Override
    public Row8<String, String, String, String, Integer, String, String, Boolean> valuesRow() {
        return (Row8) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Fields.FIELDS.FIELD_ID;
    }

    @Override
    public Field<String> field2() {
        return Fields.FIELDS.CONTENT_NAME;
    }

    @Override
    public Field<String> field3() {
        return Fields.FIELDS.DATA_TYPE;
    }

    @Override
    public Field<String> field4() {
        return Fields.FIELDS.CLASS_ID;
    }

    @Override
    public Field<Integer> field5() {
        return Fields.FIELDS.CONTENT_ORDER;
    }

    @Override
    public Field<String> field6() {
        return Fields.FIELDS.PROJECT_ID;
    }

    @Override
    public Field<String> field7() {
        return Fields.FIELDS.ACCESS;
    }

    @Override
    public Field<Boolean> field8() {
        return Fields.FIELDS.STATIC;
    }

    @Override
    public String component1() {
        return getFieldId();
    }

    @Override
    public String component2() {
        return getContentName();
    }

    @Override
    public String component3() {
        return getDataType();
    }

    @Override
    public String component4() {
        return getClassId();
    }

    @Override
    public Integer component5() {
        return getContentOrder();
    }

    @Override
    public String component6() {
        return getProjectId();
    }

    @Override
    public String component7() {
        return getAccess();
    }

    @Override
    public Boolean component8() {
        return getStatic();
    }

    @Override
    public String value1() {
        return getFieldId();
    }

    @Override
    public String value2() {
        return getContentName();
    }

    @Override
    public String value3() {
        return getDataType();
    }

    @Override
    public String value4() {
        return getClassId();
    }

    @Override
    public Integer value5() {
        return getContentOrder();
    }

    @Override
    public String value6() {
        return getProjectId();
    }

    @Override
    public String value7() {
        return getAccess();
    }

    @Override
    public Boolean value8() {
        return getStatic();
    }

    @Override
    public FieldsRecord value1(String value) {
        setFieldId(value);
        return this;
    }

    @Override
    public FieldsRecord value2(String value) {
        setContentName(value);
        return this;
    }

    @Override
    public FieldsRecord value3(String value) {
        setDataType(value);
        return this;
    }

    @Override
    public FieldsRecord value4(String value) {
        setClassId(value);
        return this;
    }

    @Override
    public FieldsRecord value5(Integer value) {
        setContentOrder(value);
        return this;
    }

    @Override
    public FieldsRecord value6(String value) {
        setProjectId(value);
        return this;
    }

    @Override
    public FieldsRecord value7(String value) {
        setAccess(value);
        return this;
    }

    @Override
    public FieldsRecord value8(Boolean value) {
        setStatic(value);
        return this;
    }

    @Override
    public FieldsRecord values(String value1, String value2, String value3, String value4, Integer value5, String value6, String value7, Boolean value8) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached FieldsRecord
     */
    public FieldsRecord() {
        super(Fields.FIELDS);
    }

    /**
     * Create a detached, initialised FieldsRecord
     */
    public FieldsRecord(String fieldId, String contentName, String dataType, String classId, Integer contentOrder, String projectId, String access, Boolean static_) {
        super(Fields.FIELDS);

        setFieldId(fieldId);
        setContentName(contentName);
        setDataType(dataType);
        setClassId(classId);
        setContentOrder(contentOrder);
        setProjectId(projectId);
        setAccess(access);
        setStatic(static_);
    }
}
