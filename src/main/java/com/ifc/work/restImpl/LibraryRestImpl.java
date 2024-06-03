package com.ifc.work.restImpl;

import com.ifc.work.dtos.UserDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.requests.library.UserObserverRequest;
import com.ifc.work.rest.LibraryRest;
import com.ifc.work.services.LibraryService;
import com.ifc.work.utils.LibraryUtils;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class LibraryRestImpl implements LibraryRest {

    @Autowired
    private LibraryService libraryService;

    @Override
    public ResponseEntity<Void> addObserverToLibrary(Long libraryId, UserDto userDto) {
        try {
            libraryService.addObserverToLibrary(libraryId, userDto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> removeObserverFromLibrary(Long libraryId, UserDto userDto) {
        try {
            libraryService.removeObserverFromLibrary(libraryId, userDto);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> notifyObserversInLibrary(Long libraryId, BookEntity book) {
        try {
            libraryService.notifyObserversInLibrary(libraryId, book);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> addBookToLibrary(Long libraryId, BookEntity book) {
        try {
            libraryService.addBookToLibrary(libraryId, book);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> removeBookFromLibrary(Long libraryId, BookEntity book) {
        try {
            libraryService.removeBookFromLibrary(libraryId, book);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> addUserToLibrary(Long libraryId, UserEntity user) {
        try {
            libraryService.addUserToLibrary(libraryId, user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<Void> removeUserFromLibrary(Long libraryId, UserEntity user) {
        try {
            libraryService.removeUserFromLibrary(libraryId, user);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<List<BookWrapper>> getBooks(Long libraryId) {
        try {
            return libraryService.getBooks(libraryId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<List<UserWrapper>> getUsers(Long libraryId) {
        try {
            return libraryService.getUsers(libraryId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
