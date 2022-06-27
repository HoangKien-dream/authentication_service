package com.example.authenservice.service;

import com.example.authenservice.entity.Permission;
import com.example.authenservice.entity.Role;
import com.example.authenservice.repository.PermissionRepository;
import com.example.authenservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    public Permission save(Permission permission){
        return permissionRepository.save(permission);
    }

    public List<Permission> getAll(){
        return permissionRepository.findAll();
    }

}
