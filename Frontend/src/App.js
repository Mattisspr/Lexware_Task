import './App.css';
import React from 'react';
import IbanDetail from "./components/IbanDetail";

function App() {
  return (
    <div className="App">
        <header className="App-header">
            <h1 className="custom-h1">Iban-Erkennung</h1>
            <IbanDetail/>
        </header>
    </div>

  );
}

export default App;
