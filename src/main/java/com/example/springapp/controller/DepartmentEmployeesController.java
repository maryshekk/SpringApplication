package com.example.springapp.controller;

import com.example.springapp.entity.DepartmentEmployeesEntity;
import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.exception.*;
import com.example.springapp.service.DepartmentEmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@RestController
@RequestMapping("/departments_employees")
public class DepartmentEmployeesController {
    @Autowired
    DepartmentEmployeesService departmentEmployeesService;

    @PostMapping("/add")
    public ResponseEntity addNote(@RequestBody DepartmentEmployeesEntity pair)
    {
        try {
            departmentEmployeesService.addNote(pair);
            return ResponseEntity.ok().body("note is added!");
        }
        catch (WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (NoteAlreadyExistsException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getNote(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(departmentEmployeesService.getNote(id));
        }
        catch (WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll()
    {
        try
        {
            return ResponseEntity.ok(departmentEmployeesService.getAll());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteNote(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(departmentEmployeesService.deleteNote(id));
        }
        catch (WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
