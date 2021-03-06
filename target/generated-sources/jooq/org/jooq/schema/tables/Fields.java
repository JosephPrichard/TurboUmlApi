/*
 * This file is generated by jOOQ.
 */
package org.jooq.schema.tables;


import java.util.Arrays;
import java.util.List;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Index;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row8;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;
import org.jooq.schema.Indexes;
import org.jooq.schema.Keys;
import org.jooq.schema.Public;
import org.jooq.schema.tables.records.FieldsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Fields extends TableImpl<FieldsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.fields</code>
     */
    public static final Fields FIELDS = new Fields();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<FieldsRecord> getRecordType() {
        return FieldsRecord.class;
    }

    /**
     * The column <code>public.fields.field_id</code>.
     */
    public final TableField<FieldsRecord, String> FIELD_ID = createField(DSL.name("field_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.fields.content_name</code>.
     */
    public final TableField<FieldsRecord, String> CONTENT_NAME = createField(DSL.name("content_name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.fields.data_type</code>.
     */
    public final TableField<FieldsRecord, String> DATA_TYPE = createField(DSL.name("data_type"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.fields.class_id</code>.
     */
    public final TableField<FieldsRecord, String> CLASS_ID = createField(DSL.name("class_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.fields.content_order</code>.
     */
    public final TableField<FieldsRecord, Integer> CONTENT_ORDER = createField(DSL.name("content_order"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.fields.project_id</code>.
     */
    public final TableField<FieldsRecord, String> PROJECT_ID = createField(DSL.name("project_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.fields.access</code>.
     */
    public final TableField<FieldsRecord, String> ACCESS = createField(DSL.name("access"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.fields.static</code>.
     */
    public final TableField<FieldsRecord, Boolean> STATIC = createField(DSL.name("static"), SQLDataType.BOOLEAN, this, "");

    private Fields(Name alias, Table<FieldsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Fields(Name alias, Table<FieldsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.fields</code> table reference
     */
    public Fields(String alias) {
        this(DSL.name(alias), FIELDS);
    }

    /**
     * Create an aliased <code>public.fields</code> table reference
     */
    public Fields(Name alias) {
        this(alias, FIELDS);
    }

    /**
     * Create a <code>public.fields</code> table reference
     */
    public Fields() {
        this(DSL.name("fields"), null);
    }

    public <O extends Record> Fields(Table<O> child, ForeignKey<O, FieldsRecord> key) {
        super(child, key, FIELDS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FKI_FIELDS_CLASS_ID_FKEY, Indexes.FKI_FIELDS_PROJECT_ID_FKEY);
    }

    @Override
    public UniqueKey<FieldsRecord> getPrimaryKey() {
        return Keys.FIELDS_PKEY;
    }

    @Override
    public List<UniqueKey<FieldsRecord>> getKeys() {
        return Arrays.<UniqueKey<FieldsRecord>>asList(Keys.FIELDS_PKEY);
    }

    @Override
    public List<ForeignKey<FieldsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<FieldsRecord, ?>>asList(Keys.FIELDS__CLASSES_CLASS_ID_FKEY, Keys.FIELDS__CLASSES_PROJECT_ID_FKEY);
    }

    private transient Classes _classes;
    private transient Projects _projects;

    public Classes classes() {
        if (_classes == null)
            _classes = new Classes(this, Keys.FIELDS__CLASSES_CLASS_ID_FKEY);

        return _classes;
    }

    public Projects projects() {
        if (_projects == null)
            _projects = new Projects(this, Keys.FIELDS__CLASSES_PROJECT_ID_FKEY);

        return _projects;
    }

    @Override
    public Fields as(String alias) {
        return new Fields(DSL.name(alias), this);
    }

    @Override
    public Fields as(Name alias) {
        return new Fields(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Fields rename(String name) {
        return new Fields(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Fields rename(Name name) {
        return new Fields(name, null);
    }

    // -------------------------------------------------------------------------
    // Row8 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row8<String, String, String, String, Integer, String, String, Boolean> fieldsRow() {
        return (Row8) super.fieldsRow();
    }
}
