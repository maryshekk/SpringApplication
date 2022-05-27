package com.example.springapp.repository;

import com.example.springapp.entity.DepartmentEmployeesEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentEmployeesRepo extends CrudRepository<DepartmentEmployeesEntity, Long> {
    DepartmentEmployeesEntity findDepartmentEmployeesEntityById(Long id);
    DepartmentEmployeesEntity findDepartmentEmployeesEntityByEmployeeIdAndDepartmentId(Long id1, Long id2);

    //удалить запись из таблицы
    @Query("delete from DepartmentEmployeesEntity de where de.departmentId=:depId and de.employeeId=:id")
    void deleteNote(@Param("id") Long id, @Param("depId") Long depId);

}
