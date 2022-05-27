package com.example.springapp.controller;

import com.example.springapp.entity.EmployeesEntity;
import com.example.springapp.exception.DepartmentDoesNotFoundException;
import com.example.springapp.exception.EmployeeAlreadyExistsException;
import com.example.springapp.exception.EmployeeDoesNotFoundException;
import com.example.springapp.exception.WrongIdException;
import com.example.springapp.service.EmployeesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/employees")
public class EmployeesController {

    @Autowired
    private EmployeesService employeesService;

    @PostMapping("/add")
    public ResponseEntity hire(@RequestBody EmployeesEntity employee)
    {
        try
        {
            employeesService.hire(employee);
            return ResponseEntity.ok().body("employee hired!");
        }catch(WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (EmployeeAlreadyExistsException e)
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
            return ResponseEntity.ok(employeesService.getAll());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("error!");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOneEmployee(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(employeesService.getOneEmployee(id));
        }
        catch(EmployeeDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body("error!");
        }
    }

    @GetMapping("/getByDepartmentName") //список работников определенного департамента
    public ResponseEntity getByDepartmentName(@RequestParam String name)
    {
        try
        {
            return ResponseEntity.ok(employeesService.getByDepartmentName(name));
        }
        catch(DepartmentDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getListOfDepartments/{id}") //список департаментов, в которых есть работник
    public ResponseEntity getListOfDepartments(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(employeesService.getListOfDepartments(id));
        }
        catch(WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getCountOfDepartments/{id}") //количество департаментов, в которых есть работник
    public ResponseEntity getCountOfDepartments(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(employeesService.getCountOfDepartments(id));
        }
        catch(WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/getListOfProjects/{id}")
    public ResponseEntity getListOfProjects(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(employeesService.getListOfProjects(id));
        }
        catch(WrongIdException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity deleteEmployee(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(employeesService.deleteEmployee(id));
        }
        catch(EmployeeDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("deleteDepartment") //убрать работника из департамента по его имени
    public ResponseEntity deleteDepartment(@RequestParam Long id, @RequestParam String name)
    {
        try
        {
            return ResponseEntity.ok(employeesService.deleteDepartmentByName(id, name));
        }
        catch(EmployeeDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/addNewDepartment")
    public ResponseEntity addDepartment(@RequestParam Long id, @RequestParam Long depId)
    {
        try
        {
            return ResponseEntity.ok(employeesService.addDepartment(id, depId));
        }
        catch (EmployeeDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/setNewPosition")
    public ResponseEntity setNewPosition(@RequestParam("id") Long id, @RequestParam("position") String position)
    {
        try
        {
            return ResponseEntity.ok(employeesService.setNewPosition(id, position));
        }
        catch (EmployeeDoesNotFoundException e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        catch (Exception e)
        {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
