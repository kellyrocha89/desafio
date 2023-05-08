import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { DataTable } from 'primereact/datatable';
import { Column } from 'primereact/column';
import { InputText } from 'primereact/inputtext';
import { Button } from 'primereact/button';
import { InputMask } from 'primereact/inputmask';
import './table.css';

const EmpresaCrud = () => {
  const [empresas, setEmpresas] = useState([]);
  const [cnpj, setCnpj] = useState('');
  const [nomeFantasia, setNomeFantasia] = useState('');
  const [cep, setCep] = useState('');
  const [editIndex, setEditIndex] = useState(null); // Índice da linha que está sendo editada
  const [editCnpj, setEditCnpj] = useState(''); // Valor do campo de edição de CNPJ
  const [editNomeFantasia, setEditNomeFantasia] = useState(''); // Valor do campo de edição de Nome Fantasia
  const [editCep, setEditCep] = useState(''); // Valor do campo de edição de CEP
  const [lazyState, setlazyState] = useState({
    filters: {
      cnpj: { value: '', matchMode: 'contains' },
      nomeFantasia: { value: '', matchMode: 'contains' },
      cep: { value: '', matchMode: 'contains' },
    },
  });

  useEffect(() => {
    fetchEmpresas();
  }, [lazyState]);

  const onFilter = (event) => {
    event['first'] = 0;
    setlazyState(event);
  };

  const fetchEmpresas = async () => {
    try {
      const response = await axios.get('/empresas');
      setEmpresas(response.data);
    } catch (error) {
      console.error(error);
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const novaEmpresa = {
      cnpj: cnpj,
      nomeFantasia: nomeFantasia,
      cep: cep,
    };
    setEmpresas([...empresas, novaEmpresa]);
    setCnpj('');
    setNomeFantasia('');
    setCep('');
  };

  const handleDelete = (index) => {
    const novasEmpresas = [...empresas];
    novasEmpresas.splice(index, 1);
    setEmpresas(novasEmpresas);
  };

  const handleEdit = (rowData, index) => {
    setEditIndex(index);
    setEditCnpj(rowData.cnpj);
    setEditNomeFantasia(rowData.nomeFantasia);
    setEditCep(rowData.cep);
  };

  const handleSave = (index) => {
    const novasEmpresas = [...empresas];
    const editedEmpresa = {
      cnpj: editCnpj,
      nomeFantasia: editNomeFantasia,
      cep: editCep,
    };
    novasEmpresas[index] = editedEmpresa;
    setEmpresas(novasEmpresas);
    setEditIndex(null);
  };

  const handleCancel = () => {
    setEditIndex(null);
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

  return (
    <div>
      <h1>Empresas</h1>
      <form onSubmit={handleSubmit}>
        <label>CNPJ:</label>
        <InputMask value={cnpj} onChange={(e) => setCnpj(e.target.value)} mask="999.999.999/9999-99" placeholder="999.999.999/9999-99"/>
        <label>Nome Fantasia:</label>
        <InputText value={nomeFantasia} onChange={(e) => setNomeFantasia(e.target.value)}/>
        <label>CEP:</label>
        <InputMask value={cep} onChange={(e) => setCep(e.target.value)} mask='99.999-999' placeholder='99.999-999'/>
        <Button label="Adicionar Empresa"  icon="pi pi-check" iconPos="right"/>
      </form>
      <DataTable
        value={empresas}
        tableStyle={{ minWidth: '50rem' }}
        filterDisplay="row"
        rows={10}
        onFilter={onFilter}
        filters={lazyState.filters}
        emptyMessage="Não há dados para exibir"
      >
        <Column field="cnpj" header="CNPJ" filter filterField="cnpj" />
        <Column
          field="nomeFantasia"
          header="Nome Fantasia"
          filter
          filterField="nomeFantasia"
        />
        <Column field="cep" header="CEP" filter filterField="cep" />
        <Column body={actionTemplate} />
      </DataTable>
    </div>
  );
};

export default EmpresaCrud;