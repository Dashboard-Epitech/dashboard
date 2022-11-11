import { createGlobalState } from 'react-hooks-global-state';

const { setGlobalState, useGlobalState } = createGlobalState({
    user: localStorage.getItem('user') ? JSON.parse(localStorage.getItem('user')) : null,
    items: []
})

export { setGlobalState, useGlobalState };