package com.example.SSHdemo.resource.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity //注解指名这是一个实体Bean
@Table(name="user") //@Table指定了Entity所要映射带数据库表, name用来指定映射表的表名
@Data  // @Data会在编译时自动生成setter和getter方法,toString()方法，并重写equals和hashcode方法
public class User {

    //@Id  hibernate的特征，必须要一个id不然会报错
    @Id //注意在MySQL中，这个ID也必须设置为自动增长，如果是int类型的话
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id") //用来跟表的字段做个映射，如果名字相同可以不写@Column
    private Integer id;

    @Column(name = "username",unique = true)
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="create_date")
    private Date create_date;

    @Column(name="last_date")
    private Date last_date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreate_date() {
        return create_date;
    }

    public void setCreate_date(Date create_date) {
        this.create_date = create_date;
    }

    public Date getLast_date() {
        return last_date;
    }

    public void setLast_date(Date last_date) {
        this.last_date = last_date;
    }
}
