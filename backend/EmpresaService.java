import java.util.List;

public class EmpresaService {
    
    private EmpresaDAO empresaDAO;
    
    public EmpresaService(EmpresaDAO empresaDAO) {
        this.empresaDAO = empresaDAO;
    }
    
    public List<Empresa> listarEmpresas() {
        return empresaDAO.listar();
    }
    
    public Empresa buscarEmpresaPorCNPJ(String cnpj) {
        return empresaDAO.buscarPorCNPJ(cnpj);
    }
    
    public void criarEmpresa(Empresa empresa) {
        empresaDAO.criar(empresa);
    }
    
    public void atualizarEmpresa(Empresa empresa) {
        empresaDAO.atualizar(empresa);
    }
    
    public void excluirEmpresa(Empresa empresa) {
        empresaDAO.excluir(empresa);
    }
    
}
