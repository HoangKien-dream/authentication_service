package com.example.authenservice.entity.dto;
import com.example.authenservice.entity.Account;
import com.example.authenservice.entity.Role;
import lombok.Data;

import java.util.Date;
import java.util.Set;

@Data
public class AccountDTO {
    private int id;
    private String username;
    private Set<Role> roles;
    private int status;
    private Date createdAt;
    private Date updatedAt;

    public AccountDTO(Account account) {
        this.id = account.getId();
        this.username = account.getUsername();
        this.roles = account.getRoles();
        this.status = account.getStatus();
        this.createdAt = account.getCreatedAt();
        this.updatedAt = account.getUpdatedAt();
    }

}
