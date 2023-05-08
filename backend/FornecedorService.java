import java.util.List;

public class FornecedorService {
    
    private FornecedorDAO fornecedorDAO;
    
    public FornecedorService(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }
    
    public List<Fornecedor> listarFornecedores() {
        return fornecedorDAO.listar();
    }
    
    public Fornecedor buscarFornecedorPorCNPJCPF(String cnpjCpf) {
        return fornecedorDAO.buscarPorCNPJCPF(cnpjCpf);
    }
    
    public void criarFornecedor(Fornecedor fornecedor) {
        fornecedorDAO.criar(fornecedor);
    }
    
    public void atualizarFornecedor(Fornecedor fornecedor) {
        fornecedorDAO.atualizar(fornecedor);
    }
    
    public void excluirFornecedor(Fornecedor fornecedor) {
        fornecedorDAO.excluir(fornecedor);
    }

    public List<Fornecedor> buscarFornecedoresPorNome(String nome) {
        return fornecedorDAO.buscarPorNome(nome);
    }

    public List<Fornecedor> buscarFornecedoresPorEmpresa(Long idEmpresa) {
        return fornecedorDAO.buscarPorEmpresa(idEmpresa);
    }

    public List<Fornecedor> buscarFornecedoresPorNomeECNPJCPF(String nome, String cnpjCpf) {
        return fornecedorDAO.buscarPorNomeECNPJCPF(nome, cnpjCpf);
    }

    public List<Fornecedor> filtrarFornecedores(String nome, String cpfCnpj) {
        List<Fornecedor> fornecedores = fornecedorDAO.listar();
        List<Fornecedor> fornecedoresFiltrados = new ArrayList<Fornecedor>();
        
        for (Fornecedor fornecedor : fornecedores) {
            if (fornecedor.getNome().contains(nome) && fornecedor.getCpfCnpj().equals(cpfCnpj)) {
                fornecedoresFiltrados.add(fornecedor);
            }
        }
        
        return fornecedoresFiltrados;
    }

    public void validarFornecedorMenorIdade(Fornecedor fornecedor) throws Exception {
        // Verifica se a empresa é do Paraná
        if (fornecedor.getEmpresas().getEndereco().getEstado().equalsIgnoreCase("Paraná")) {
            // Verifica se o fornecedor é pessoa física e menor de idade
            if (fornecedor.getCnpjCpf().length() == 11 && fornecedor.getDataNascimento() != null) {
                // Calcula a idade do fornecedor
                LocalDate hoje = LocalDate.now();
                LocalDate dataNascimento = fornecedor.getDataNascimento().toLocalDate();
                Period periodo = Period.between(dataNascimento, hoje);
                int idade = periodo.getYears();

                // Verifica se a idade é menor que 18 anos
                if (idade < 18) {
                    throw new Exception("Não é permitido cadastrar fornecedor pessoa física menor de idade no Paraná.");
                }
            }
        }
    }
    
}