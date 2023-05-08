import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { InputMask } from 'primereact/inputmask';
import { Calendar } from 'primereact/calendar';
import moment from 'moment';
import './table.css';

const FornecedorCrud = () => {
  const [fornecedores, setFornecedores] = useState([]);
  const [cnpjCpf, setCnpjCpf] = useState('');
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [cep, setCep] = useState('');
  const [rg, setRg] = useState('');
  const [dataNascimento, setDataNascimento] = useState('');
  const [empresas, setEmpresas] = useState([]);
  const [empresaSelecionada, setEmpresaSelecionada] = useState('');
  const [lazyState, setLazyState] = useState({
    filters: {
      cnpjCpf: { value: '', matchMode: 'contains' },
      nome: { value: '', matchMode: 'contains' },
      email: { value: '', matchMode: 'contains' },
      cep: { value: '', matchMode: 'contains' },
      rg: { value: '', matchMode: 'contains' },
      dataNascimento: { value: '', matchMode: 'contains' },
      empresas: { value: '', matchMode: 'contains' },
    },
  });
  const [editIndex, setEditIndex] = useState(-1);
  const [editedFornecedor, setEditedFornecedor] = useState(null);

  useEffect(() => {
    fetchFornecedores();
  }, [lazyState]);

  const onFilter = (event) => {
    event['first'] = 0;
    setLazyState(event);
  };

  const fetchFornecedores = async () => {
    try {
      const response = await axios.get('/fornecedor'); // Rota da sua API para consultar todas as empresas
      setFornecedores(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    // Verificar se o CNPJ ou CPF já existe
    if (fornecedores.some((fornecedor) => fornecedor.cnpjCpf === cnpjCpf)) {
      alert('O CNPJ/CPF informado já está cadastrado.');
      return;
    }
    // Verificar se a empresa é do Paraná e o fornecedor é menor de idade
    if (
      empresas.some(
        (empresa) => empresa.uf === 'PR' && calculaIdade(dataNascimento) < 18
      )
    ) {
      alert('Não é possível cadastrar fornecedores menores de idade no Paraná.');
      return;
    }
    const novoFornecedor = {
      cnpjCpf: cnpjCpf,
      nome: nome,
      email: email,
      cep: cep,
      rg: rg,
      dataNascimento: dataNascimento,
      empresas: [...empresas.filter((empresa) => empresa !== '')],
    };
    setFornecedores([...fornecedores, novoFornecedor]);
    setCnpjCpf('');
    setNome('');
    setEmail('');
    setCep('');
    setRg('');
    setDataNascimento('');
    setEmpresas([]);
  };

  const handleDelete = (index) => {
    const novosFornecedores = [...fornecedores];
    novosFornecedores.splice(index, 1);
    setFornecedores(novosFornecedores);
  };

  const handleSelectEmpresa = (e) => {
    setEmpresaSelecionada(e.target.value);
  };

  const handleAddEmpresa = () => {
    if (empresas.includes(empresaSelecionada)) {
      alert('A empresa selecionada já foi adicionada.');
      return;
    }
    setEmpresas([...empresas, empresaSelecionada]);
    setEmpresaSelecionada('');
  };

  const handleRemoveEmpresa = (index) => {
    const novasEmpresas = [...empresas];
    novasEmpresas.splice(index, 1);
    setEmpresas(novasEmpresas);
  };

  const calculaIdade = (dataNascimento) => {
    const hoje = new Date();
    const nascimento = new Date(dataNascimento);
    let idade = hoje.getFullYear() - nascimento.getFullYear();
    const mes = hoje.getMonth() - nascimento.getMonth();
    if (mes < 0 || (mes === 0 && hoje.getDate() < nascimento.getDate())) {
      idade--;
    }
    return idade;
  };

  const handleEdit = (fornecedor, index) => {
    setEditIndex(index);
    setEditedFornecedor({ ...fornecedor });
  };

  const handleSave = (index) => {
    const novosFornecedores = [...fornecedores];
    novosFornecedores[index] = editedFornecedor;
    setFornecedores(novosFornecedores);
    setEditIndex(-1);
    setEditedFornecedor(null);
  };

  const handleCancel = () => {
    setEditIndex(-1);
    setEditedFornecedor(null);
  };

  const actionTemplate = (rowData, index) => {
    if (editIndex === index) {
      return (
        <>
          <Button
            onClick={() => handleSave(index)}
            icon="pi pi-check"
            className="p-button-success"
          />
          <Button
            onClick={handleCancel}
            icon="pi pi-times"
            className="p-button-secondary"
          />
        </>
      );
    } else {
      return (
        <>
          <Button
            onClick={() => handleEdit(rowData, index)}
            icon="pi pi-pencil"
            className="p-button-warning"
          />
          <Button
            onClick={() => handleDelete(index)}
            icon="pi pi-trash"
            className="p-button-danger"
          />
        </>
      );
    }
  };

  const showRgAndDataNascimento = cnpjCpf.length === 11;

  return (
    <div>
      <h1>Fornecedores</h1>
      <form onSubmit={handleSubmit}>
        <label>CNPJ/CPF:</label>
        <InputMask value={cnpjCpf} onChange={(e) => setCnpjCpf(e.target.value)} 
          mask={cnpjCpf.length != 14 ? "999.999.999-99" : "999.999.999/9999-99"}
        />

        <label>
          Nome:
          <InputText value={nome} onChange={(e) => setNome(e.target.value)} />
        </label>
        <label>E-mail:</label>
        <InputText value={email} onChange={(e) => setEmail(e.target.value)} />
        <label>CEP:</label>
        <InputMask
          value={cep}
          onChange={(e) => setCep(e.target.value)}
          mask="
          99.999-999"
          placeholder="99.999-999"
        />
        {showRgAndDataNascimento && (
          <div className="w-100">
            <label>RG:</label>
            <InputMask
              value={rg}
              onChange={(e) => setRg(e.target.value)}
              mask="99.999.999-9"
              placeholder="99.999.999-9"
            />
            <label>Data de Nascimento:</label>
            <Calendar
              value={dataNascimento}
              onChange={(e) => setDataNascimento(e.target.value)}
            />
          </div>
        )}
        <div>
          <label>Informe a(s) empresa(s) do fornecedor:</label>
          <InputText
            value={empresaSelecionada}
            onChange={handleSelectEmpresa}
            placeholder="Empresa"
          />
        </div>
        <Button
          label="Adicionar Fornecedor"
          icon="pi pi-check"
          iconPos="right"
        />
      </form>
      <DataTable
        value={fornecedores}
        filterDisplay="row"
        rows={10}
        onFilter={onFilter}
        filters={lazyState.filters}
        emptyMessage="Não há dados para exibir"
      >
        <Column field="cnpjCpf" header="CNPJ/CPF" filter filterField="cnpjCpf" />
        <Column field="nome" header="Nome" filter filterField="nome" />
        <Column field="email" header="E-mail" filter filterField="email" />
        <Column field="cep" header="CEP" filter filterField="cep" />
        <Column field="rg" header="RG" filter filterField="rg" />
        <Column
          field="dataNascimento"
          header="Data de Nascimento"
          filter
          filterField="dataNascimento"
        />
        <Column
          field="empresas"
          header="Empresas"
          body={(rowData) => rowData.empresas.join(', ')}
          filter
          filterField="empresas"
        />
        <Column body={actionTemplate} />
      </DataTable>
    </div>
  );
};

export default FornecedorCrud;
