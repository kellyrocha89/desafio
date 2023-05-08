import java.util.List;

@Service // Indica que essa classe é um componente gerenciado pelo Spring
@Transactional // Indica que as operações de banco de dados devem ser realizadas em uma transação
public class FornecedorServiceController {

    @Autowired // Indica que o objeto EntityManager deve ser injetado automaticamente pelo Spring
    private EntityManager entityManager;

    private FornecedorDAO fornecedorDAO;
    
    public FornecedorService(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }

    public void criarFornecedor(Fornecedor fornecedor) {
        entityManager.persist(fornecedor); // Cria um novo fornecedor no banco de dados
    }

    public Fornecedor buscarFornecedor(Long id) {
        return entityManager.find(Fornecedor.class, id); // Busca um fornecedor pelo ID no banco de dados
    }

    public void atualizarFornecedor(Fornecedor fornecedor) {
        entityManager.merge(fornecedor); // Atualiza um fornecedor existente no banco de dados
    }

    public void excluirFornecedor(Long id) {
        Fornecedor fornecedor = entityManager.find(Fornecedor.class, id);
        entityManager.remove(fornecedor); // Exclui um fornecedor pelo ID no banco de dados
    }

    public List<Fornecedor> buscarFornecedoresPorEmpresa(Empresa empresa) {
        Query query = entityManager.createQuery("SELECT f FROM Fornecedor f WHERE f.empresas.contains(:empresa)");
        query.setParameter("empresa", empresa);
        return query.getResultList(); // Retorna a lista de fornecedores que trabalham para uma determinada empresa
    }

    public List<Fornecedor> buscarFornecedoresPorNome(String nome) {
        Query query = entityManager.createQuery("SELECT f FROM Fornecedor f WHERE f.nome LIKE :nome");
        query.setParameter("nome", "%" + nome + "%");
        return query.getResultList(); // Retorna a lista de fornecedores que possuem um determinado nome
    }

    public Fornecedor buscarFornecedorPorCpfCnpj(String cpfCnpj) {
        Query query = entityManager.createQuery("SELECT f FROM Fornecedor f WHERE f.cpfCnpj = :cpfCnpj");
        query.setParameter("cpfCnpj", cpfCnpj);
        return (Fornecedor) query.getSingleResult(); // Retorna o fornecedor que possui um determinado CPF/CNPJ
    }

    public List<Fornecedor> filtrarFornecedores(String nome, String cpfCnpj) {
        return fornecedorDAO.filtrarPorNomeECpfCnpj(nome, cpfCnpj);
    }

    public boolean validarFornecedorMaiorIdade(Fornecedor fornecedor, Empresa empresa) {
        if (empresa.getEstado().equalsIgnoreCase("Paraná") && fornecedor instanceof PessoaFisica) {
            PessoaFisica pessoaFisica = (PessoaFisica) fornecedor;
            LocalDate dataNascimento = pessoaFisica.getDataNascimento();
            LocalDate dataMaioridade = LocalDate.now().minusYears(18);
            return dataNascimento.isBefore(dataMaioridade);
        } else {
            return true;
        }
    }
}
