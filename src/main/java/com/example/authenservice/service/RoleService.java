package com.example.authenservice.service;

import com.example.authenservice.entity.Permission;
import com.example.authenservice.entity.Role;
import com.example.authenservice.repository.PermissionRepository;
import com.example.authenservice.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PermissionRepository permissionRepository;
    public Role save(Role role){
        if (role != null){
            return roleRepository.save(role);
        }
        return null;
    }

    public List<Role> getAll(){
        return roleRepository.findAll();
    }

    public Role addPermissionToRole(int id, int roleId){
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role !=null){
            Permission permission =permissionRepository.findById(id).orElse(null);
            role.getPermissions().add(permission);
            return roleRepository.save(role);
        }
        return null;
    }

    public Role removePermission(int id, int roleId){
        Role role = roleRepository.findById(roleId).orElse(null);
        if (role !=null){
            Permission permission =permissionRepository.findById(id).orElse(null);
            role.getPermissions().remove(permission);
            return roleRepository.save(role);
        }
        return null;
    }
}
