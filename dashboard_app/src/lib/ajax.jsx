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

export const getUserData = (token) => {
    return axios.get(BASE_URL + 'user/get', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }); 
}

//DASHBOARD

export const getUserDashboards = (token) => {
    return axios.get(BASE_URL + 'dashboard/get/all', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    }); 
}

export const getDashboardData = (token, dashboardId) => {
    return axios.post(BASE_URL + 'dashboard/get/data', {
        dashboardId: dashboardId
    }, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const getDashboardWidgets = (token, dashboardId) => {
    return axios.post(BASE_URL + 'dashboard/get/widgets', 
    {
        dashboardId: dashboardId
    }, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const newDashboard = (token, name) => {
    return axios.post(BASE_URL + 'dashboard/create', {
        name: name
    }, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

// Weather

export const getWeather = (token, city, unit) => {
    return axios.post(BASE_URL + 'weather_api/get', {
        city: city,
        unit: unit,
    }, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const newWeatherWidget = (token, dashboardId, city, unit, size, refreshRate) => {
    return axios.post(BASE_URL + 'widget/create/weather', {
        dashboardId: dashboardId,
        city: city,
        unit: unit,
        size: size,
        refreshRate: refreshRate
    }, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const setWeatherWidget = (city, isCelsius, dashboard_id) => {
    
    const body = {
        city: city,
        isCelsius: isCelsius,
        dashboard_id: dashboard_id
    }

    return axios.post(BASE_URL + 'widget/weather/create', body)
}

// Currency

export const getAllcurrenciesBrut = () => {
    const headers = {
        'Authorization': 'Basic ' + btoa('epitech766635432:fosm18i26is7m9usvfukb5b4em')
    }
    return axios.get('https://xecdapi.xe.com/v1/currencies.json/?obsolete=false', {headers})
}

export const compareCurrenies = (from, to) => {
    const headers = {
        'Authorization': 'Basic ' + btoa('epitech766635432:fosm18i26is7m9usvfukb5b4em')
    }
    console.log(`${from}${to}`)
    return axios.get(`https://xecdapi.xe.com/v1/convert_from.json/?from=${from}&to=${to}`, {headers})
}

//GITHUB

export const authGithub = () => {
    return axios.get(BASE_URL + 'github/login');
}

//SPOTIFY 

export const createSpotifyWidget = (token, dashboardId, type, size, trackId, playlistId) => {
    return axios.post(BASE_URL + 'widget/create/spotify', {
        dashboardId: dashboardId,
        type: type,
        size: size,
        trackId: trackId,
        playlistId: playlistId
    }, {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const authSpotify = (token) => {
    return axios.get(BASE_URL + 'spotify/login', {
        headers: {
            Authorization: `Bearer ${token}`
        }
    })
}

export const bindSpotifyToken = (apiToken, spotifyToken, spotifyRefresh) => {
    return axios.post(BASE_URL + 'spotify/token/bind', {
        token: spotifyToken,
        refresh: spotifyRefresh
    }, {
        headers: {
            Authorization: `Bearer ${apiToken}`
        }
    })
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