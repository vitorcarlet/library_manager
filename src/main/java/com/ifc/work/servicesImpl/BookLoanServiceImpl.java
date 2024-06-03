package com.ifc.work.servicesImpl;

import com.ifc.work.dtos.BookDto;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.BookLoanEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.repositories.BookLoanRepository;
import com.ifc.work.repositories.BookRepository;
import com.ifc.work.repositories.UserRepository;
import com.ifc.work.requests.loanBook.loanBookRequest;
import com.ifc.work.services.BookLoanService;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class BookLoanServiceImpl implements BookLoanService {

    @Autowired
    private BookLoanRepository bookLoanRepository;

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private BookLoanEntity bookLoanEntity;

    @Autowired
    private BookRepository bookRepository;

    DateFormat mysqlDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public ResponseEntity<String> loanBook(Long userId, BookDto bookDto, int diasEmprestimo) {
        try {
            Optional<UserEntity> user = userRepository.findById(userId);
            if (user.isEmpty()) {
                return null; // Or throw an exception
            }

            Optional<BookEntity> book = bookRepository.findById(bookDto.id());
            if (book.isEmpty() || book.get().getQuantity() <= 0) {
                return LibraryUtils.getResponseEntity("Book currently unavailable", HttpStatus.OK);
            }

            BookLoanEntity bookLoan = new BookLoanEntity();
            bookLoan.setUser(user.get());
            bookLoan.setBook(book.get());

            // Define as datas de empréstimo
            LocalDate loanDate = LocalDate.now();
            LocalDate returnLoanDate = loanDate.plusDays(diasEmprestimo);

            // Converte LocalDate para java.util.Date
            java.util.Date loanDateUtil = java.sql.Date.valueOf(loanDate);
            java.util.Date returnLoanDateUtil = java.sql.Date.valueOf(returnLoanDate);

            bookLoan.setLoanDate(loanDateUtil); // Supondo que loanDates seja do tipo Date
            bookLoan.setLoanDuration(diasEmprestimo);
            bookLoan.setReturnLoanDate(returnLoanDateUtil); // Supondo que returnLoanDate seja do tipo Date

            bookLoanRepository.save(bookLoan);
            book.get().setQuantity(book.get().getQuantity() - 1);

            return LibraryUtils.getResponseEntity("Successfully Loaned", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<String> returnLoanBook(Long userId, BookDto bookDto) {
        try {
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Optional<BookEntity> bookOptional = bookRepository.findById(bookDto.id());
            if (bookOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }

            BookEntity book = bookOptional.get();
            if (book.getQuantity() <= 0) {
                return LibraryUtils.getResponseEntity("Book currently unavailable", HttpStatus.OK);
            }

            UserEntity user = userOptional.get();
            BookLoanEntity bookLoan = bookLoanRepository.findByUserIdAndBookId(user.getId(), book.getId());
            if (bookLoan == null) {
                return ResponseEntity.badRequest().body("Loan record not found");
            }

            bookLoan.setReturned(true);
            bookLoanRepository.save(bookLoan);

            synchronized (this) {
                book.setQuantity(book.getQuantity() + 1);
                bookRepository.save(book);
            }

            return ResponseEntity.ok("Book returned successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<String> updateLoanBook(Long userId, BookDto bookDto, int newDiasEmprestimo) {

        try {
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Optional<BookEntity> bookOptional = bookRepository.findById(bookDto.id());
            if (bookOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book not found");
            }

            UserEntity user = userOptional.get();
            BookEntity book = bookOptional.get();

            // Find the book loan record
            BookLoanEntity bookLoan = bookLoanRepository.findByUserIdAndBookId(user.getId(), book.getId());
            if (bookLoan == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Loan record not found");
            }

            // Calculate the new return date
            LocalDate newReturnLoanDate = LocalDate.now().plusDays(newDiasEmprestimo);

            // Convert LocalDate to java.util.Date
            java.util.Date newReturnLoanDateUtil = java.sql.Date.valueOf(newReturnLoanDate);

            // Update the return date in the loan record
            bookLoan.setReturnLoanDate(newReturnLoanDateUtil);
            bookLoan.setLoanDuration(newDiasEmprestimo);

            // Save the updated loan record
            bookLoanRepository.save(bookLoan);

            return ResponseEntity.ok("Return date updated successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

