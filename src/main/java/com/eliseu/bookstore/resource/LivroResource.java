package com.eliseu.bookstore.resource;

import com.eliseu.bookstore.domain.Categoria;
import com.eliseu.bookstore.domain.Livro;
import com.eliseu.bookstore.dtos.CategoriaDTO;
import com.eliseu.bookstore.dtos.LivroDTO;
import com.eliseu.bookstore.services.CategoriaService;
import com.eliseu.bookstore.services.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin("*")
@RestController
@RequestMapping("/livros")
public class LivroResource {
    @Autowired
    private LivroService livroService;


    @GetMapping(value = "/{id}")
    public ResponseEntity<Livro> findById(@PathVariable Integer id){
        Livro obj = livroService.findById(id);
        return ResponseEntity.ok().body(obj);
    }
    @GetMapping
    public ResponseEntity<List<LivroDTO>> findAll(@RequestParam(value = "categoria", defaultValue = "0") Integer id){
        List<Livro> obj = livroService.findAll(id);
        List<LivroDTO> dtoList = obj.stream().map(objList -> new LivroDTO(objList)).collect(Collectors.toList());
        return ResponseEntity.ok().body(dtoList);
    }

    @PostMapping
    public ResponseEntity<Livro> create( @RequestParam(value = "categoria", defaultValue = "0") Integer id , @Valid @RequestBody Livro livro){
        Livro livroObj = livroService.create(id, livro);
        URI uri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/livros/{id}").buildAndExpand(livroObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Livro> update( @PathVariable Integer id, @Valid @RequestBody Livro livroObj){
        Livro livro = livroService.update(id, livroObj);
        return ResponseEntity.ok().body(livro);
    }
    @PatchMapping(value = "/{id}")
    public ResponseEntity<Livro> updatePatch(  @PathVariable Integer id, @Valid @RequestBody Livro livroObj){
        Livro livro = livroService.update(id, livroObj);
        return ResponseEntity.ok().body(livro);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        livroService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
