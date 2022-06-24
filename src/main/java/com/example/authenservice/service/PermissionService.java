package com.example.authenservice.service;

import com.example.authenservice.entity.Permission;
import com.example.authenservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PermissionService {

//    @Autowired
//    RoleRepository roleRepository;
//    public Permission save(Permission permission){
//        return permissionRepository.save(permission);
//    }
//    public Permission addPermissionToRole(String namePermission, String nameRole){
//        Role role = roleRepository.findByName(nameRole).orElse(null);
//        Permission permission =permissionRepository.findByName(namePermission).orElse(null);
//        if (permission != null){
//            permission.getRoles().add(role);
//            return permissionRepository.save(permission);
//        }
//        return null;
//    }
}
