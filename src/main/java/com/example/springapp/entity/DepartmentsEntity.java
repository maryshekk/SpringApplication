package com.example.springapp.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "departments")
public class DepartmentsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE,
                CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "department")
    private List<ProjectsEntity> projects;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    private List<DepartmentEmployeesEntity> employees;

    public DepartmentsEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public DepartmentsEntity() {
    }

//    public List<ProjectsEntity> getProjects() {
//        return projects;
//    }
//
//    public void setProjects(List<ProjectsEntity> projects) {
//        this.projects = projects;
//    }
//
////    public List<DepartmentEmployeesEntity> getEmployees() {
//        return employees;
//    }

//    public void setEmployees(List<DepartmentEmployeesEntity> employees) {
//        this.employees = employees;
//    }

    public DepartmentsEntity(Long id, String name, List<DepartmentEmployeesEntity> employees) {
        this.id = id;
        this.name = name;
//        this.employees = employees;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

//    public List<DepartmentEmployeesEntity> getEmployees() {
//        return employees;
//    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public void setEmployees(List<DepartmentEmployeesEntity> depEmployees) {
//        this.employees = employees;
//    }
}
