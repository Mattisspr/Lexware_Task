import axios from 'axios';

const api = axios.create({
    baseURL: 'http://localhost:8080/api/iban', // URL des Spring Boot Backends
    headers: {
        'Content-Type': 'application/json',
    },
});

export const getBankByIban = async (iban) => {
const response = await api.get(`${iban}`);
console.log(response.data)
return response.data;
}
