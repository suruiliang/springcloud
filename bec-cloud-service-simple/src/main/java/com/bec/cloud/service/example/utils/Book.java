package com.bec.cloud.service.example.utils;

import lombok.Data;

@Data
public class Book {
    private int bookId;
    private String name;
    private String author;
    private float price;
    private String isbn;
    private String pubName;
    private byte[] preface;
	public Book(int bookId, String name, String author, float price, String isbn, String pubName, byte[] preface) {
		super();
		this.bookId = bookId;
		this.name = name;
		this.author = author;
		this.price = price;
		this.isbn = isbn;
		this.pubName = pubName;
		this.preface = preface;
	}
}
