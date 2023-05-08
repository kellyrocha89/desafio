import java.util.List;

public class RelacionamentoEmpresaFornecedorService {

    private EmpresaDAO empresaDAO;
    private FornecedorDAO fornecedorDAO;

    public RelacionamentoEmpresaFornecedorService(EmpresaDAO empresaDAO, FornecedorDAO fornecedorDAO) {
        this.empresaDAO = empresaDAO;
        this.fornecedorDAO = fornecedorDAO;
    }

    public void associarFornecedorEmpresa(Long idEmpresa, Long idFornecedor) {
        Empresa empresa = empresaDAO.buscarPorId(idEmpresa);
        Fornecedor fornecedor = fornecedorDAO.buscarPorId(idFornecedor);

        if (empresa != null && fornecedor != null) {
            empresa.adicionarFornecedor(fornecedor);
            fornecedor.adicionarEmpresa(empresa);

            empresaDAO.atualizar(empresa);
            fornecedorDAO.atualizar(fornecedor);
        }
    }

    public void desassociarFornecedorEmpresa(Long idEmpresa, Long idFornecedor) {
        Empresa empresa = empresaDAO.buscarPorId(idEmpresa);
        Fornecedor fornecedor = fornecedorDAO.buscarPorId(idFornecedor);

        if (empresa != null && fornecedor != null) {
            empresa.removerFornecedor(fornecedor);
            fornecedor.removerEmpresa(empresa);

            empresaDAO.atualizar(empresa);
            fornecedorDAO.atualizar(fornecedor);
        }
    }

    public List<Empresa> listarEmpresasPorFornecedor(Long idFornecedor) {
        Fornecedor fornecedor = fornecedorDAO.buscarPorId(idFornecedor);

        if (fornecedor != null) {
            return fornecedor.getEmpresas();
        }

        return null;
    }

    public List<Fornecedor> listarFornecedoresPorEmpresa(Long idEmpresa) {
        Empresa empresa = empresaDAO.buscarPorId(idEmpresa);

        if (empresa != null) {
            return empresa.getFornecedores();
        }

        return null;
    }
}
