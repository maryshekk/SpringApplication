package com.example.springapp.repository;

import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentsRepo extends CrudRepository<DepartmentsEntity, Long> {

    DepartmentsEntity getByName( String name);
    DepartmentsEntity findByName(String name);
    DepartmentsEntity findDepartmentsEntityById(Long id);

    //количество работников в департаменте по его имени
    @Query("SELECT count(distinct de.employeeId) from DepartmentEmployeesEntity de " +
            "JOIN DepartmentsEntity d ON d.name=:name AND d.id=de.departmentId")
    Long getCountOfEmployees(@Param("name") String name);

    //количество проектов департамента по его имени
    @Query("SELECT count(distinct p.departmentId) from ProjectsEntity p " +
            "JOIN DepartmentsEntity d ON d.name=:name AND d.id=p.departmentId")
    Long getCountOfProjects(@Param("name") String name);

}
