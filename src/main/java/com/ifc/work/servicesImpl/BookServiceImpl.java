package com.ifc.work.servicesImpl;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.repositories.BookRepository;
import com.ifc.work.requests.book.BookRequest;
import com.ifc.work.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    public ResponseEntity<String> addBook(BookEntity book) {
        bookRepository.save(book);
        return ResponseEntity.ok("Book added successfully");
    }

    public ResponseEntity<String> removeBook(Long bookId) {
        bookRepository.deleteById(bookId);
        return ResponseEntity.ok("Book removed successfully");
    }

    public ResponseEntity<String> editBook(Long bookId, BookEntity updatedBookEntity) {
        return bookRepository.findById(bookId).map(book -> {
            book.setTitle(updatedBookEntity.getTitle());
            book.setAuthor(updatedBookEntity.getAuthor());
            book.setPubYear(updatedBookEntity.getPubYear());
            book.setBookCode(updatedBookEntity.getBookCode());
            book.setBookType(updatedBookEntity.getBookType());
            book.setQuantity(updatedBookEntity.getQuantity());
             bookRepository.save(book);
            return ResponseEntity.ok("Book updated successfully");
        }).orElseThrow(() -> new RuntimeException("Book not found"));
    }
}
