package com.example.papyrus; // Ou seu pacote principal

import com.example.papyrus.model.Livro;
import com.example.papyrus.repository.LivroRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final LivroRepository livroRepository;

    public DataLoader(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("Carregando dados de teste...");

        livroRepository.save(new Livro("O Guia do Mochileiro das Galáxias", "Douglas Adams", 1979));
        livroRepository.save(new Livro("Fahrenheit 451", "Ray Bradbury", 1953));
        livroRepository.save(new Livro("O Sol é para todos", "Harper Lee", 1960));
        livroRepository.save(new Livro("Cem Anos de Solidão", "Gabriel García Márquez", 1967));
        livroRepository.save(new Livro("A Revolução dos Bichos", "George Orwell", 1945));
        livroRepository.save(new Livro("1984", "George Orwell", 1949));
        livroRepository.save(new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", 1954));
        livroRepository.save(new Livro("O Hobbit", "J.R.R. Tolkien", 1937));
        livroRepository.save(new Livro("A Metamorfose", "Franz Kafka", 1915));
        livroRepository.save(new Livro("Crime e Castigo", "Fiódor Dostoiévski", 1866));

        System.out.println("Dados carregados. Total de livros no banco: " + livroRepository.count());
    }
}
