package com.example.springapp.service;

import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.exception.DepartmentAlreadyExistsException;
import com.example.springapp.exception.DepartmentDoesNotFoundException;
import com.example.springapp.exception.WrongIdException;
import com.example.springapp.repository.DepartmentEmployeesRepo;
import com.example.springapp.repository.DepartmentsRepo;
import com.example.springapp.repository.ProjectsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsService {
    @Autowired
    private DepartmentsRepo departmentsRepo;

    @Autowired
    private DepartmentEmployeesRepo departmentEmployeesRepo;

    @Autowired
    private ProjectsRepo projectsRepo;

    public DepartmentsEntity addDepartment(DepartmentsEntity department) throws DepartmentAlreadyExistsException, WrongIdException {
        if (departmentsRepo.findDepartmentsEntityById(department.getId()) != null)
        {
            throw new WrongIdException("id is already used");
        }
        if(departmentsRepo.findByName(department.getName()) != null)
        {
            throw new DepartmentAlreadyExistsException("department already exists!");
        }
        return departmentsRepo.save(department);
    }

    public Long getCountOfProjects(String name) throws DepartmentDoesNotFoundException {
        DepartmentsEntity dep = departmentsRepo.findByName(name);
        if (dep == null) {
            throw new DepartmentDoesNotFoundException("no such department");
        }
        else {
            return departmentsRepo.getCountOfProjects(name);
        }
    }

    public DepartmentsEntity getOneDepartment(Long id) throws DepartmentDoesNotFoundException {
        DepartmentsEntity department = departmentsRepo.findById(id).get();
        if (department.getId() == null)
        {
            throw new DepartmentDoesNotFoundException("department does not found!");
        }
        return department;
    }

    public List<DepartmentsEntity> getALl()
    {
        return (List<DepartmentsEntity>) departmentsRepo.findAll();
    }

    public Long deleteDepartment(Long id) throws DepartmentDoesNotFoundException {
        DepartmentsEntity dep = departmentsRepo.findDepartmentsEntityById(id);
        if (dep != null) {
            departmentsRepo.deleteById(id);
            return id;
        }
        else {
            throw new DepartmentDoesNotFoundException("no such department");
        }
    }

    public Long getCount(String name) throws DepartmentDoesNotFoundException {
        DepartmentsEntity dep = departmentsRepo.findByName(name);
        if (dep != null) {
            return departmentsRepo.getCountOfEmployees(name);
        }
        else {
            throw new DepartmentDoesNotFoundException("no such department");
        }
    }
}

