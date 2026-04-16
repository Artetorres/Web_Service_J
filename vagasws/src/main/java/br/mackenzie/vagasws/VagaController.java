package br.mackenzie.vagasws;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VagaController {
    private List<Vaga> vagas = new ArrayList<>();

    public VagaController() {
        vagas.add(new Vaga(1, "Desenvolvedor Java", "Atuação em projetos backend...", "2025-10-01", true, 1));
        vagas.add(new Vaga(2, "Analista de Suporte Técnico", "Suporte a clientes...", "2025-09-27", true, 2));
        vagas.add(new Vaga(3, "Engenheiro de Software", "Desenvolvimento de soluções...", "2025-10-03", false, 3));
    }

    @Autowired
    private VagaRepository vagaRepo;

    @GetMapping("/fci/api/vagas")
    public Iterable<Vaga> getVagas() { 
        Iterable<Vaga> vagas = vagaRepo.findAll();
        return vagas; 
    }

    @PostMapping("/fci/api/vagas")
    public Vaga create(@RequestBody Vaga v) {
        return vagaRepo.save(v);
    }

    @PutMapping("/fci/api/vagas/{id}")
    public Vaga update(@PathVariable long id, @RequestBody Vaga dados) {
        for(Vaga v : vagas) {
            if(v.getId() == id) {
                v.setTitulo(dados.getTitulo());
                v.setDescricao(dados.getDescricao());
                v.setPublicacao(dados.getPublicacao());
                v.setAtivo(dados.isAtivo());
                v.setIdEmpresa(dados.getIdEmpresa());
                return v;
            }
        }
        return null;
    }

    @DeleteMapping("/fci/api/vagas/{id}")
    public void delete(@PathVariable long id) { vagas.removeIf(v -> v.getId() == id); }
}