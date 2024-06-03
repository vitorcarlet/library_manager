package com.ifc.work.restImpl;

import com.ifc.work.dtos.BookDto;
import com.ifc.work.dtos.BookLoanDto;
import com.ifc.work.persistence.BookLoanEntity;
import com.ifc.work.requests.loanBook.ReturnLoanBook;
import com.ifc.work.requests.loanBook.loanBookRequest;
import com.ifc.work.rest.BookLoanRest;
import com.ifc.work.services.BookLoanService;
import com.ifc.work.utils.LibraryUtils;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

@RestController
public class BookLoanRestImpl implements BookLoanRest {

    @Autowired
    BookLoanService bookLoanService;

    @Override
    public ResponseEntity<String> loanBook( loanBookRequest request) {
        try{

             Long userId = request.getUserId();
             BookDto bookDto = request.getBookDto();
             int loanDays= request.getLoanDays();
            return bookLoanService.loanBook(userId,bookDto,loanDays);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> returnLoanBook( ReturnLoanBook request) {
        try{
            Long userId = request.getUserId();
            BookDto bookDto = request.getBookDto();
            return bookLoanService.returnLoanBook(userId,bookDto);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }

    @Override
    public ResponseEntity<String> updateLoanBook(loanBookRequest request) {
        try{
            Long userId = request.getUserId();
            BookDto bookDto = request.getBookDto();
            int newLoanDays= request.getLoanDays();
            return bookLoanService.updateLoanBook(userId, bookDto,newLoanDays);
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
}
