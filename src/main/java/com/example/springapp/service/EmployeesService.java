package com.example.springapp.service;

import com.example.springapp.entity.DepartmentEmployeesEntity;
import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.entity.EmployeesEntity;
import com.example.springapp.entity.ProjectsEntity;
import com.example.springapp.exception.*;
import com.example.springapp.repository.DepartmentEmployeesRepo;
import com.example.springapp.repository.DepartmentsRepo;
import com.example.springapp.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeesService {

    @Autowired
    private EmployeesRepo employeesRepo;

    @Autowired
    private DepartmentsRepo departmentsRepo;

    @Autowired
    private DepartmentEmployeesRepo departmentEmployeesRepo;

    @Autowired
    private DepartmentEmployeesService departmentEmployeesService;

    public EmployeesEntity hire(EmployeesEntity employee) throws EmployeeAlreadyExistsException, WrongIdException {
        if (employeesRepo.findEmployeesEntityById(employee.getId()) != null)
        {
            throw new WrongIdException("id is already used");
        }
        if (employeesRepo.findEmployeesEntityByFirstNameAndAndLastNameAndPatherName(employee.getFirstName(),
                employee.getLastName(), employee.getPatherName()) != null)
        {
            throw new EmployeeAlreadyExistsException("employee is already hired");
        }
        return employeesRepo.save(employee);
    }

    public EmployeesEntity getOneEmployee(Long id) throws EmployeeDoesNotFoundException {
        EmployeesEntity employee = employeesRepo.findById(id).orElse(null);
        if (employee == null)
        {
            throw new EmployeeDoesNotFoundException("employee not found!");
        }
        return employee;
    }

    public List<EmployeesEntity> getAll()
    {
        return (List<EmployeesEntity>) employeesRepo.findAll();
    }

    public Long deleteEmployee(Long id) throws EmployeeDoesNotFoundException, WrongIdException {
        Optional<EmployeesEntity> dep = employeesRepo.findById(id);
        if (dep.isPresent())
        {
            employeesRepo.deleteById(id);
            return id;
        }
        else
        {
            throw new EmployeeDoesNotFoundException("no such department");
        }
    }

    public Long setNewPosition(Long id, String position) throws EmployeeDoesNotFoundException {
        EmployeesEntity employee = employeesRepo.findEmployeesEntityById(id);
        if (employee == null)
        {
            throw new EmployeeDoesNotFoundException("employee does not found!");
        }
        employee.setPosition(position);
        employeesRepo.save(employee);
        return id;
    }

    public Long addDepartment(Long id, Long depId) throws EmployeeDoesNotFoundException, NoteAlreadyExistsException, WrongIdException, DepartmentDoesNotFoundException {
        EmployeesEntity employee = employeesRepo.findEmployeesEntityById(id);
        if (employee == null)
        {
            throw new EmployeeDoesNotFoundException("employee does not found!");
        }
        DepartmentsEntity dep = departmentsRepo.findDepartmentsEntityById(depId);
        if (dep == null)
        {
            throw new DepartmentDoesNotFoundException("no such department");
        }
        DepartmentEmployeesEntity note = new DepartmentEmployeesEntity();
        note.setEmployeeId(id);
        note.setDepartmentId(depId);
        departmentEmployeesService.addNote(note);
        return depId;
    }

    public Long deleteDepartmentByName(Long id, String name) throws EmployeeDoesNotFoundException, DepartmentDoesNotFoundException {
        EmployeesEntity employee = employeesRepo.findEmployeesEntityById(id);
        if (employee == null)
        {
            throw new EmployeeDoesNotFoundException("employee does not found!");
        }
        DepartmentsEntity dep = departmentsRepo.findByName(name);
        if (dep == null)
        {
            throw new DepartmentDoesNotFoundException("no such department");
        }
        employeesRepo.deleteDepartmentByName(id, name);
    return dep.getId();
    }

    public List<EmployeesEntity> getByDepartmentName(String name) throws DepartmentDoesNotFoundException {
        DepartmentsEntity dep = departmentsRepo.findByName(name);
        if (dep == null)
        {
            throw new DepartmentDoesNotFoundException(name + " is wrong name");
        }
        return employeesRepo.listEmployeesEnitities(dep.getId());
    }

    public List<DepartmentsEntity> getListOfDepartments(Long id) throws WrongIdException
    {
        EmployeesEntity e = employeesRepo.findEmployeesEntityById(id);
        if (e == null)
        {
            throw new WrongIdException("no such employee");
        }
        return employeesRepo.listOfDepartments(id);
    }

    public Long getCountOfDepartments(Long id) throws WrongIdException
    {
        EmployeesEntity e = employeesRepo.findEmployeesEntityById(id);
        if (e == null)
        {
            throw new WrongIdException("no such employee");
        }
        return employeesRepo.getCountOfDepartments(id);
    }

    public List<ProjectsEntity> getListOfProjects(Long id) throws WrongIdException
    {
        EmployeesEntity e = employeesRepo.findEmployeesEntityById(id);
        if (e == null)
        {
            throw new WrongIdException("no such employee");
        }
        return employeesRepo.listProjects(id);
    }

}
