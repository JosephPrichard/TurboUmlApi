/*
 * This file is generated by jOOQ.
 */
package org.jooq.schema.tables.records;


import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record10;
import org.jooq.Row10;
import org.jooq.impl.UpdatableRecordImpl;
import org.jooq.schema.tables.Methods;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class MethodsRecord extends UpdatableRecordImpl<MethodsRecord> implements Record10<String, String, String, String, String, String, Integer, String, Boolean, Boolean> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.methods.method_id</code>.
     */
    public void setMethodId(String value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.methods.method_id</code>.
     */
    public String getMethodId() {
        return (String) get(0);
    }

    /**
     * Setter for <code>public.methods.content_name</code>.
     */
    public void setContentName(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.methods.content_name</code>.
     */
    public String getContentName() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.methods.return_type</code>.
     */
    public void setReturnType(String value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.methods.return_type</code>.
     */
    public String getReturnType() {
        return (String) get(2);
    }

    /**
     * Setter for <code>public.methods.params</code>.
     */
    public void setParams(String value) {
        set(3, value);
    }

    /**
     * Getter for <code>public.methods.params</code>.
     */
    public String getParams() {
        return (String) get(3);
    }

    /**
     * Setter for <code>public.methods.class_id</code>.
     */
    public void setClassId(String value) {
        set(4, value);
    }

    /**
     * Getter for <code>public.methods.class_id</code>.
     */
    public String getClassId() {
        return (String) get(4);
    }

    /**
     * Setter for <code>public.methods.project_id</code>.
     */
    public void setProjectId(String value) {
        set(5, value);
    }

    /**
     * Getter for <code>public.methods.project_id</code>.
     */
    public String getProjectId() {
        return (String) get(5);
    }

    /**
     * Setter for <code>public.methods.content_order</code>.
     */
    public void setContentOrder(Integer value) {
        set(6, value);
    }

    /**
     * Getter for <code>public.methods.content_order</code>.
     */
    public Integer getContentOrder() {
        return (Integer) get(6);
    }

    /**
     * Setter for <code>public.methods.access</code>.
     */
    public void setAccess(String value) {
        set(7, value);
    }

    /**
     * Getter for <code>public.methods.access</code>.
     */
    public String getAccess() {
        return (String) get(7);
    }

    /**
     * Setter for <code>public.methods.abstract</code>.
     */
    public void setAbstract(Boolean value) {
        set(8, value);
    }

    /**
     * Getter for <code>public.methods.abstract</code>.
     */
    public Boolean getAbstract() {
        return (Boolean) get(8);
    }

    /**
     * Setter for <code>public.methods.static</code>.
     */
    public void setStatic(Boolean value) {
        set(9, value);
    }

    /**
     * Getter for <code>public.methods.static</code>.
     */
    public Boolean getStatic() {
        return (Boolean) get(9);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record10 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row10<String, String, String, String, String, String, Integer, String, Boolean, Boolean> fieldsRow() {
        return (Row10) super.fieldsRow();
    }

    @Override
    public Row10<String, String, String, String, String, String, Integer, String, Boolean, Boolean> valuesRow() {
        return (Row10) super.valuesRow();
    }

    @Override
    public Field<String> field1() {
        return Methods.METHODS.METHOD_ID;
    }

    @Override
    public Field<String> field2() {
        return Methods.METHODS.CONTENT_NAME;
    }

    @Override
    public Field<String> field3() {
        return Methods.METHODS.RETURN_TYPE;
    }

    @Override
    public Field<String> field4() {
        return Methods.METHODS.PARAMS;
    }

    @Override
    public Field<String> field5() {
        return Methods.METHODS.CLASS_ID;
    }

    @Override
    public Field<String> field6() {
        return Methods.METHODS.PROJECT_ID;
    }

    @Override
    public Field<Integer> field7() {
        return Methods.METHODS.CONTENT_ORDER;
    }

    @Override
    public Field<String> field8() {
        return Methods.METHODS.ACCESS;
    }

    @Override
    public Field<Boolean> field9() {
        return Methods.METHODS.ABSTRACT;
    }

    @Override
    public Field<Boolean> field10() {
        return Methods.METHODS.STATIC;
    }

    @Override
    public String component1() {
        return getMethodId();
    }

    @Override
    public String component2() {
        return getContentName();
    }

    @Override
    public String component3() {
        return getReturnType();
    }

    @Override
    public String component4() {
        return getParams();
    }

    @Override
    public String component5() {
        return getClassId();
    }

    @Override
    public String component6() {
        return getProjectId();
    }

    @Override
    public Integer component7() {
        return getContentOrder();
    }

    @Override
    public String component8() {
        return getAccess();
    }

    @Override
    public Boolean component9() {
        return getAbstract();
    }

    @Override
    public Boolean component10() {
        return getStatic();
    }

    @Override
    public String value1() {
        return getMethodId();
    }

    @Override
    public String value2() {
        return getContentName();
    }

    @Override
    public String value3() {
        return getReturnType();
    }

    @Override
    public String value4() {
        return getParams();
    }

    @Override
    public String value5() {
        return getClassId();
    }

    @Override
    public String value6() {
        return getProjectId();
    }

    @Override
    public Integer value7() {
        return getContentOrder();
    }

    @Override
    public String value8() {
        return getAccess();
    }

    @Override
    public Boolean value9() {
        return getAbstract();
    }

    @Override
    public Boolean value10() {
        return getStatic();
    }

    @Override
    public MethodsRecord value1(String value) {
        setMethodId(value);
        return this;
    }

    @Override
    public MethodsRecord value2(String value) {
        setContentName(value);
        return this;
    }

    @Override
    public MethodsRecord value3(String value) {
        setReturnType(value);
        return this;
    }

    @Override
    public MethodsRecord value4(String value) {
        setParams(value);
        return this;
    }

    @Override
    public MethodsRecord value5(String value) {
        setClassId(value);
        return this;
    }

    @Override
    public MethodsRecord value6(String value) {
        setProjectId(value);
        return this;
    }

    @Override
    public MethodsRecord value7(Integer value) {
        setContentOrder(value);
        return this;
    }

    @Override
    public MethodsRecord value8(String value) {
        setAccess(value);
        return this;
    }

    @Override
    public MethodsRecord value9(Boolean value) {
        setAbstract(value);
        return this;
    }

    @Override
    public MethodsRecord value10(Boolean value) {
        setStatic(value);
        return this;
    }

    @Override
    public MethodsRecord values(String value1, String value2, String value3, String value4, String value5, String value6, Integer value7, String value8, Boolean value9, Boolean value10) {
        value1(value1);
        value2(value2);
        value3(value3);
        value4(value4);
        value5(value5);
        value6(value6);
        value7(value7);
        value8(value8);
        value9(value9);
        value10(value10);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached MethodsRecord
     */
    public MethodsRecord() {
        super(Methods.METHODS);
    }

    /**
     * Create a detached, initialised MethodsRecord
     */
    public MethodsRecord(String methodId, String contentName, String returnType, String params, String classId, String projectId, Integer contentOrder, String access, Boolean abstract_, Boolean static_) {
        super(Methods.METHODS);

        setMethodId(methodId);
        setContentName(contentName);
        setReturnType(returnType);
        setParams(params);
        setClassId(classId);
        setProjectId(projectId);
        setContentOrder(contentOrder);
        setAccess(access);
        setAbstract(abstract_);
        setStatic(static_);
    }
}
