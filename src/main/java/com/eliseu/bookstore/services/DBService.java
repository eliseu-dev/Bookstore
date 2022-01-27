package com.eliseu.bookstore.services;

import com.eliseu.bookstore.domain.Categoria;
import com.eliseu.bookstore.domain.Livro;
import com.eliseu.bookstore.repositories.CategoriaRepository;
import com.eliseu.bookstore.repositories.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class DBService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private LivroRepository livroRepository;


    public void instanciaDB(){
        Categoria catMisterio = new Categoria( "misterio", "esse mesmo");
        Livro livro1 = new Livro("as viagens de gulliver", "Gulliver", "blabla bla", catMisterio);
        Categoria catRomance = new Categoria( "Romance", "daiskidaiô");
        Livro livro2 = new Livro("your name", "Hinata", "blabla bla", catRomance);
        Categoria catInvestigacao = new Categoria( "Investigação", "elementar");
        Livro livro3 = new Livro("sherlock holmes", "watson", "blabla bla", catInvestigacao);
        Categoria catFantasia = new Categoria( "Fantasia", "you shold not pass");
        Livro livro4 = new Livro("o senhor dos aneis", "Gandalf", "blabla bla", catFantasia);

        Categoria catEducacao = new Categoria("Educacao", "livros didaticos");
        Livro livro5 = new Livro("livro de ciencias", "cientista", "xzinho e xzão", catEducacao);
        Livro livro6 = new Livro("livro de portugues", "renilson", "a dona aranha", catEducacao);

        catMisterio.getLivros().addAll(Arrays.asList(livro1));

        this.categoriaRepository.saveAll(Arrays.asList(catMisterio,catFantasia,catInvestigacao,catRomance,catEducacao));
        this.livroRepository.saveAll(Arrays.asList(livro1,livro2,livro3,livro4,livro5,livro6));
    }
}
