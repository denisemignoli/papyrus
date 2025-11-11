package com.example.papyrus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/livros")
public class Controller {
    private List<Livro> livros = new ArrayList<>();

    public Controller() {
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954));
        livros.add(new Livro("Dom Quixote", "Miguel de Cervantes", 1605));
        livros.add(new Livro("1984", "George Orwell", 1949));
    }

    @GetMapping("/bem-vindo")
    public String home() {
        return "Seja bem-vindo ao Papyrus!";
    }

    @GetMapping
    public List<Livro> getLivros() {
        return livros;
    }

    @GetMapping("/{id}")
    public Livro getByID(@PathVariable String id) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;
    }
}
