<<<<<<< HEAD
const express = require('express');
const mysql = require('mysql');

const app = express();
const port = 5000;

// Configuração da conexão com o MySQL
const connection = mysql.createConnection({
  host: 'localhost',       // Altere para o endereço do seu servidor MySQL
  user: 'root',     // Altere para o usuário do MySQL
  password: '',   // Altere para a senha do MySQL
  database: 'desafio'  // Altere para o nome do seu banco de dados
});

// Teste de conexão com o MySQL
connection.connect((err) => {
  if (err) {
    console.error('Erro ao conectar ao MySQL:', err);
  } else {
    console.log('Conexão com o MySQL estabelecida com sucesso!');
  }
});

// Rota de exemplo para retornar os dados da tabela Empresa
app.get('/empresas', (req, res) => {
  const query = 'SELECT * FROM Empresa';

  connection.query(query, (err, results) => {
    if (err) {
      console.error('Erro ao executar a consulta:', err);
      res.status(500).send('Erro ao executar a consulta no banco de dados');
    } else {
      res.json(results);
    }
  });
});

app.listen(port, () => {
  console.log(`Servidor rodando na porta ${port}`);
=======
const express = require('express');
const mysql = require('mysql');

const app = express();
const port = 5000;

// Configuração da conexão com o MySQL
const connection = mysql.createConnection({
  host: 'localhost',       // Altere para o endereço do seu servidor MySQL
  user: 'root',     // Altere para o usuário do MySQL
  password: '',   // Altere para a senha do MySQL
  database: 'desafio'  // Altere para o nome do seu banco de dados
});

// Teste de conexão com o MySQL
connection.connect((err) => {
  if (err) {
    console.error('Erro ao conectar ao MySQL:', err);
  } else {
    console.log('Conexão com o MySQL estabelecida com sucesso!');
  }
});

// Rota de exemplo para retornar os dados da tabela Empresa
app.get('/empresas', (req, res) => {
  const query = 'SELECT * FROM Empresa';

  connection.query(query, (err, results) => {
    if (err) {
      console.error('Erro ao executar a consulta:', err);
      res.status(500).send('Erro ao executar a consulta no banco de dados');
    } else {
      res.json(results);
    }
  });
});

app.listen(port, () => {
  console.log(`Servidor rodando na porta ${port}`);
>>>>>>> f2758ad1e1d8bbd3d73d383299a4ea0458b8d475
});