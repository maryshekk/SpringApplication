package com.example.springapp.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "employees")
public class EmployeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "pather_name", nullable = false)
    private String patherName;

    @Column(name = "position", nullable = false)
    private String position;

    @Column(name = "salary", nullable = false)
    private Float salary;

//    @ManyToMany
//    @Cascade({
//            org.hibernate.annotations.CascadeType.SAVE_UPDATE,
//            org.hibernate.annotations.CascadeType.MERGE,
//            org.hibernate.annotations.CascadeType.PERSIST
//    })
//    @JoinTable(
//            name = "departments_employees",
//            joinColumns = @JoinColumn(name = "employeeId"),
//            inverseJoinColumns = @JoinColumn(name = "departmentId")
//    )
//    private List<DepartmentsEntity> departments;
//
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "employee")
    private List<DepartmentEmployeesEntity> departments;

    public EmployeesEntity() {
    }

    public EmployeesEntity(Long id, String firstName, String lastName,
                           String patherName, String position, Float salary) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.position = position;
        this.salary = salary;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatherName() {
        return patherName;
    }

    public void setPatherName(String patherName) {
        this.patherName = patherName;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

//    public List<DepartmentEmployeesEntity> getDepartments() {
//        return departments;
//    }
//
//    public void setDepartments(List<DepartmentEmployeesEntity> departments) {
//        this.departments = departments;
//    }
}
