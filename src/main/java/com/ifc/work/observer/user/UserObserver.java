package com.ifc.work.observer.user;

import com.ifc.work.persistence.BookEntity;

import java.util.Date;

public interface UserObserver {

    void update(BookEntity book);
    void notifyReturn(BookEntity book, Date dataDevolucao);

    void updateReturn(BookEntity book, Date returnDate);
}
