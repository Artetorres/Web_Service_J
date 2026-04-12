package br.mackenzie.vagasws;

import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmpresaController {
    
    private List<Empresa> empresas = new ArrayList<>();

    public EmpresaController() {
        // Dados da tabela do enunciado
        empresas.add(new Empresa(1, "Empresa Alfa LTDA", "12.345.678/0001-90", "contato@empresa-alfa.com"));
        empresas.add(new Empresa(2, "Beta Comércio ME", "98.765.432/0001-10", "beta@comercio.com"));
        empresas.add(new Empresa(3, "Gamma Serviços S.A.", "11.222.333/0001-44", "servicos@gamma.com"));
    }

    @GetMapping("/fci/api/empresas")
    public List<Empresa> getEmpresas() {
        return empresas;
    }

    @GetMapping("/fci/api/empresas/{id}")
    public Empresa getEmpresa(@PathVariable long id) {
        for(Empresa e : empresas) {
            if(e.getId() == id) return e;
        }
        return null;
    }

    @PostMapping("/fci/api/empresas")
    public Empresa createEmpresa(@RequestBody Empresa nova) {
        long novoId = empresas.isEmpty() ? 1 : empresas.get(empresas.size() - 1).getId() + 1;
        nova.setId(novoId);
        empresas.add(nova);
        return nova;
    }

    @PutMapping("/fci/api/empresas/{id}")
    public Empresa updateEmpresa(@PathVariable long id, @RequestBody Empresa dadosAtualizados) {
        for(Empresa e : empresas) {
            if(e.getId() == id) {
                e.setNome(dadosAtualizados.getNome());
                e.setCnpj(dadosAtualizados.getCnpj());
                e.setEmail(dadosAtualizados.getEmail());
                return e;
            }
        }
        return null;
    }

    @DeleteMapping("/fci/api/empresas/{id}")
    public void deleteEmpresa(@PathVariable long id) {
        empresas.removeIf(e -> e.getId() == id);
    }
}