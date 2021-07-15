package com.turbouml.services;

import com.turbouml.dao.*;
import com.turbouml.dto.models.UMLDiagram;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.zip.ZipOutputStream;

@Service("UMLDiagramService")
public class UMLDiagramService
{
    private final ProjectDao projectDao;
    private final ClassDiagramDao classDao;
    private final PackageDao packageDao;
    private final RelationshipDao relationshipDao;
    private final AuthService authService;

    @Autowired
    public UMLDiagramService(
        @Qualifier("ProjectDao") ProjectDao projectDao,
        @Qualifier("ClassDiagramDao") ClassDiagramDao classDao,
        @Qualifier("PackageDao") PackageDao packageDao,
        @Qualifier("RelationshipDao") RelationshipDao relationshipDao,
        @Qualifier("AuthorizationService") AuthService authService
    )
    {
        this.projectDao = projectDao;
        this.classDao = classDao;
        this.packageDao = packageDao;
        this.relationshipDao = relationshipDao;
        this.authService = authService;
    }

    public UMLDiagram retrieveDiagram(String userId, String projectId) throws SQLException
    {
        authService.authorizeProjectAccess(userId, projectId);

        var project = projectDao.findProjectById(projectId);
        var classRelationships = relationshipDao.findByProjectId(projectId);
        var packages = packageDao.findByProjectId(projectId);
        var classes = classDao.findByProjectId(projectId);

        var diagram = new UMLDiagram();
        diagram.setProject(project);
        diagram.setClassRelationships(classRelationships);
        diagram.setPackages(packages);
        diagram.setClassDiagrams(classes);

        return diagram;
    }

    public ZipOutputStream retrieveDiagramAsFiles(String userId, String projectId) throws SQLException
    {
        return null;
    }

}
