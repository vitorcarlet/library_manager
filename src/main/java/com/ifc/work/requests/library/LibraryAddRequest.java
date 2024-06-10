package com.ifc.work.requests.library;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class LibraryAddRequest {
    private Set<BookEntity> books = new HashSet<>();
    private Set<UserEntity> observers = new HashSet<>();
    private Set<UserEntity> users = new HashSet<>();

    public Set<BookEntity> getBooks() {
        return books;
    }

    public Set<UserEntity> getObservers() {
        return observers;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }
}
