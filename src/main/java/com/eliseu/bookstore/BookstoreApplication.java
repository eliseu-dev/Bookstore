package com.eliseu.bookstore;

import com.eliseu.bookstore.domain.Categoria;
import com.eliseu.bookstore.domain.Livro;
import com.eliseu.bookstore.repositories.CategoriaRepository;
import com.eliseu.bookstore.repositories.LivroRepository;
import com.eliseu.bookstore.services.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;

@SpringBootApplication
public class BookstoreApplication  {



	public static void main(String[] args)  {
		SpringApplication.run(BookstoreApplication.class, args);
		System.out.println("teste");

	}


}
