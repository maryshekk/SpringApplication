package com.example.springapp.entity;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Entity
@Table(name = "projects")
public class ProjectsEntity {

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
                            CascadeType.REFRESH})
    @JoinColumn(name = "department_id", insertable = false, updatable = false)
    private DepartmentsEntity department;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "cost", nullable = false)
    private float cost;

    @Column(name = "date_beg", nullable = false)
    private String dateBeg;

    @Column(name = "date_end", nullable = false)
    private String dateEnd;

    @Column(name = "date_end_real", nullable = false)
    private String dateEndReal;

    @Column(name = "department_id", nullable = true)
    private Long departmentId;

    public ProjectsEntity(Long id, String name, float cost,
                          String dateBeg, String dateEnd,
                          String dateEndReal, Long departmentId) {
        this.id = id;
        this.name = name;
        this.cost = cost;
        this.dateBeg = dateBeg;
        this.dateEnd = dateEnd;
        this.dateEndReal = dateEndReal;
        this.departmentId = departmentId;
    }

    public ProjectsEntity() {
    }

    public DepartmentsEntity getDepartment() {
        return department;
    }

    public void setDepartment(DepartmentsEntity department) {
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public String getDateBeg() {
        return dateBeg;
    }

    public void setDateBeg(String dateBeg) {
        this.dateBeg = dateBeg;
    }

    public String getDateEnd() {
        return dateEnd;
    }

    public void setDateEnd(String dateEnd) {
        this.dateEnd = dateEnd;
    }

    public String getDateEndReal() {
        return dateEndReal;
    }

    public void setDateEndReal(String dateEndReal) {
        this.dateEndReal = dateEndReal;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
