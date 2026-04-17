package br.mackenzie.vagasws;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class VagaController {
    private List<Vaga> vagas = new ArrayList<>();

    public VagaController() {
        vagas.add(new Vaga(1L, "Desenvolvedor Java", "Atuação em projetos backend...", "2025-10-01", true, 1));
        vagas.add(new Vaga(2L, "Analista de Suporte Técnico", "Suporte a clientes...", "2025-09-27", true, 2));
        vagas.add(new Vaga(3L, "Engenheiro de Software", "Desenvolvimento de soluções...", "2025-10-03", false, 3));
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
    public Vaga update(@PathVariable long id, @RequestBody Vaga dadosAtualizados) {
        Optional<Vaga> vagaOpt = vagaRepo.findById(id);
        if (vagaOpt.isEmpty()) return null;
        Vaga vaga = vagaOpt.get();
        vaga.setTitulo(dadosAtualizados.getTitulo());
        vaga.setDescricao(dadosAtualizados.getDescricao());
        vaga.setPublicacao(dadosAtualizados.getPublicacao());
        vaga.setAtivo(dadosAtualizados.isAtivo());
        vaga.setIdEmpresa(dadosAtualizados.getIdEmpresa());
        return vagaRepo.save(vaga);
    }

    @DeleteMapping("/fci/api/vagas/{id}")
    public void delete(@PathVariable long id) { 
        vagaRepo.deleteById(id);
    }
}