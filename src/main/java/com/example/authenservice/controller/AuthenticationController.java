package com.example.authenservice.controller;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.authenservice.entity.Account;
import com.example.authenservice.entity.Role;
import com.example.authenservice.entity.dto.AccountDTO;
import com.example.authenservice.entity.dto.CredentialDTO;
import com.example.authenservice.entity.dto.RegisterDTO;
import com.example.authenservice.service.AuthenticationService;
import com.example.authenservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseEntity<Object> register(@RequestBody RegisterDTO registerDTO) {
        AccountDTO account = authenticationService.saveAccount(registerDTO);
        return ResponseEntity.ok().body(account);
    }

    @RequestMapping(path = "/accounts",method = RequestMethod.PUT)
    public ResponseEntity<Object> updatedStatus(@RequestParam int id,
                                          @RequestParam int status){
        AccountDTO account = authenticationService.updated(id,status);
        return ResponseEntity.ok().body(account);
    }

    @RequestMapping(path = "/role",method = RequestMethod.PUT)
    public ResponseEntity<Object> addRole(@RequestParam String username,
                                    @RequestParam String role){
        AccountDTO account = authenticationService.addRole(username,role);
        return ResponseEntity.ok().body(account);
    }
    @RequestMapping(path = "/role",method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeRole(@RequestParam String username,
                                             @RequestParam String role){
        AccountDTO account = authenticationService.removeRole(username,role);
        return ResponseEntity.ok().body(account);
    }
    @RequestMapping(path = "/accounts",method = RequestMethod.GET)
    public ResponseEntity<Object> getAll(){
        List<AccountDTO> listAccount = authenticationService.getAll();
        return ResponseEntity.ok().body(listAccount);
    }
    @RequestMapping(value = "/token/refresh", method = RequestMethod.GET)
    public ResponseEntity<Object> refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("require token in header");
        }
        try {
            String token = authorizationHeader.replace("Bearer", "").trim();
            DecodedJWT decodedJWT = JwtUtil.getDecodedJwt(token);
            String username = decodedJWT.getSubject();
            //load account in the token
            Account account = authenticationService.getAccount(username);
            if (account == null) {
                return ResponseEntity.badRequest().body("Wrong token: Username not exist");
            }
            //now return new token
            //generate tokens
            Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
            List<String> roles = new ArrayList<>() ;
            for (Role role:
                 account.getRoles()) {
                authorities.add(new SimpleGrantedAuthority(role.getName()));
                roles.add(role.getName());
            }
            String accessToken = JwtUtil.generateToken(
                    account.getUsername(),
                    roles,
                    request.getRequestURL().toString(),
                    JwtUtil.ONE_DAY * 7);

            String refreshToken = JwtUtil.generateToken(
                    account.getUsername(),
                    null,
                    request.getRequestURL().toString(),
                    JwtUtil.ONE_DAY * 14);
            CredentialDTO credential = new CredentialDTO(accessToken, refreshToken,account.getUsername());
            return ResponseEntity.ok(credential);
        } catch (Exception ex) {
            //show error
            return ResponseEntity.internalServerError().body(ex.getMessage());
        }
    }
}