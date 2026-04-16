package br.mackenzie.vagasws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EstudanteController {
    private List<Estudante> estudantes = new ArrayList<>();

    public EstudanteController() {
        // Inserindo os 10 estudantes da tabela
        estudantes.add(new Estudante(1, "Ana Paula Souza", "ana.souza@email.com", "2002-03-15", 2020));
        estudantes.add(new Estudante(2, "Carlos Henrique Lima", "carlos.lima@email.com", "2001-10-22", 2019));
        estudantes.add(new Estudante(3, "Fernanda Oliveira", "fernanda.oliveira@email.com", "2003-07-05", 2021));
        estudantes.add(new Estudante(4, "Lucas Pereira", "lucas.pereira@email.com", "2002-04-11", 2020));
        estudantes.add(new Estudante(5, "Gabriela Martins", "gabriela.martins@email.com", "2001-12-25", 2019));
        estudantes.add(new Estudante(6, "Rafael Costa", "rafael.costa@email.com", "2000-09-13", 2018));
        estudantes.add(new Estudante(7, "Juliana Silva", "juliana.silva@email.com", "2002-06-18", 2020));
        estudantes.add(new Estudante(8, "Marcos Vinícius", "marcos.vinicius@email.com", "2003-01-30", 2021));
        estudantes.add(new Estudante(9, "Camila Azevedo", "camila.azevedo@email.com", "2001-11-08", 2019));
        estudantes.add(new Estudante(10, "Felipe Cardoso", "felipe.cardoso@email.com", "2000-08-27", 2018));
    }
    @Autowired
    private EstudanteRepository estudanteRepo;

    @GetMapping("/fci/api/estudantes")
    public Iterable<Estudante> getEstudantes() { 
        Iterable<Estudante> estudantes = estudanteRepo.findAll();
        return estudantes; }

    @PostMapping("/fci/api/estudantes")
    public Estudante create(@RequestBody Estudante e) {
        long novoId = estudantes.get(estudantes.size()-1).getId() + 1;
        e.setId(novoId);
        estudantes.add(e);
        return e;
    }

    @PutMapping("/fci/api/estudantes/{id}")
    public Estudante update(@PathVariable long id, @RequestBody Estudante dados) {
        for(Estudante e : estudantes) {
            if(e.getId() == id) {
                e.setNome(dados.getNome());
                e.setEmail(dados.getEmail());
                e.setNascimento(dados.getNascimento());
                e.setAnoIngresso(dados.getAnoIngresso());
                return e;
            }
        }
        return null;
    }

    @DeleteMapping("/fci/api/estudantes/{id}")
    public void delete(@PathVariable long id) { estudantes.removeIf(e -> e.getId() == id); }
}