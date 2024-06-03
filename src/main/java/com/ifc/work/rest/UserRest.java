package com.ifc.work.rest;

import com.ifc.work.requests.user.SignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/usuarios")
public interface UserRest {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> signUp(@RequestBody SignUpRequest request);

    @GetMapping("/admin")
    public String getAdmin();

    @GetMapping("/user")
    public String getUser();
}
