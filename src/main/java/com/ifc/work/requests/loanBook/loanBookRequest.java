package com.ifc.work.requests.loanBook;

import com.ifc.work.dtos.BookDto;


public class loanBookRequest {


    private Long userId;
    private BookDto bookDto;
    private int loanDays;

    public Long getUserId() {
        return userId;
    }


    public BookDto getBookDto() {
        return bookDto;
    }


    public int getLoanDays() {
        return loanDays;
    }


}
