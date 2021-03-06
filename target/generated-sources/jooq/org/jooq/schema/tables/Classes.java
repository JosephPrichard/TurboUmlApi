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
import org.jooq.Row7;
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
import org.jooq.schema.tables.records.ClassesRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Classes extends TableImpl<ClassesRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.classes</code>
     */
    public static final Classes CLASSES = new Classes();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ClassesRecord> getRecordType() {
        return ClassesRecord.class;
    }

    /**
     * The column <code>public.classes.class_id</code>.
     */
    public final TableField<ClassesRecord, String> CLASS_ID = createField(DSL.name("class_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.classes.content_name</code>.
     */
    public final TableField<ClassesRecord, String> CONTENT_NAME = createField(DSL.name("content_name"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.classes.access</code>.
     */
    public final TableField<ClassesRecord, String> ACCESS = createField(DSL.name("access"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.classes.stereotypes</code>.
     */
    public final TableField<ClassesRecord, String[]> STEREOTYPES = createField(DSL.name("stereotypes"), SQLDataType.CLOB.getArrayDataType(), this, "");

    /**
     * The column <code>public.classes.project_id</code>.
     */
    public final TableField<ClassesRecord, String> PROJECT_ID = createField(DSL.name("project_id"), SQLDataType.CLOB.nullable(false), this, "");

    /**
     * The column <code>public.classes.x_pos</code>.
     */
    public final TableField<ClassesRecord, Integer> X_POS = createField(DSL.name("x_pos"), SQLDataType.INTEGER.nullable(false), this, "");

    /**
     * The column <code>public.classes.y_pos</code>.
     */
    public final TableField<ClassesRecord, Integer> Y_POS = createField(DSL.name("y_pos"), SQLDataType.INTEGER.nullable(false), this, "");

    private Classes(Name alias, Table<ClassesRecord> aliased) {
        this(alias, aliased, null);
    }

    private Classes(Name alias, Table<ClassesRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.classes</code> table reference
     */
    public Classes(String alias) {
        this(DSL.name(alias), CLASSES);
    }

    /**
     * Create an aliased <code>public.classes</code> table reference
     */
    public Classes(Name alias) {
        this(alias, CLASSES);
    }

    /**
     * Create a <code>public.classes</code> table reference
     */
    public Classes() {
        this(DSL.name("classes"), null);
    }

    public <O extends Record> Classes(Table<O> child, ForeignKey<O, ClassesRecord> key) {
        super(child, key, CLASSES);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<Index> getIndexes() {
        return Arrays.<Index>asList(Indexes.FKI_G);
    }

    @Override
    public UniqueKey<ClassesRecord> getPrimaryKey() {
        return Keys.CLASSES_PKEY;
    }

    @Override
    public List<UniqueKey<ClassesRecord>> getKeys() {
        return Arrays.<UniqueKey<ClassesRecord>>asList(Keys.CLASSES_PKEY);
    }

    @Override
    public List<ForeignKey<ClassesRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<ClassesRecord, ?>>asList(Keys.CLASSES__CLASSES_PROJECT_ID_FKEY);
    }

    private transient Projects _projects;

    public Projects projects() {
        if (_projects == null)
            _projects = new Projects(this, Keys.CLASSES__CLASSES_PROJECT_ID_FKEY);

        return _projects;
    }

    @Override
    public Classes as(String alias) {
        return new Classes(DSL.name(alias), this);
    }

    @Override
    public Classes as(Name alias) {
        return new Classes(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Classes rename(String name) {
        return new Classes(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public Classes rename(Name name) {
        return new Classes(name, null);
    }

    // -------------------------------------------------------------------------
    // Row7 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row7<String, String, String, String[], String, Integer, Integer> fieldsRow() {
        return (Row7) super.fieldsRow();
    }
}
