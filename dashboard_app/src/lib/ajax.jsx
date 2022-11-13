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

//GITHUB

export const authGithub = () => {
    return axios.get(BASE_URL + 'github/login');
}

//SPOTIFY 

export const authSpotify = (token) => {
    const headers = {
        'Authorization': 'Bearer ' + token,
    }

    return axios.get(BASE_URL + 'spotify/login', {headers})
}

export const getSpotifyToken = (token) => {
    return axios.get(BASE_URL + 'spotify/usertoken', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const refreshSpotifyToken = (token) => {
    return axios.get(BASE_URL + 'spotify/refresh', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const getUserPlaylists = (token) => {
    return axios.get(BASE_URL + 'spotify/playlists', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const getPlaylistTracks = (token, playlistId) => {
    return axios.get(BASE_URL + 'spotify/playlists/tracks/' + playlistId, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const getRandomEminemTrack = (token) => {
    return axios.get(BASE_URL + 'spotify/track/eminem', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}