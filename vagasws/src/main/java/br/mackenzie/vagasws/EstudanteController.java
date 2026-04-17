package br.mackenzie.vagasws;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EstudanteController {
    private List<Estudante> estudantes = new ArrayList<>();

    public EstudanteController() {
        // Inserindo os 10 estudantes da tabela
        estudantes.add(new Estudante(1L, "Ana Paula Souza", "ana.souza@email.com", "2002-03-15", 2020));
        estudantes.add(new Estudante(2L, "Carlos Henrique Lima", "carlos.lima@email.com", "2001-10-22", 2019));
        estudantes.add(new Estudante(3L, "Fernanda Oliveira", "fernanda.oliveira@email.com", "2003-07-05", 2021));
        estudantes.add(new Estudante(4L, "Lucas Pereira", "lucas.pereira@email.com", "2002-04-11", 2020));
        estudantes.add(new Estudante(5L, "Gabriela Martins", "gabriela.martins@email.com", "2001-12-25", 2019));
        estudantes.add(new Estudante(6L, "Rafael Costa", "rafael.costa@email.com", "2000-09-13", 2018));
        estudantes.add(new Estudante(7L, "Juliana Silva", "juliana.silva@email.com", "2002-06-18", 2020));
        estudantes.add(new Estudante(8L, "Marcos Vinícius", "marcos.vinicius@email.com", "2003-01-30", 2021));
        estudantes.add(new Estudante(9L, "Camila Azevedo", "camila.azevedo@email.com", "2001-11-08", 2019));
        estudantes.add(new Estudante(10L, "Felipe Cardoso", "felipe.cardoso@email.com", "2000-08-27", 2018));
    }
    @Autowired
    private EstudanteRepository estudanteRepo;

    @GetMapping("/fci/api/estudantes")
    public Iterable<Estudante> getEstudantes() { 
        Iterable<Estudante> estudantes = estudanteRepo.findAll();
        return estudantes; }

    @PostMapping("/fci/api/estudantes")
    public Estudante create(@RequestBody Estudante e) {
        return estudanteRepo.save(e);
    }

    @PutMapping("/fci/api/estudantes/{id}")
    public Estudante update(@PathVariable long id, @RequestBody Estudante dadosAtualizados) {
        Optional<Estudante> estudanteOpt = estudanteRepo.findById(id);
        if (estudanteOpt.isEmpty()) return null;
        Estudante estudante = estudanteOpt.get();
        estudante.setNome(dadosAtualizados.getNome());
        estudante.setEmail(dadosAtualizados.getEmail());
        estudante.setNascimento(dadosAtualizados.getNascimento());
        estudante.setAnoIngresso(dadosAtualizados.getAnoIngresso());
        return estudanteRepo.save(estudante);
    }

    @DeleteMapping("/fci/api/estudantes/{id}")
    public void delete(@PathVariable long id) {
        estudanteRepo.deleteById(id);
    }
}