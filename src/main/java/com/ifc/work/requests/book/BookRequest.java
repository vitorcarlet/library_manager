package com.ifc.work.requests.book;

import lombok.Data;

@Data
public class BookRequest {
    private String title;
    private String author;
    private int pubYear;
    private int bookCode;
    private String bookType;
    private int quantity;
}