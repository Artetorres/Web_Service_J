package br.mackenzie.vagasws;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmpresaController {
    
    private List<Empresa> empresas = new ArrayList<>();

    public EmpresaController() {
        empresas.add(new Empresa(1L, "Empresa Alfa LTDA", "12.345.678/0001-90", "contato@empresa-alfa.com"));
        empresas.add(new Empresa(2L, "Beta Comércio ME", "98.765.432/0001-10", "beta@comercio.com"));
        empresas.add(new Empresa(3L, "Gamma Serviços S.A.", "11.222.333/0001-44", "servicos@gamma.com"));
    }

    @Autowired
    private EmpresaRepository empresaRepo;

    @GetMapping("/fci/api/empresas")
    public Iterable<Empresa> getEmpresas() {
        Iterable<Empresa> empresas = empresaRepo.findAll();
        return empresas;
    }

    @GetMapping("/fci/api/empresas/{id}")
    public Iterable<Empresa> getEmpresa(@PathVariable long id) {
        Iterable<Empresa> empresas = empresaRepo.findAll();
        return empresas;
    }

    @PostMapping("/fci/api/empresas")
    public Empresa createEmpresa(@RequestBody Empresa nova) {
        return empresaRepo.save(nova);
    }

    @PutMapping("/fci/api/empresas/{id}")
    public Empresa updateEmpresa(@PathVariable long id, @RequestBody Empresa dadosAtualizados) {
        Optional<Empresa> empresaOpt = empresaRepo.findById(id);
        if (empresaOpt.isEmpty()) return null;
        Empresa empresa = empresaOpt.get();
        empresa.setNome(dadosAtualizados.getNome());
        empresa.setCnpj(dadosAtualizados.getCnpj());
        empresa.setEmail(dadosAtualizados.getEmail());
        return empresaRepo.save(empresa);
    }

    @DeleteMapping("/fci/api/empresas/{id}")
    public void deleteEmpresa(@PathVariable long id) {
        empresaRepo.deleteById(id);
    }
}