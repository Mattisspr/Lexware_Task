import React, { useState } from 'react';
import { getBankByIban } from '../services/api';

function IbanDetail() {
    let [iban, setIban] = useState('');
    const [bank, setBank] = useState('');
    const [error, setError] = useState(null);

    const handleInputChange = (e) => {
        setIban(e.target.value);
    };

    const handleSearch = async () => {
        sanitizeIban()
        console.log(iban)
        try {
            const data = await getBankByIban(iban);
            setBank(data);
            setError(null);
        } catch (err) {
            setError('Bank nicht gefunden oder Fehler beim Abrufen der Daten');
            setBank('');
        }
    };

    function sanitizeIban() {
        iban = iban.replace(/[\s-./]/g, '');
    }

    return (
        <div>
            <h2>Bank suchen</h2>
            <input
                type="text"
                value={iban}
                onChange={handleInputChange}
                placeholder="Iban eingeben"
            />
            <button onClick={handleSearch}>Suchen</button>

            {error && <p style={{ color: 'red' }}>{error}</p>}
            {bank && (
                <div>
                    <p>Bankname: {bank}</p>
                </div>
            )}
        </div>
    );
}


export default IbanDetail;