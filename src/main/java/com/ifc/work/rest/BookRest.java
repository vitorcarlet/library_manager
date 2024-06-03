package com.ifc.work.rest;

import com.ifc.work.requests.book.BookRequest;
import com.ifc.work.requests.loanBook.loanBookRequest;
import com.ifc.work.requests.user.SignUpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(path="/book")
public interface BookRest {

    @PostMapping()
    public ResponseEntity<String> addBook(@RequestBody BookRequest request);

    @DeleteMapping(path="/{bookId}")
    public ResponseEntity<String> removeBook(@PathVariable Long bookId);


    @PutMapping(path="/edit/{bookId}")
    public ResponseEntity<String> editBook(@PathVariable Long bookId, @RequestBody BookRequest request);
}
