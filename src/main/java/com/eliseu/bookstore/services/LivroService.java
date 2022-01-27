package com.eliseu.bookstore.services;

import com.eliseu.bookstore.domain.Categoria;
import com.eliseu.bookstore.domain.Livro;
import com.eliseu.bookstore.dtos.CategoriaDTO;
import com.eliseu.bookstore.dtos.LivroDTO;
import com.eliseu.bookstore.repositories.CategoriaRepository;
import com.eliseu.bookstore.repositories.LivroRepository;
import com.eliseu.bookstore.services.exceptions.DataIntegrityViolationException;
import com.eliseu.bookstore.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private  CategoriaService categoriaService;

    public Livro findById(Integer id){
        Optional<Livro> obj = livroRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! ID: " + id + ", Tipo: " + Livro.class.getName()));
    }

    public List<Livro> findAll(Integer id){
        categoriaService.findById(id);
        return livroRepository.findAllByCategoria(id);
    }

    public Livro create(Integer id,Livro livro){
        livro.setId(null);
        Categoria cat = categoriaService.findById(id);
        livro.setCategoria(cat);
        return livroRepository.save(livro);
    }

    public Livro update(Integer id, Livro livroObj){
        Livro livro = findById(id);
        updateData(livro, livroObj);

        return livroRepository.save(livro);
    }

    private void updateData(Livro livro, Livro livroObj) {
        if(livroObj.getNomeAutor()!= null){livro.setNomeAutor(livroObj.getNomeAutor());}
        if(livroObj.getTexto()!= null){livro.setTexto(livroObj.getTexto());}
        if(livroObj.getTitulo()!= null){livro.setTitulo(livroObj.getTitulo());}

    }

    public void delete(Integer id){
        findById(id);
            livroRepository.deleteById(id);
    }
}
