package com.example.papyrus.Service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.example.papyrus.Model.Livro;

@Service
public class Acervo {
    private List<Livro> livros;

    public Acervo() {
        livros = new ArrayList<>();
        livros.add(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954));
        livros.add(new Livro("Dom Quixote", "Miguel de Cervantes", 1605));
        livros.add(new Livro("1984", "George Orwell", 1949));
    }

    public Object getTotalDelivros() {
        return livros.size();
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public Livro getLivroByID(String id) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;
    }

    public List<String> findTitulos() {
        return livros.stream()
                .map(livro -> livro.getTitulo())
                .toList();
    }

    public List<String> findAutores() {
        return livros.stream()
                .map(livro -> livro.getAutor())
                .distinct()
                .toList();
    }

    public Livro addLivro(Livro novoLivro) {
        livros.add(novoLivro);
        return novoLivro;
    }

    public List<Livro> findLivrosPorAno(int ano) {
        return livros.stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    public List<Livro> findLivrosDesatualizados(int ano) {
        return livros.stream()
                .filter(livro -> livro.getAno() < ano)
                .toList();
    }

    public List<Livro> filtrarPorAutorEAno(String autor, int ano) {
        return livros.stream()
                .filter(livro -> livro.getAutor().equals(autor) && livro.getAno() == ano)
                .toList();
    }

    public Livro updateLivro(String id, Livro dadosAtualizados) {
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                livro.setTitulo(dadosAtualizados.getTitulo());
                livro.setAutor(dadosAtualizados.getAutor());
                livro.setAno(dadosAtualizados.getAno());
                return livro;
            }
        }
        return null;
    }

    public boolean deleteLivro(String id) {
        return livros.removeIf(livro -> livro.getId().equals(id));
    }

}
