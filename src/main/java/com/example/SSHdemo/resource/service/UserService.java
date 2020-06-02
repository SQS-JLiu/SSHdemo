package com.example.SSHdemo.resource.service;

import com.example.SSHdemo.resource.entity.User;
import com.example.SSHdemo.resource.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(rollbackFor = Exception.class)
    public User getUserByUsername(String username){
        List<User> list;
        //list = userRepository.getUser1(username);
        //list = userRepository.getUser2(username);
        list = userRepository.findByUsername(username);
        if(list.size() == 1){
            return list.get(0);
        }
        return null;
    }

    @Transactional
    public Page<User> getAllUsersByStartId(Integer id, Integer currentPage, Integer pageSize){
        // spring boot 2.0推荐写法.
        // 由于Page的currentPage规定从0开始，而前端通常返回的是从1开始，需要同步一下, 故需要减1
        Pageable pageable = PageRequest.of(currentPage-1,pageSize);
        // spring boot 2.0 以前，2.0版本也适用，但是2.0版本推荐使用上面的方式
        // Pageable pageable = new PageRequest(currentPage-1,pageSize);
        return userRepository.findByIdGreaterThan(id,pageable);
    }

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }
}
