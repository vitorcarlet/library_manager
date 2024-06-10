package com.ifc.work.restImpl;

import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.requests.library.AddBookRequest;
import com.ifc.work.requests.library.AddUserRequest;
import com.ifc.work.requests.library.LibraryAddRequest;
import com.ifc.work.rest.LibraryRest;
import com.ifc.work.services.LibraryService;
import com.ifc.work.utils.LibraryUtils;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import constants.LibraryConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
public class LibraryRestImpl implements LibraryRest {

    @Autowired
    private LibraryService libraryService;

    @Override
    public ResponseEntity<String> createLibrary(Long libraryId) {
        try {
            libraryService.createLibrary(libraryId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    public ResponseEntity<String> editLibrary(Long libraryId, LibraryAddRequest libraryAddRequest ) {
        try {

            Set<BookEntity> books = new HashSet<>();
            Set<UserEntity> observers = new HashSet<>();
            Set<UserEntity> users = new HashSet<>();


            return libraryService.editLibrary(books, observers, users,libraryId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> removeLibrary(Long libraryId) {
        try {
            return libraryService.removeLibrary(libraryId);
        } catch (Exception ex) {
            ex.printStackTrace();
            return LibraryUtils.getResponseEntity(LibraryConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public ResponseEntity<String> addObserverToLibrary(Long libraryId, AddUserRequest userId) {
        try {
            String userIdd = userId.getUserId();
            libraryService.addObserverToLibrary(libraryId, userIdd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeObserverFromLibrary(Long libraryId, AddUserRequest userId) {
        try {
            String userIdd = userId.getUserId();
            libraryService.removeObserverFromLibrary(libraryId, userIdd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> notifyObserversInLibrary(Long libraryId, AddBookRequest bookId) {
        try {
            String bookIdUserId = bookId.getBookId();
            libraryService.notifyObserversInLibrary(libraryId, bookIdUserId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> addBookToLibrary(Long libraryId, AddBookRequest bookId) {
        try {
            String bookIdUserId = bookId.getBookId();
            libraryService.addBookToLibrary(libraryId, bookIdUserId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeBookFromLibrary(Long libraryId, AddBookRequest bookId) {
        try {
            String bookIdUserId = bookId.getBookId();
            libraryService.removeBookFromLibrary(libraryId, bookIdUserId);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> addUserToLibrary(Long libraryId, AddUserRequest userId) {
        try {
            String userIdd = userId.getUserId();
            libraryService.addUserToLibrary(libraryId, userIdd);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    @Override
    public ResponseEntity<String> removeUserFromLibrary(Long libraryId, AddUserRequest userId) {
        try {
            String userIdd = userId.getUserId();
            libraryService.removeUserFromLibrary(libraryId, userIdd);
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
