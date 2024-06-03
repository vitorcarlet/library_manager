package com.ifc.work.rest;

import com.ifc.work.dtos.UserDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.requests.library.UserObserverRequest;
import com.ifc.work.requests.loanBook.ReturnLoanBook;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(name="/library")
public interface LibraryRest {

    @PostMapping("/{libraryId}/addObservers")
    ResponseEntity<Void> addObserverToLibrary(@PathVariable Long libraryId, @RequestBody UserDto userDto);

    @DeleteMapping("/{libraryId}/deleteObservers")
    ResponseEntity<Void> removeObserverFromLibrary(@PathVariable Long libraryId, @RequestBody UserDto userDto);

    @PostMapping("/{libraryId}/notify")
    ResponseEntity<Void> notifyObserversInLibrary(@PathVariable Long libraryId, @RequestBody BookEntity book);

    @PostMapping("/{libraryId}/books")
    ResponseEntity<Void> addBookToLibrary(@PathVariable Long libraryId, @RequestBody BookEntity book);

    @DeleteMapping("/{libraryId}/books")
    ResponseEntity<Void> removeBookFromLibrary(@PathVariable Long libraryId, @RequestBody BookEntity book);

    @PostMapping("/{libraryId}/users")
    ResponseEntity<Void> addUserToLibrary(@PathVariable Long libraryId, @RequestBody UserEntity user);

    @DeleteMapping("/{libraryId}/users")
    ResponseEntity<Void> removeUserFromLibrary(@PathVariable Long libraryId, @RequestBody UserEntity user);

    @GetMapping("/{libraryId}/getBooks")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookWrapper>> getBooks(@PathVariable Long libraryId);

    @GetMapping("/{libraryId}/getUsers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserWrapper>> getUsers(@PathVariable Long libraryId);


}
