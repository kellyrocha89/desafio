public class EmpresaFornecedor {

    private Long id;
    private Empresa empresa;
    private Fornecedor fornecedor;

    public EmpresaFornecedor(Empresa empresa, Fornecedor fornecedor) {
        this.empresa = empresa;
        this.fornecedor = fornecedor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
}
