package com.example.papyrus.Controller;

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

import com.example.papyrus.Model.Livro;
import com.example.papyrus.Service.Acervo;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;

@RestController
@RequestMapping("/livros")
public class LivroController {
    private Acervo acervo;

    public LivroController(Acervo _acervo) {
        this.acervo = _acervo;
    }

    @GetMapping("/status")
    public ResponseEntity<Map<String, Object>> status() {
        Map<String, Object> response = Map.of(
                "mensagem", "Seja bem-vindo ao Papyrus!",
                "timestamp", LocalDateTime.now(),
                "totalLivros", acervo.getTotalDelivros());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public List<Livro> getLivros() {
        return acervo.getLivros();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Livro> getLivroByID(@PathVariable String id) {
        Livro livroEncontrado = acervo.getLivroByID(id);
        if (livroEncontrado != null) {
            return ResponseEntity.ok(livroEncontrado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/titulos")
    public ResponseEntity<List<String>> getTitulos() {
        List<String> titulos = acervo.findTitulos();
        if (titulos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(titulos);
    }

    @GetMapping("/autores")
    public List<String> getAutores() {
        List<String> autoresUnicos = acervo.findAutores();
        return autoresUnicos;
    }

    @PostMapping
    public ResponseEntity<Livro> criarLivro(@RequestBody Livro novoLivro) {
        Livro livroCriado = acervo.addLivro(novoLivro);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroCriado);
    }

    @GetMapping("/por-ano/{ano}")
    public ResponseEntity<List<Livro>> getLivrosPorAno(@PathVariable int ano) {
        List<Livro> livrosPorAno = acervo.findLivrosPorAno(ano);
        if (livrosPorAno.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(livrosPorAno);
    }

    @GetMapping("/desatualizados/{ano}")
    public ResponseEntity<List<Livro>> getLivrosDesatualizados(@PathVariable int ano) {
        List<Livro> anteriores = acervo.findLivrosDesatualizados(ano);
        if (anteriores.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(anteriores);
    }

    @GetMapping("/filtrar")
    public ResponseEntity<List<Livro>> filtrarPorAutorEAno(@RequestParam String autor, @RequestParam int ano) {
        List<Livro> filtrados = acervo.filtrarPorAutorEAno(autor, ano);
        if (filtrados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(filtrados);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Livro> atualizarLivro(@PathVariable String id, @RequestBody Livro dadosAtualizados) {
        Livro livroAtualizado = acervo.updateLivro(id, dadosAtualizados);
        if (livroAtualizado != null) {
            return ResponseEntity.ok(livroAtualizado);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarLivro(@PathVariable String id) {
        boolean removido = acervo.deleteLivro(id);
        if (removido) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
