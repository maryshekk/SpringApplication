package com.example.springapp.controller;

import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.exception.DepartmentAlreadyExistsException;
import com.example.springapp.exception.DepartmentDoesNotFoundException;
import com.example.springapp.exception.EmployeeDoesNotFoundException;
import com.example.springapp.exception.WrongIdException;
import com.example.springapp.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/departments")
public class DepartmentsController {
    @Autowired
    private DepartmentsService departmentsService;

    @PostMapping("/add")
    public ResponseEntity addDepartment(@RequestBody DepartmentsEntity department)
    {
        try
        {
            departmentsService.addDepartment(department);
            return ResponseEntity.ok().body("department is added!");
        }
        catch(WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (DepartmentAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCount")
    public ResponseEntity getCountOfEmployees(@RequestParam("name") String name)
    {
        try
        {
            return ResponseEntity.ok(departmentsService.getCount(name));
        }
        catch(DepartmentDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOneDepartment(@PathVariable("id") Long id) throws DepartmentDoesNotFoundException {
        try
        {
            return new ResponseEntity<>(departmentsService.getOneDepartment(id), HttpStatus.OK);
        }
        catch(DepartmentDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("error");
        }
    }

    @GetMapping("/getCountOfProjects")
    public ResponseEntity getCountOfProjectsByDepName(@RequestParam("name") String name) throws DepartmentDoesNotFoundException {
        try
        {
            return new ResponseEntity<>(departmentsService.getCountOfProjects(name), HttpStatus.OK);
        }
        catch(DepartmentDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch(Exception e)
        {
            return ResponseEntity.badRequest().body("error");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll()
    {
        try
        {
            return ResponseEntity.ok(departmentsService.getALl());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteDepartment(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(departmentsService.deleteDepartment(id));
        }
        catch (DepartmentDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
