import { createGlobalState } from 'react-hooks-global-state';

const { setGlobalState, useGlobalState } = createGlobalState({
    ACCESS_TOKEN: localStorage.getItem('ACCESS_TOKEN') ?? null,
    USER: localStorage.getItem('USER') ? JSON.parse(localStorage.getItem("USER")) : null
})

export { setGlobalState, useGlobalState };