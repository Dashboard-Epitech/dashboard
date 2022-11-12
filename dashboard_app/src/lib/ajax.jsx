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

export const getWeather = (city) => {
    return axios.get(`https://api.openweathermap.org/data/2.5/weather?q=${city}&appid=248f71af2576da84a291a06fba0a8fdd`)
}

// export const getUserWidgets = (id, token) => {
//     const headers = {
//         'Authorization': 'Bearer ' + token,
//     }
    
//     const body = {
//         id: id
//     }

//     return axios.post(BASE_URL + 'dashboard/get', body, {headers});
// }