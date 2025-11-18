package com.example.papyrus.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import com.example.papyrus.model.Livro;
import com.example.papyrus.repository.LivroRepository;

@Service
public class LivroService {
    private final LivroRepository livroRepository;

    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public long getTotalDelivros() {
        return livroRepository.count();
    }

    public List<Livro> getLivros() {
        return livroRepository.findAll();
    }

    public Optional<Livro> getLivroByID(Long id) {
        return livroRepository.findById(id);
    }

    public List<String> findTitulos() {
        return livroRepository.findAll().stream()
                .map(Livro::getTitulo)
                .toList();
    }

    public List<String> findAutores() {
        return livroRepository.findAll().stream()
                .map(Livro::getAutor)
                .distinct()
                .toList();
    }

    public Livro addLivro(Livro novoLivro) {
        return livroRepository.save(novoLivro);
    }

    public List<Livro> findLivrosPorAno(int ano) {
        return livroRepository.findAll().stream()
                .filter(livro -> livro.getAno() == ano)
                .toList();
    }

    public List<Livro> findLivrosDesatualizados(int ano) {
        return livroRepository.findAll().stream()
                .filter(livro -> livro.getAno() < ano)
                .toList();
    }

    public List<Livro> filtrarPorAutorEAno(String autor, int ano) {
        return livroRepository.findAll().stream()
                .filter(livro -> livro.getAutor().equals(autor) && livro.getAno() == ano)
                .toList();
    }

    public Livro updateLivro(Long id, Livro dadosAtualizados) {
        Optional<Livro> livroOpt = livroRepository.findById(id);

        if (livroOpt.isEmpty()) {
            return null;
        }

        Livro livroExistente = livroOpt.get();

        livroExistente.setTitulo(dadosAtualizados.getTitulo());
        livroExistente.setAutor(dadosAtualizados.getAutor());
        livroExistente.setAno(dadosAtualizados.getAno());

        return livroRepository.save(livroExistente);
    }

    public boolean deleteLivro(Long id) {
        if (livroRepository.existsById(id)) {
            livroRepository.deleteById(id);
            return true;
        }
        return false;
    }

}
