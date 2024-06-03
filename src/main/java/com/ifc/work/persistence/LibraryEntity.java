package com.ifc.work.persistence;

import com.ifc.work.observer.user.UserObserver;
import com.ifc.work.subjects.library.LibrarySubject;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.*;

@Getter
@Setter
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_libraries")
public class LibraryEntity implements LibrarySubject {

//    private List<Observer> observers = new ArrayList();
//    private static final Biblioteca instance = new Biblioteca();
//    private List<Livro> livros = new ArrayList();
//    private List<Membro> membros = new ArrayList();

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @ManyToMany
    @JoinTable(
            name = "library_books",
            joinColumns = @JoinColumn(name = "library_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private Set<BookEntity> books = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="library_books_observers",
            joinColumns = @JoinColumn(name="library_id"),
            inverseJoinColumns = @JoinColumn(name="observer_id")
    )
    private Set<UserEntity> observers = new HashSet<>();

    @ManyToMany
    @JoinTable(
            name="library_users",
            joinColumns = @JoinColumn(name="library_id"),
            inverseJoinColumns = @JoinColumn(name="user_id")
    )
    private Set<UserEntity> users = new HashSet<>();




    @Override
    public void addObserver(UserEntity observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(UserEntity observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(BookEntity book) {
        for (UserObserver observer : observers) {
            observer.update(book);
        }
    }

    @Override
    public void notifyReturnObservers(BookEntity book, Date returnDate) {
        for (UserObserver observer : observers) {
            observer.updateReturn(book, returnDate);
        }
    }

    public void addBook(BookEntity book) {
        books.add(book);
    }


    public void removeBook(BookEntity book) {
        books.remove(book);
    }


    public void addUser(UserEntity user) {
        users.add(user);
    }


    public void removeUser(UserEntity user) {
        users.remove(user);
    }
}
