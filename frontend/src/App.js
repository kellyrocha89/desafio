import React from 'react';
import { BrowserRouter as Router, Route, NavLink, Routes } from 'react-router-dom';
import "./table.css";
import Empresa from './Empresa';
import Fornecedor from './Fornecedor';

const App = () => {
  return (
    <Router>
      <div>
        <nav >
          <ul>
            <li>
              <NavLink to="/empresa">Empresas</NavLink>
            </li>
            <li>
              <NavLink to="/fornecedor">Fornecedores</NavLink>
            </li>
          </ul>
        </nav>

        <Routes>
          <Route path="/empresa" element={<Empresa />} />
          <Route path="/fornecedor" element={<Fornecedor />} />
        </Routes>
      </div>
    </Router>
  );
};

export default App;