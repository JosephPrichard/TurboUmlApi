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
import org.jooq.Row10;
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
import org.jooq.schema.tables.records.MethodsRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Methods extends TableImpl<MethodsRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.methods</code>
     */
    public static final Methods METHODS = new Methods();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<MethodsRecord> getRecordType() {
        return MethodsRecord.class;
    }

    /**
     * The column <code>public.methods.method_id</code>.
     */
    public final TableField<MethodsRecord, String> METHOD_ID = createField(DSL.name("method_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.methods.content_name</code>.
     */
    public final TableField<MethodsRecord, String> CONTENT_NAME = createField(DSL.name("content_name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.methods.return_type</code>.
     */
    public final TableField<MethodsRecord, String> RETURN_TYPE = createField(DSL.name("return_type"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.methods.params</code>.
     */
    public final TableField<MethodsRecord, String> PARAMS = createField(DSL.name("params"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.methods.class_id</code>.
     */
    public final TableField<MethodsRecord, String> CLASS_ID = createField(DSL.name("class_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.methods.project_id</code>.
     */
    public final TableField<MethodsRecord, String> PROJECT_ID = createField(DSL.name("project_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.methods.content_order</code>.
     */
    public final TableField<MethodsRecord, Integer> CONTENT_ORDER = createField(DSL.name("content_order"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.methods.access</code>.
     */
    public final TableField<MethodsRecord, String> ACCESS = createField(DSL.name("access"), SQLDataType.CLOB, this, "");

    /**
     * The column <code>public.methods.abstract</code>.
     */
    public final TableField<MethodsRecord, Boolean> ABSTRACT = createField(DSL.name("abstract"), SQLDataType.BOOLEAN, this, "");

    /**
     * The column <code>public.methods.static</code>.
     */
    public final TableField<MethodsRecord, Boolean> STATIC = createField(DSL.name("static"), SQLDataType.BOOLEAN, this, "");

    private Methods(Name alias, Table<MethodsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Methods(Name alias, Table<MethodsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.methods</code> table reference
     */
    public Methods(String alias) {
        this(DSL.name(alias), METHODS);
    }

    /**
     * Create an aliased <code>public.methods</code> table reference
     */
    public Methods(Name alias) {
        this(alias, METHODS);
    }

    /**
     * Create a <code>public.methods</code> table reference
     */
    public Methods() {
        this(DSL.name("methods"), null);
    }

    public <O extends Record> Methods(Table<O> child, ForeignKey<O, MethodsRecord> key) {
        super(child, key, METHODS);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FKI_METHODS_CLASS_ID_FKEY, Indexes.FKI_METHODS_PROJECT_ID_FKEY);
    }

    @Override
    public UniqueKey<MethodsRecord> getPrimaryKey() {
        return Keys.METHODS_PKEY;
    }

    @Override
    public List<UniqueKey<MethodsRecord>> getKeys() {
        return Arrays.<UniqueKey<MethodsRecord>>asList(Keys.METHODS_PKEY);
    }

    @Override
    public List<ForeignKey<MethodsRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<MethodsRecord, ?>>asList(Keys.METHODS__METHODS_CLASS_ID_FKEY, Keys.METHODS__METHODS_PROJECT_ID_FKEY);
    }

    private transient Classes _classes;
    private transient Projects _projects;

    public Classes classes() {
        if (_classes == null)
            _classes = new Classes(this, Keys.METHODS__METHODS_CLASS_ID_FKEY);

        return _classes;
    }

    public Projects projects() {
        if (_projects == null)
            _projects = new Projects(this, Keys.METHODS__METHODS_PROJECT_ID_FKEY);

        return _projects;
    }

    @Override
    public Methods as(String alias) {
        return new Methods(DSL.name(alias), this);
    }

    @Override
    public Methods as(Name alias) {
        return new Methods(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Methods rename(String name) {
        return new Methods(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Methods rename(Name name) {
        return new Methods(name, null);
    }

    // -------------------------------------------------------------------------
    // Row10 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row10<String, String, String, String, String, String, Integer, String, Boolean, Boolean> fieldsRow() {
        return (Row10) super.fieldsRow();
    }
}
