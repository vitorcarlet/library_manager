package com.ifc.work.requests.loanBook;

import com.ifc.work.dtos.BookDto;


public class loanBookRequest {


    private Long userId;
    private long bookId;
    private int loanDays;

    public Long getUserId() {
        return userId;
    }


    public long getBookId() {
        return bookId;
    }

    public int getLoanDays() {
        return loanDays;
    }


}
