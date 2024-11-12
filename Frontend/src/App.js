import './App.css';
import React from 'react';
import IbanDetail from "./components/IbanDetail";
import logo from './logo.JPG';

function App() {
  return (
    <div className="App">
        <header className="App-header">
            <img src={logo} alt="Logo" className="App-logo"/>
            <h1 className="custom-h1">Iban-Erkennung</h1>
            <IbanDetail/>
        </header>
    </div>

  );
}

export default App;
