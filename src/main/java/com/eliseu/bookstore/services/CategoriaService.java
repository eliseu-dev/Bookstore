package com.eliseu.bookstore.services;

import com.eliseu.bookstore.domain.Categoria;
import com.eliseu.bookstore.dtos.CategoriaDTO;
import com.eliseu.bookstore.repositories.CategoriaRepository;
import com.eliseu.bookstore.services.exceptions.DataIntegrityViolationException;
import com.eliseu.bookstore.services.exceptions.ObjectNotFoundExceptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria findById(Integer id){
        Optional<Categoria> obj = categoriaRepository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundExceptions("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria create(Categoria categoria){
        categoria.setId(null);
        return categoriaRepository.save(categoria);
    }

    public Categoria update(Integer id, CategoriaDTO categoriaDTO){
        Categoria categoria = findById(id);
            categoria.setNome(categoriaDTO.getNome() + "");
            categoria.setDescricao(categoriaDTO.getDescricao() + "");
        return categoriaRepository.save(categoria);
    }
    public void delete(Integer id){
        findById(id);
        try{
            categoriaRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DataIntegrityViolationException("Categoria não pode ser deletada pois possui livros associados");
        }

    }
}
