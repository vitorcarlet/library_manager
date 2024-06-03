package com.ifc.work.subjects.library;

import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;

import java.util.Date;

public interface LibrarySubject {

    void addObserver(UserEntity observer);
    void removeObserver(UserEntity observer);
    void notifyObservers(BookEntity livro);
    void notifyReturnObservers(BookEntity livro, Date dataDevolucao);
}
