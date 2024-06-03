package com.ifc.work.restImpl;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.requests.book.BookRequest;
import com.ifc.work.rest.BookRest;
import com.ifc.work.services.BookService;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class BookRestImpl implements BookRest {

    @Autowired
    private BookService bookService;

    @Override

    public ResponseEntity<String> addBook(BookRequest request) {
        try{BookEntity bookEntity = new BookEntity();
            bookEntity.setTitle(request.getTitle());
            bookEntity.setAuthor(request.getAuthor());
            bookEntity.setPubYear(request.getPubYear());
            bookEntity.setBookCode(request.getBookCode());
            bookEntity.setBookType(request.getBookType());
            bookEntity.setQuantity(request.getQuantity());
            bookEntity.setRegistration_date(new Date());

            bookService.addBook(bookEntity);
            return ResponseEntity.ok("Book added successfully");}catch (Exception ex){
            ex.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @Override

    public ResponseEntity<String> removeBook(Long bookId) {
        bookService.removeBook(bookId);
        return ResponseEntity.ok("Book removed successfully");
    }

    @Override
    public ResponseEntity<String> editBook(Long bookId, BookRequest request) {
        BookEntity updatedBookEntity = new BookEntity();
        updatedBookEntity.setTitle(request.getTitle());
        updatedBookEntity.setAuthor(request.getAuthor());
        updatedBookEntity.setPubYear(request.getPubYear());
        updatedBookEntity.setBookCode(request.getBookCode());
        updatedBookEntity.setBookType(request.getBookType());
        updatedBookEntity.setQuantity(request.getQuantity());

        bookService.editBook(bookId, updatedBookEntity);
        return ResponseEntity.ok("Book edited successfully");
    }
}


