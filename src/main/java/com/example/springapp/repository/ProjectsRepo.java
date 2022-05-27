package com.example.springapp.repository;

import com.example.springapp.entity.EmployeesEntity;
import com.example.springapp.entity.ProjectsEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProjectsRepo extends CrudRepository<ProjectsEntity, Long> {
    ProjectsEntity findByName(String name);
    ProjectsEntity findProjectsEntityById(Long id);

    //список проектов по имени департамента
    @Query("SELECT p FROM ProjectsEntity p JOIN DepartmentsEntity d ON d.name=:name WHERE d.id=p.departmentId")
    List<ProjectsEntity> listProjectsByDepartmentName(@Param("name") String name);

    //список работинков, которые заниаются этим проектомм
    @Query("select e from EmployeesEntity e inner join ProjectsEntity p on p.id=:id " +
            "inner join DepartmentEmployeesEntity de on p.departmentId=de.departmentId where " +
            "de.employeeId=e.id")
    List<EmployeesEntity> listEmployees(@Param("id") Long id);

    //обновить id департамента, ответственного за проект
    @Query("UPDATE ProjectsEntity p SET p.departmentId=:depId WHERE p.id=:id")
    void setDepartmentId(@Param("id") Long id, @Param("depId") Long depId);

    //удалить проект
    @Query("DELETE FROM ProjectsEntity p where p.id = :id")
    void deleteById(@Param("id") Long id);
}
