<<<<<<< HEAD
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{id}")
    public Empresa getEmpresa(@PathVariable Long id) {
        return empresaService.getEmpresa(id);
    }

    @PostMapping
    public Empresa criarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.criarEmpresa(empresa);
    }

    @PutMapping("/{id}")
    public Empresa atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        return empresaService.atualizarEmpresa(id, empresa);
    }

    @DeleteMapping("/{id}")
    public void deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresa(id);
    }
}
=======
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/empresas")
public class EmpresaController {
    private EmpresaService empresaService;

    @Autowired
    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @GetMapping("/{id}")
    public Empresa getEmpresa(@PathVariable Long id) {
        return empresaService.getEmpresa(id);
    }

    @PostMapping
    public Empresa criarEmpresa(@RequestBody Empresa empresa) {
        return empresaService.criarEmpresa(empresa);
    }

    @PutMapping("/{id}")
    public Empresa atualizarEmpresa(@PathVariable Long id, @RequestBody Empresa empresa) {
        return empresaService.atualizarEmpresa(id, empresa);
    }

    @DeleteMapping("/{id}")
    public void deletarEmpresa(@PathVariable Long id) {
        empresaService.deletarEmpresa(id);
    }
}
>>>>>>> f2758ad1e1d8bbd3d73d383299a4ea0458b8d475
