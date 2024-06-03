package com.ifc.work.services;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.requests.book.BookRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface BookService {

    public ResponseEntity<String> addBook( BookEntity request);


    public ResponseEntity<String> removeBook( Long bookId);


    public ResponseEntity<String> editBook(Long bookId, BookEntity bookUpdated);
}
