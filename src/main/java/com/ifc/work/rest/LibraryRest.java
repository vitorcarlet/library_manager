package com.ifc.work.rest;

import com.ifc.work.dtos.UserDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.requests.library.AddBookRequest;
import com.ifc.work.requests.library.AddUserRequest;
import com.ifc.work.requests.library.LibraryAddRequest;
import com.ifc.work.requests.library.UserObserverRequest;
import com.ifc.work.requests.loanBook.ReturnLoanBook;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/library")
public interface LibraryRest {


    @PostMapping("/add/{libraryId}")
    ResponseEntity<String>createLibrary(@PathVariable Long libraryId);

    @PutMapping("/{libraryId}/edit")
    ResponseEntity<String>editLibrary(@PathVariable Long libraryId, @RequestBody LibraryAddRequest libraryAddRequest);

    @PostMapping("/remove/{libraryId}")
    ResponseEntity<String>removeLibrary(@PathVariable Long libraryId);

    @PostMapping("/{libraryId}/addObservers")
   ResponseEntity<String>addObserverToLibrary(@PathVariable Long libraryId, @RequestBody AddUserRequest userId);

    @DeleteMapping("/{libraryId}/deleteObservers")
   ResponseEntity<String>removeObserverFromLibrary(@PathVariable Long libraryId,  @RequestBody AddUserRequest userId);

    @PostMapping("/{libraryId}/notify")
   ResponseEntity<String>notifyObserversInLibrary(@PathVariable Long libraryId,  @RequestBody AddBookRequest bookId);

    @PostMapping("/{libraryId}/books")
   ResponseEntity<String>addBookToLibrary(@PathVariable Long libraryId,  @RequestBody AddBookRequest bookId);

    @DeleteMapping("/{libraryId}/books")
   ResponseEntity<String>removeBookFromLibrary(@PathVariable Long libraryId, @RequestBody AddBookRequest bookId);

    @PostMapping("/{libraryId}/users")
     ResponseEntity<String> addUserToLibrary(@PathVariable Long libraryId, @RequestBody AddUserRequest userId);

    @DeleteMapping("/{libraryId}/users")
   ResponseEntity<String>removeUserFromLibrary(@PathVariable Long libraryId, @RequestBody AddUserRequest userId);

    @GetMapping("/{libraryId}/getBooks")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<BookWrapper>> getBooks(@PathVariable Long libraryId);

    @GetMapping("/{libraryId}/getUsers")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<List<UserWrapper>> getUsers(@PathVariable Long libraryId);


}
