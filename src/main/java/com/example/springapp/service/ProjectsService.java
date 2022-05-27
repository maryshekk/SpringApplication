package com.example.springapp.service;

import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.entity.EmployeesEntity;
import com.example.springapp.entity.ProjectsEntity;
import com.example.springapp.exception.DepartmentDoesNotFoundException;
import com.example.springapp.exception.ProjectAlreadyExistsException;
import com.example.springapp.exception.ProjectDoesNotFoundException;
import com.example.springapp.exception.WrongIdException;
import com.example.springapp.repository.DepartmentsRepo;
import com.example.springapp.repository.ProjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectsService {

    @Autowired
    private ProjectsRepo projectsRepo;

    @Autowired
    private DepartmentsRepo departmentsRepo;

    public List<ProjectsEntity> getByDepartmentName(String name) throws ProjectDoesNotFoundException {
        List<ProjectsEntity> projects = (List<ProjectsEntity>) projectsRepo.listProjectsByDepartmentName(name);
//        if (projects.isEmpty())
//        {
//            throw new ProjectDoesNotFoundException("no projects");
//        }
        return projects;
    }

    public List<EmployeesEntity> listEmployees(Long id) throws ProjectDoesNotFoundException
    {
        ProjectsEntity p = projectsRepo.findProjectsEntityById(id);
        if (p == null)
        {
            throw new ProjectDoesNotFoundException("no such project");
        }
        return projectsRepo.listEmployees(id);
    }
    public List<ProjectsEntity> listProjects() throws ProjectDoesNotFoundException {
        List<ProjectsEntity> projects = (List<ProjectsEntity>) projectsRepo.findAll();
        if (projects.isEmpty())
        {
            throw new ProjectDoesNotFoundException("no projects");
        }
        return projects;
    }

    public ProjectsEntity addProject(ProjectsEntity project) throws ProjectAlreadyExistsException, WrongIdException {
        if (projectsRepo.findProjectsEntityById(project.getId()) != null)
        {
            throw new WrongIdException("id is already used");
        }
        if (projectsRepo.findByName(project.getName()) != null)
        {
            throw new ProjectAlreadyExistsException("project already exists!");
        }
        return projectsRepo.save(project);
    }

    public ProjectsEntity getOneProject(Long id) throws ProjectDoesNotFoundException
    {
        ProjectsEntity project = projectsRepo.findById(id).orElse(null);
        if (project == null)
        {
            throw new ProjectDoesNotFoundException("project does not found!");
        }
        return project;
    }

    public Long deleteProject(Long id) throws ProjectDoesNotFoundException {
        ProjectsEntity project = projectsRepo.findProjectsEntityById(id);
        if (project == null)
        {
            throw new ProjectDoesNotFoundException("project does not found!");
        }
        projectsRepo.deleteById(id);
        return id;
    }
//
    public Long setDepartmentId(Long projectId, String name) throws ProjectDoesNotFoundException, DepartmentDoesNotFoundException {
        ProjectsEntity project = projectsRepo.findProjectsEntityById(projectId);
        if (project == null)
        {
            throw new ProjectDoesNotFoundException("project does not found!");
        }
        DepartmentsEntity dep = departmentsRepo.findByName(name);
        if (dep == null)
        {
            throw new DepartmentDoesNotFoundException("no such department");
        }
        project.setDepartmentId(dep.getId());
        projectsRepo.save(project);
        return dep.getId();
    }
}
