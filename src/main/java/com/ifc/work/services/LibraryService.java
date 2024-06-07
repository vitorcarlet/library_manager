package com.ifc.work.services;

import com.ifc.work.dtos.UserDto;
import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.persistence.BookEntity;
import com.ifc.work.persistence.UserEntity;
import com.ifc.work.wrapper.BookWrapper;
import com.ifc.work.wrapper.UserWrapper;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface LibraryService {

    /**
     * Adiciona um observador a uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param observer Observador que será notificado sobre eventos da biblioteca.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> addObserverToLibrary(Long libraryId, Long userId) throws RuntimeException;

    /**
     * Remove um observador de uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param observer Observador que será removido.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> removeObserverFromLibrary(Long libraryId, Long userId) throws RuntimeException;

    /**
     * Notifica todos os observadores cadastrados em uma biblioteca sobre um determinado livro.
     * @param libraryId Identificador da biblioteca.
     * @param book Livro a ser notificado.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> notifyObserversInLibrary(Long libraryId, Long bookId) throws RuntimeException;

    /**
     * Adiciona um livro a uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param book Livro a ser adicionado.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> addBookToLibrary(Long libraryId, Long bookId) throws RuntimeException;

    /**
     * Remove um livro de uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param book Livro a ser removido.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> removeBookFromLibrary(Long libraryId, Long bookId) throws RuntimeException;

    /**
     * Adiciona um usuário a uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param user Usuário a ser adicionado.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> addUserToLibrary(Long libraryId, Long userId) throws RuntimeException;

    /**
     * Remove um usuário de uma biblioteca.
     * @param libraryId Identificador da biblioteca.
     * @param user Usuário a ser removido.
     * @throws RuntimeException Caso a biblioteca não seja encontrada.
     */
    ResponseEntity<String> removeUserFromLibrary(Long libraryId, Long userId) throws RuntimeException;

    ResponseEntity<List<BookWrapper>> getBooks(Long libraryId);

    ResponseEntity<List<UserWrapper>> getUsers(Long libraryId);
}
