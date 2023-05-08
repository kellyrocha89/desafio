import java.time.LocalDate;
import java.time.Period;

public class ValidacaoService {
    
    private EmpresaDAO empresaDAO;
    private FornecedorDAO fornecedorDAO;
    
    public ValidacaoService(EmpresaDAO empresaDAO, FornecedorDAO fornecedorDAO) {
        this.empresaDAO = empresaDAO;
        this.fornecedorDAO = fornecedorDAO;
    }
    
    public boolean validarCnpjUnico(String cnpj) {
        Empresa empresa = empresaDAO.buscarPorCNPJ(cnpj);
        return empresa == null;
    }
    
    public boolean validarCpfUnico(String cpf) {
        Fornecedor fornecedor = fornecedorDAO.buscarPorCPF(cpf);
        return fornecedor == null;
    }
    
    public boolean validarCep(String cep) {
        // realizar validação na API http://cep.la/api
        // retornar true se o CEP for válido e false caso contrário
    }
    
    public boolean validarIdadeFornecedor(LocalDate dataNascimento) {
        int idade = Period.between(dataNascimento, LocalDate.now()).getYears();
        return idade >= 18;
    }
    
}
