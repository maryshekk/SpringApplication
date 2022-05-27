package com.example.springapp.entity;

import javax.persistence.*;

@Entity
@Table(name = "departments_employees")
public class DepartmentEmployeesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "department_id", nullable = false)
    private Long departmentId;

    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @ManyToOne
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private DepartmentsEntity department;

    @ManyToOne
    @JoinColumn(name = "employee_id", insertable = false, updatable = false)
    private EmployeesEntity employee;

    public DepartmentEmployeesEntity(Long id, Long departmentId, Long employeeId) {
        this.id = id;
        this.departmentId = departmentId;
        this.employeeId = employeeId;
    }

    public DepartmentEmployeesEntity() {}

    public Long getId() {
        return id;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDepartmentId(Long department_id) {
        this.departmentId = department_id;
    }

    public void setEmployeeId(Long employee_id) {
        this.employeeId = employee_id;
    }
}
