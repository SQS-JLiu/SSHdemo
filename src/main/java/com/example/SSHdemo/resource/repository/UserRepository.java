package com.example.SSHdemo.resource.repository;

import com.example.SSHdemo.resource.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
//JpaRepository继承PagingAndSortingRepository继承CrudRepository继承Repository
//根据继承关系,我们平时直接使用JpaRepository即可,它包含以上继承的所有方法
public interface UserRepository extends JpaRepository<User,Integer> {
    //这里给出了三种基本的自定义查询方法

    @Query(value = "select u from User u where u.username=?1") //这里的User是类User不是表名字
    List<User> getUser1(String username);

    @Query(value = "select u from User u where u.username=:username") //这里的User是类User不是表名字
    List<User> getUser2(@Param("username") String username);

    //JPA会自动根据你写的方法名去做截取findBy记住定义方法名称的时候要驼峰写法(JPA自定义方法查询)
    List<User> findByUsername(String username);  //等价于...where x.username=?1

    //分页查询格式, 请求参数中需添加Pageable, 返回Page链表
    Page<User> findByIdGreaterThan(Integer id, Pageable request);
}
