package com.example.papyrus;

import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private List<Livro> livros = new ArrayList<>();

    public LivroController() {
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954));
        livros.add(new Livro("Dom Quixote", "Miguel de Cervantes", 1605));
        livros.add(new Livro("1984", "George Orwell", 1949));
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> response = Map.of(
                "mensagem", "Seja bem-vindo ao Papyrus!",
                "timestamp", LocalDateTime.now(),
                "totalLivros", livros.size());
        return ResponseEntity.ok(response);
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
    public ResponseEntity<List<String>> getTitulos() {
        List<String> titulos = livros.stream()
                .map(livro -> livro.getTitulo())
                .toList();

        if (titulos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(titulos);
    }

    @GetMapping("/autores")
    public List<String> getAutores() {
        List<String> autoresUnicos = livros.stream()
                .map(livro -> livro.getAutor())
                .distinct()
                .toList();
        return autoresUnicos;
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro novoLivro) {
        livros.add(novoLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoLivro);
    }

    @GetMapping("/por-ano/{ano}")
    public ResponseEntity<List<Livro>> getLivrosPorAno(@PathVariable int ano) {
        List<Livro> livrosPorAno = livros.stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();

        if (livrosPorAno.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livrosPorAno);
    }

    @GetMapping("/desatualizados/{ano}")
    public ResponseEntity<List<Livro>> getLivrosDesatualizados(@PathVariable int ano) {
        List<Livro> anteriores = livros.stream()
                .filter(livro -> livro.getAno() < ano)
                .toList();

        if (anteriores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anteriores);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Livro>> filtrarPorAutorEAno(@RequestParam String autor, @RequestParam int ano) {
        List<Livro> filtrados = livros.stream()
                .filter(livro -> livro.getAutor().equals(autor) && livro.getAno() == ano)
                .toList();

        if (filtrados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filtrados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable String id, @RequestBody Livro dadosAtualizados) {
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

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable String id) {
        boolean removido = livros.removeIf(livro -> livro.getId().equals(id));

        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
