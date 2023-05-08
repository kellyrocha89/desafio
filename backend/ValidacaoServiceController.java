import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class ValidacaoServiceController {
    
    private FornecedorDAO fornecedorDAO;
    
    @Autowired
    public ValidacaoService(FornecedorDAO fornecedorDAO) {
        this.fornecedorDAO = fornecedorDAO;
    }
    
    public boolean validarCnpjUnico(String cnpj) {
        Fornecedor fornecedor = fornecedorDAO.buscarPorCnpj(cnpj);
        return fornecedor == null;
    }
    
    public boolean validarCpfUnico(String cpf) {
        Fornecedor fornecedor = fornecedorDAO.buscarPorCpf(cpf);
        return fornecedor == null;
    }
    
    public boolean validarCep(String cep) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest httpRequest = HttpRequest.newBuilder()
                .uri(URI.create("http://cep.la/api/" + cep))
                .build();
        
        try {
            HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            
            if (httpResponse.statusCode() == 200) {
                // CEP válido
                return true;
            } else {
                // CEP inválido
                return false;
            }
        } catch (Exception e) {
            // Erro na validação do CEP
            return false;
        }
    }
    
    public boolean validarIdade(Fornecedor fornecedor) {
        if (fornecedor instanceof PessoaFisica && fornecedor.getEstado().equals("Paraná")) {
            PessoaFisica pessoaFisica = (PessoaFisica) fornecedor;
            LocalDate dataNascimento = pessoaFisica.getDataNascimento();
            LocalDate dataAtual = LocalDate.now();
            long anos = ChronoUnit.YEARS.between(dataNascimento, dataAtual);
            return anos >= 18;
        }
        return true;
    }
}
