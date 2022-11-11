import axios from "axios";
const BASE_URL = 'http://localhost:8080/api/';

export const authLogin = (email, password) => {
    return axios.post(BASE_URL + 'auth/login', {
        username: email,
        password: password
    })
}

export const authRegister = (username, email, password) => {
    return axios.post(BASE_URL + 'auth/register', {
        username: username,
        email: email,
        password: password
    })
}

export const getUserData = (email, token) => {
    const headers = {
        'Authorization': 'Bearer ' + token,
    }
    
    const body = {
        email: email
    }

    return axios.post(BASE_URL + 'user/get', body, {headers}); 
}