package com.bec.cloud.service.example.utils;

import java.util.Date;

import lombok.Data;

@Data
public class Student {
    private long id;
    private String name;
    private int age;
    private boolean sex;
    private Date birthday;
	public Student(long id, String name, int age, boolean sex, Date birthday) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.sex = sex;
		this.birthday = birthday;
	}
}
