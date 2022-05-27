package com.example.springapp.controller;

import com.example.springapp.entity.ProjectsEntity;
import com.example.springapp.exception.EmployeeDoesNotFoundException;
import com.example.springapp.exception.ProjectAlreadyExistsException;
import com.example.springapp.exception.ProjectDoesNotFoundException;
import com.example.springapp.exception.WrongIdException;
import com.example.springapp.service.ProjectsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/projects")
public class ProjectsController {

    @Autowired
    private ProjectsService projectsService;

    @GetMapping("/getByDepartmentName") //проекты департамента по его имени
    public ResponseEntity getByDepartmentName(@RequestParam("name") String name)
    {
        try {
            return ResponseEntity.ok(projectsService.getByDepartmentName(name));
        }
        catch (ProjectDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping ("/getListOfEmployees/{id}") //список работников, которые занимаются этим проектом
    public ResponseEntity getListOfEmloyees(@PathVariable Long id)
    {
        try {
            return ResponseEntity.ok(projectsService.listEmployees(id));
        }
        catch (ProjectDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity getAll() {
        try {
            return ResponseEntity.ok(projectsService.listProjects());
        }
        catch (ProjectDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add")
    public ResponseEntity addProject(@RequestBody ProjectsEntity project)
    {
        try
        {
            projectsService.addProject(project);
            return ResponseEntity.ok().body("project added!");
        }
        catch(WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (ProjectAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOneProject(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(projectsService.getOneProject(id));
        }
        catch(ProjectDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("error!");
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteProject(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(projectsService.deleteProject(id));
        }
        catch(ProjectDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/setNewDepartmentByName")
    public ResponseEntity setDepartmentId(@RequestParam("id") Long projectId, @RequestParam("name") String name)
    {
        try
        {
            return ResponseEntity.ok(projectsService.setDepartmentId(projectId, name));
        }
        catch (ProjectDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
