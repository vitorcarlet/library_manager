package com.ifc.work.rest;

import com.ifc.work.requests.loanBook.ReturnLoanBook;
import com.ifc.work.requests.loanBook.UpdateLoanBookRequest;
import com.ifc.work.requests.loanBook.loanBookRequest;
import com.ifc.work.requests.user.SignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/loan")
public interface BookLoanRest {

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> loanBook(@RequestBody loanBookRequest request);

    @PostMapping(path="/return")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> returnLoanBook(@RequestBody ReturnLoanBook request);

    @PostMapping(path= "/update")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> updateLoanBook(@RequestBody loanBookRequest request);

}
