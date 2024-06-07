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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
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
    public ResponseEntity<String> loanBook(Long userId, long bookId, int diasEmprestimo) {
        log.info("chegou no addLoan");
        try {
            UserEntity user = userRepository.findById(userId).orElseThrow(() -> new Exception("User not found"));
            BookEntity book = bookRepository.findById(bookId).orElseThrow(() -> new Exception("Book not found"));

            // Verificar se existe um empréstimo ativo para este livro e usuário
            List<BookLoanEntity> existingLoans = bookLoanRepository.findByBookAndUser(book, user);
            for (BookLoanEntity loan : existingLoans) {
                if (!loan.isReturned()) {
                    throw new Exception("Book is already loaned and not returned");
                }
            }

            // Criar novo empréstimo
            BookLoanEntity newLoan = new BookLoanEntity();
            newLoan.setUser(user);
            newLoan.setBook(book);
            newLoan.setLoanDate(new Date());
            newLoan.setLoanDuration(diasEmprestimo);
            newLoan.setReturnLoanDate(null);
            newLoan.setReturned(false);

            // Salvar o novo empréstimo
            bookLoanRepository.save(newLoan);

            return LibraryUtils.getResponseEntity("Successfully Loaned", HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    public ResponseEntity<String> returnLoanBook(Long userId, long bookId) {
        try {
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Optional<BookEntity> bookOptional = bookRepository.findById(bookId);
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
    public ResponseEntity<String> updateLoanBook(Long userId, Long bookId, int newDiasEmprestimo) {

        try {
            Optional<UserEntity> userOptional = userRepository.findById(userId);
            if (userOptional.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }

            Optional<BookEntity> bookOptional = bookRepository.findById(bookId);
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

