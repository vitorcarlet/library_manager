package com.ifc.work.services;

import com.ifc.work.dtos.UserDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface LibraryService {

    /**
     * Adiciona um observador a uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param userId Observador que será notificado sobre eventos da biblioteca.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> addObserverToLibrary(Long libraryId, String userId) throws RuntimeException;

    /**
     * Remove um observador de uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param userId Observador que será removido.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> removeObserverFromLibrary(Long libraryId, String userId) throws RuntimeException;

    /**
     * Notifica todos os observadores cadastrados em uma biblioteca sobre um determinado livro.
     * @param libraryId Identificador da biblioteca.
     * @param bookId Livro a ser notificado.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> notifyObserversInLibrary(Long libraryId, String bookId) throws RuntimeException;

    /**
     * Adiciona um livro a uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param bookId Livro a ser adicionado.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> addBookToLibrary(Long libraryId, String bookId) throws RuntimeException;

    /**
     * Remove um livro de uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param bookId Livro a ser removido.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> removeBookFromLibrary(Long libraryId, String bookId) throws RuntimeException;

    /**
     * Adiciona um usuário a uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param userId Usuário a ser adicionado.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> addUserToLibrary(Long libraryId, String userId) throws RuntimeException;

    /**
     * Remove um usuário de uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param userId Usuário a ser removido.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> removeUserFromLibrary(Long libraryId, String userId) throws RuntimeException;

    ResponseEntity<List<BookWrapper>> getBooks(Long libraryId);

    ResponseEntity<List<UserWrapper>> getUsers(Long libraryId);


    ResponseEntity<String> createLibrary(Long libraryId);

    ResponseEntity<String> removeLibrary(Long libraryId);

    ResponseEntity<String> editLibrary(Set<BookEntity> books, Set<UserEntity> observers, Set<UserEntity> users,Long libraryId);
}
