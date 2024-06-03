package com.ifc.work.requests.loanBook;

import com.ifc.work.dtos.BookDto;

import java.util.Date;
import java.util.List;

public class ReturnLoanBook {
    private Long userId;
    private BookDto bookDto;

    public Long getUserId() {
        return userId;
    }

    public BookDto getBookDto() {
        return bookDto;
    }
}