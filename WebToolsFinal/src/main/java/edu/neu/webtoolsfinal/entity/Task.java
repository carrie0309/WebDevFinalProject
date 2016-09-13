package edu.neu.webtoolsfinal.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Tasks")
public class Task implements Serializable {

    private int taskId;
    private User fromUser;
    private String category;
    private String weight;
    private Address toAddress;
    private Address fromAddress;
    private User acceptCompany;
    private Date taskDate;
//    private Set<User> companyList = new HashSet<>();

    @Id
    @Column(name = "Id")
    @GeneratedValue
    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public User getFromUser() {
        return fromUser;
    }

    public void setFromUser(User fromUser) {
        this.fromUser = fromUser;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public User getAcceptCompany() {
        return acceptCompany;
    }

    public void setAcceptCompany(User acceptCompany) {
        this.acceptCompany = acceptCompany;
    }

    @Basic
    @Column(name = "Weight")
    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }
    
    @Basic
    @Column(name = "Category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Address getToAddress() {
        return toAddress;
    }

    public void setToAddress(Address toAddress) {
        this.toAddress = toAddress;
    }

    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    public Address getFromAddress() {
        return fromAddress;
    }

    public void setFromAddress(Address fromAddress) {
        this.fromAddress = fromAddress;
    }

    @Basic
    @Column(name = "TaskDate")
    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

//    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    @JoinTable(name = "TaskUser", joinColumns = {
//        @JoinColumn(name = "TaskId", nullable = false, updatable = false)},
//            inverseJoinColumns = {
//                @JoinColumn(name = "UserId",
//                        nullable = false, updatable = false)})
//    public Set<User> getCompanyList() {
//        return companyList;
//    }
//
//    public void setCompanyList(Set<User> companyList) {
//        this.companyList = companyList;
//    }
//
//    public void addCompany(User user) {
//        companyList.add(user);
//
//    }

}
