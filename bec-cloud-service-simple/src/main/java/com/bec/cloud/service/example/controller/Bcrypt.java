package com.bec.cloud.service.example.controller;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Bcrypt {
	public static void main(String[] args) {
		System.out.println("passwd="+(new BCryptPasswordEncoder().encode("123456")));
	}
}
