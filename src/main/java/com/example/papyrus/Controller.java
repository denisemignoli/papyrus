package com.example.papyrus;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;

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
    public ResponseEntity<Livro> getLivroByID(@PathVariable String id) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                return ResponseEntity.ok(livro);
            }
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/titulos")
    public List<String> getTitulos() {
        List<String> titulos = new ArrayList<>();
        for (Livro livro : livros) {
            titulos.add(livro.getTitulo());
        }
        return titulos;
    }

    @GetMapping("/autores")
    public List<String> getAutores() {
        List<String> autoresUnicos = livros.stream()
                .map(livro -> livro.getAutor())
                .distinct()
                .toList();
        return autoresUnicos;
    }

    @PostMapping("/novoLivro")
    public boolean cadastraLivro(@RequestBody Livro novoLivro) {
        return livros.add(novoLivro);
    }

    @GetMapping("/por-ano")
    public List<Livro> getLivrosPorAno(@RequestParam int ano) {
        List<Livro> livrosPorAno = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAno() == ano) {
                livrosPorAno.add(livro);
            }
        }
        return livrosPorAno;
    }

    @GetMapping("/desatualizados/{ano}")
    public List<Livro> getDesatualizados(@PathVariable int ano) {
        List<Livro> desatualizados = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.getAno() < ano) {
                desatualizados.add(livro);
            }
        }
        return desatualizados;
    }

    @GetMapping("/especifico")
    public List<Livro> getLivrosEspecificos(@RequestParam String autor, int ano) {
        List<Livro> livrosEspecificos = livros.stream()
                .filter(livro -> livro.getAutor().equals(autor) && livro.getAno() == ano)
                .toList();
        return livrosEspecificos;
    }

    @PutMapping("updateLivro/{id}")
    public ResponseEntity<Livro> updateLivro(@PathVariable String id, @RequestBody Livro dadosAtualizados) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                livro.setTitulo(dadosAtualizados.getTitulo());
                livro.setAutor(dadosAtualizados.getAutor());
                livro.setAno(dadosAtualizados.getAno());
                return ResponseEntity.ok(livro);
            }
        }
        return ResponseEntity.notFound().build();
    }
}
