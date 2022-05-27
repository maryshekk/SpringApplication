package com.example.springapp.repository;
import com.example.springapp.entity.DepartmentsEntity;
import com.example.springapp.entity.EmployeesEntity;
import com.example.springapp.entity.ProjectsEntity;
import com.example.springapp.model.EmployeeModel;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;



import java.util.List;
import java.util.Optional;

public interface EmployeesRepo extends CrudRepository<EmployeesEntity, Long> {

//    EmployeesEntity findEmployeesEntityByFirst_nameAndLast_nameAndPather_name

    EmployeesEntity findEmployeesEntityByFirstNameAndAndLastNameAndPatherName(String firstName,
                                                                              String lastName,
                                                                              String patherName);
    EmployeesEntity findEmployeesEntityById(Long id);

    //список работников в департаменте
    @Query("SELECT e FROM EmployeesEntity e JOIN DepartmentEmployeesEntity de " +
            "ON de.departmentId=:id AND e.id=de.employeeId")
    List<EmployeesEntity> listEmployeesEnitities(@Param("id") Long id);

    //список департаментов, в которых есть работник
    @Query("SELECT d FROM DepartmentsEntity d JOIN DepartmentEmployeesEntity de " +
            "ON de.departmentId=d.id AND de.employeeId=:id")
    List<DepartmentsEntity> listOfDepartments(@Param("id") Long id);

    //количество департаментов, в которых есть работник
    @Query("SELECT count(distinct de.departmentId) from DepartmentEmployeesEntity de " +
            "JOIN EmployeesEntity e ON e.id=:id AND e.id=de.employeeId")
    Long getCountOfDepartments(@Param("id") Long id);

    @Query("select p from ProjectsEntity p inner join EmployeesEntity e on e.id=:id " +
            "inner join DepartmentEmployeesEntity de on p.departmentId=de.departmentId where " +
            "de.employeeId=e.id")
    List<ProjectsEntity> listProjects(@Param("id") Long id);

    //удалить работника из департамента
    @Query("Delete from DepartmentEmployeesEntity de where de.employeeId=:id AND de.department.name=:name")
    void deleteDepartmentByName(@Param("id") Long id, @Param("name") String name);

}
