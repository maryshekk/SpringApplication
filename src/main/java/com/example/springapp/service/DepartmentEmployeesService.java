package com.example.springapp.service;

import com.example.springapp.entity.DepartmentEmployeesEntity;
import com.example.springapp.exception.NoteAlreadyExistsException;
import com.example.springapp.exception.WrongIdException;
import com.example.springapp.repository.DepartmentEmployeesRepo;
import com.example.springapp.repository.DepartmentsRepo;
import com.example.springapp.repository.EmployeesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentEmployeesService {
    @Autowired
    private DepartmentEmployeesRepo departmentEmployeesRepo;

    @Autowired
    private DepartmentsRepo departmentsRepo;

    @Autowired
    private EmployeesRepo employeesRepo;

    public DepartmentEmployeesEntity addNote(DepartmentEmployeesEntity note) throws WrongIdException, NoteAlreadyExistsException {
        if (departmentEmployeesRepo.findDepartmentEmployeesEntityById(note.getId()) != null)
        {
            throw new WrongIdException("id is already used");
        }
        if (departmentEmployeesRepo.findDepartmentEmployeesEntityByEmployeeIdAndDepartmentId(
                note.getEmployeeId(), note.getDepartmentId()) != null)
        {
            throw new NoteAlreadyExistsException("note already exists!");
        }
        return departmentEmployeesRepo.save(note);

    }

    public List<DepartmentEmployeesEntity> getAll()
    {
        return (List<DepartmentEmployeesEntity>) departmentEmployeesRepo.findAll();
    }

    public DepartmentEmployeesEntity getNote(Long id) throws WrongIdException {
        DepartmentEmployeesEntity note = departmentEmployeesRepo.findDepartmentEmployeesEntityById(id);
        if (note == null)
        {
            throw new WrongIdException("there no note with such id");
        }
        return note;
    }

    public Long deleteNote(Long id) throws WrongIdException {
        DepartmentEmployeesEntity note = departmentEmployeesRepo.findDepartmentEmployeesEntityById(id);
        if (note == null)
        {
            throw new WrongIdException("there no note with such id");
        }
        departmentEmployeesRepo.delete(note);
        return id;
    }

}
