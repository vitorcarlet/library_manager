package com.ifc.work.persistence;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_books")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    private String title;

    private String author;

    private int pubYear;

    private int bookCode;

    private String bookType;

    int quantity;

    Date registration_date;

    //protected BookDecorator livroEdicao;

    @ManyToMany(mappedBy = "books")
    private Set<LibraryEntity> libraries = new HashSet<>();

    public BookEntity(Long id, String title, String author, int bookCode, int quantity) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.bookCode = bookCode;
        this.quantity = quantity;
    }


}
