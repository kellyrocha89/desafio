<<<<<<< HEAD
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;
    private String nomeFantasia;
    private String cep;

    public Empresa() {
        // Construtor vazio necessário para o mapeamento do JPA
    }

    public Empresa(String cnpj, String nomeFantasia, String cep) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.cep = cep;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
=======
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cnpj;
    private String nomeFantasia;
    private String cep;

    public Empresa() {
        // Construtor vazio necessário para o mapeamento do JPA
    }

    public Empresa(String cnpj, String nomeFantasia, String cep) {
        this.cnpj = cnpj;
        this.nomeFantasia = nomeFantasia;
        this.cep = cep;
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
>>>>>>> f2758ad1e1d8bbd3d73d383299a4ea0458b8d475
}