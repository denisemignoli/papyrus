package com.example.papyrus.service;

import java.util.List;
import org.springframework.stereotype.Service;
import com.example.papyrus.model.Livro;
import com.example.papyrus.repository.LivroRepository;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public Object getTotalDelivros() {
        return livroRepository.count();
    }

    public List<Livro> getLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivroByID(long id) {
        for (Livro livro : livroRepository.findAll()) {
            if (livro.getId().equals(id)) {
                return livro;
            }
        }
        return null;
    }

    public List<String> findTitulos() {
        List<Livro> todosOsLivros = livroRepository.findAll();
        return todosOsLivros.stream()
                .map(livro -> livro.getTitulo())
                .toList();
    }

    public List<String> findAutores() {
        List<Livro> todosOsLivros = livroRepository.findAll();
        return todosOsLivros.stream()
                .map(livro -> livro.getAutor())
                .distinct()
                .toList();
    }

    public Livro addLivro(Livro novoLivro) {
        livroRepository.save(novoLivro);
        return novoLivro;
    }

    public List<Livro> findLivrosPorAno(int ano) {
        List<Livro> todosOsLivros = livroRepository.findAll();
        return todosOsLivros.stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    public List<Livro> findLivrosDesatualizados(int ano) {
        List<Livro> todosOsLivros = livroRepository.findAll();
        return todosOsLivros.stream()
                .filter(livro -> livro.getAno() < ano)
                .toList();
    }

    public List<Livro> filtrarPorAutorEAno(String autor, int ano) {
        List<Livro> todosOsLivros = livroRepository.findAll();
        return todosOsLivros.stream()
                .filter(livro -> livro.getAutor().equals(autor) && livro.getAno() == ano)
                .toList();
    }

    public Livro updateLivro(long id, Livro dadosAtualizados) {
        for (Livro livro : livroRepository.findAll()) {
            if (livro.getId().equals(id)) {
                livro.setTitulo(dadosAtualizados.getTitulo());
                livro.setAutor(dadosAtualizados.getAutor());
                livro.setAno(dadosAtualizados.getAno());
                return livro;
            }
        }
        return null;
    }

    public boolean deleteLivro(Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
