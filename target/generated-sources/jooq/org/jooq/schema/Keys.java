/*
 * This file is generated by jOOQ.
 */
package org.jooq.schema;


import org.jooq.ForeignKey;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.DSL;
import org.jooq.impl.Internal;
import org.jooq.schema.tables.Classes;
import org.jooq.schema.tables.Fields;
import org.jooq.schema.tables.Methods;
import org.jooq.schema.tables.Packages;
import org.jooq.schema.tables.Projects;
import org.jooq.schema.tables.Relationships;
import org.jooq.schema.tables.records.ClassesRecord;
import org.jooq.schema.tables.records.FieldsRecord;
import org.jooq.schema.tables.records.MethodsRecord;
import org.jooq.schema.tables.records.PackagesRecord;
import org.jooq.schema.tables.records.ProjectsRecord;
import org.jooq.schema.tables.records.RelationshipsRecord;


/**
 * A class modelling foreign key relationships and constraints of tables in 
 * public.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ClassesRecord> CLASSES_PKEY = Internal.createUniqueKey(Classes.CLASSES, DSL.name("classes_pkey"), new TableField[] { Classes.CLASSES.CLASS_ID }, true);
    public static final UniqueKey<FieldsRecord> FIELDS_PKEY = Internal.createUniqueKey(Fields.FIELDS, DSL.name("fields_pkey"), new TableField[] { Fields.FIELDS.FIELD_ID }, true);
    public static final UniqueKey<MethodsRecord> METHODS_PKEY = Internal.createUniqueKey(Methods.METHODS, DSL.name("methods_pkey"), new TableField[] { Methods.METHODS.METHOD_ID }, true);
    public static final UniqueKey<PackagesRecord> PACKAGES_PKEY = Internal.createUniqueKey(Packages.PACKAGES, DSL.name("packages_pkey"), new TableField[] { Packages.PACKAGES.PACKAGE_ID }, true);
    public static final UniqueKey<ProjectsRecord> PROJECTS_PKEY = Internal.createUniqueKey(Projects.PROJECTS, DSL.name("projects_pkey"), new TableField[] { Projects.PROJECTS.PROJECT_ID }, true);
    public static final UniqueKey<RelationshipsRecord> RELATIONSHIPS_PKEY = Internal.createUniqueKey(Relationships.RELATIONSHIPS, DSL.name("relationships_pkey"), new TableField[] { Relationships.RELATIONSHIPS.RELATIONSHIP_ID }, true);

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------

    public static final ForeignKey<ClassesRecord, ProjectsRecord> CLASSES__CLASSES_PROJECT_ID_FKEY = Internal.createForeignKey(Classes.CLASSES, DSL.name("classes_project_id_fkey"), new TableField[] { Classes.CLASSES.PROJECT_ID }, Keys.PROJECTS_PKEY, new TableField[] { Projects.PROJECTS.PROJECT_ID }, true);
    public static final ForeignKey<FieldsRecord, ClassesRecord> FIELDS__CLASSES_CLASS_ID_FKEY = Internal.createForeignKey(Fields.FIELDS, DSL.name("classes_class_id_fkey"), new TableField[] { Fields.FIELDS.CLASS_ID }, Keys.CLASSES_PKEY, new TableField[] { Classes.CLASSES.CLASS_ID }, true);
    public static final ForeignKey<FieldsRecord, ProjectsRecord> FIELDS__CLASSES_PROJECT_ID_FKEY = Internal.createForeignKey(Fields.FIELDS, DSL.name("classes_project_id_fkey"), new TableField[] { Fields.FIELDS.PROJECT_ID }, Keys.PROJECTS_PKEY, new TableField[] { Projects.PROJECTS.PROJECT_ID }, true);
    public static final ForeignKey<MethodsRecord, ClassesRecord> METHODS__METHODS_CLASS_ID_FKEY = Internal.createForeignKey(Methods.METHODS, DSL.name("methods_class_id_fkey"), new TableField[] { Methods.METHODS.CLASS_ID }, Keys.CLASSES_PKEY, new TableField[] { Classes.CLASSES.CLASS_ID }, true);
    public static final ForeignKey<MethodsRecord, ProjectsRecord> METHODS__METHODS_PROJECT_ID_FKEY = Internal.createForeignKey(Methods.METHODS, DSL.name("methods_project_id_fkey"), new TableField[] { Methods.METHODS.PROJECT_ID }, Keys.PROJECTS_PKEY, new TableField[] { Projects.PROJECTS.PROJECT_ID }, true);
    public static final ForeignKey<RelationshipsRecord, ClassesRecord> RELATIONSHIPS__RELATIONSHIPS_CLASS_ID_FROM_FKEY = Internal.createForeignKey(Relationships.RELATIONSHIPS, DSL.name("relationships_class_id_from_fkey"), new TableField[] { Relationships.RELATIONSHIPS.CLASS_ID_FROM }, Keys.CLASSES_PKEY, new TableField[] { Classes.CLASSES.CLASS_ID }, true);
    public static final ForeignKey<RelationshipsRecord, ClassesRecord> RELATIONSHIPS__RELATIONSHIPS_CLASS_ID_TO_FKEY = Internal.createForeignKey(Relationships.RELATIONSHIPS, DSL.name("relationships_class_id_to_fkey"), new TableField[] { Relationships.RELATIONSHIPS.CLASS_ID_TO }, Keys.CLASSES_PKEY, new TableField[] { Classes.CLASSES.CLASS_ID }, true);
    public static final ForeignKey<RelationshipsRecord, ProjectsRecord> RELATIONSHIPS__RELATIONSHIPS_PROJECT_ID_FKEY = Internal.createForeignKey(Relationships.RELATIONSHIPS, DSL.name("relationships_project_id_fkey"), new TableField[] { Relationships.RELATIONSHIPS.PROJECT_ID }, Keys.PROJECTS_PKEY, new TableField[] { Projects.PROJECTS.PROJECT_ID }, true);
}
