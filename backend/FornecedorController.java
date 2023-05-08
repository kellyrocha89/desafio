import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fornecedores")
public class FornecedorController {
    private FornecedorService fornecedorService;

    @Autowired
    public FornecedorController(FornecedorService fornecedorService) {
        this.fornecedorService = fornecedorService;
    }

    @GetMapping("/{id}")
    public Fornecedor getFornecedor(@PathVariable Long id) {
        return fornecedorService.getFornecedor(id);
    }

    @PostMapping
    public Fornecedor criarFornecedor(@RequestBody Fornecedor fornecedor) {
        return fornecedorService.criarFornecedor(fornecedor);
    }

    @PutMapping("/{id}")
    public Fornecedor atualizarFornecedor(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        return fornecedorService.atualizarFornecedor(id, fornecedor);
    }

    @DeleteMapping("/{id}")
    public void deletarFornecedor(@PathVariable Long id) {
        fornecedorService.deletarFornecedor(id);
    }
}
