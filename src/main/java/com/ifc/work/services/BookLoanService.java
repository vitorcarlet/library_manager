package com.ifc.work.services;

import com.ifc.work.dtos.BookDto;
import com.ifc.work.persistence.BookLoanEntity;
import com.ifc.work.requests.loanBook.ReturnLoanBook;
import com.ifc.work.requests.loanBook.loanBookRequest;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;

public interface BookLoanService {

     ResponseEntity<String> loanBook(Long userId, long bookId, int diasEmprestimo);


     ResponseEntity<String> returnLoanBook(Long userId, long bookId );


     ResponseEntity<String> updateLoanBook(Long userId,  Long bookId, int newDiasEmprestimo);
}
