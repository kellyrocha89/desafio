import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelacionamentoEmpresaFornecedorServiceController {
    
    private RelacionamentoEmpresaFornecedorDAO relacionamentoDAO;
    
    @Autowired
    public RelacionamentoEmpresaFornecedorService(RelacionamentoEmpresaFornecedorDAO relacionamentoDAO) {
        this.relacionamentoDAO = relacionamentoDAO;
    }
    
    public List<RelacionamentoEmpresaFornecedor> listarRelacionamentos() {
        return relacionamentoDAO.listar();
    }
    
    public List<Fornecedor> listarFornecedoresPorEmpresa(Long idEmpresa) {
        return relacionamentoDAO.listarFornecedoresPorEmpresa(idEmpresa);
    }
    
    public List<Empresa> listarEmpresasPorFornecedor(Long idFornecedor) {
        return relacionamentoDAO.listarEmpresasPorFornecedor(idFornecedor);
    }
    
    public void criarRelacionamento(RelacionamentoEmpresaFornecedor relacionamento) {
        relacionamentoDAO.criar(relacionamento);
    }
    
    public void excluirRelacionamento(RelacionamentoEmpresaFornecedor relacionamento) {
        relacionamentoDAO.excluir(relacionamento);
    }
    
}
