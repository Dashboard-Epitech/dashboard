import * as React from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { createRoot } from 'react-dom/client';
import { ChakraProvider, ColorModeScript } from '@chakra-ui/react';
import App from './App';
import WebFont from 'webfontloader';

const root = createRoot(document.getElementById('root'));
root.render(
  <React.StrictMode>
    <ChakraProvider>
      <ColorModeScript initialColorMode='dark'></ColorModeScript>
      <App />
    </ChakraProvider>
  </React.StrictMode>
);