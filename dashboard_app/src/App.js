import React, { useEffect } from 'react';
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import { useGlobalState } from './state';
import { AuthPage } from './components/auth/AuthPage';
import { Dashboard } from './components/dashboard/Dashboard';
import { LoginForm } from './components/auth/login/LoginForm';
import { RegisterForm } from './components/auth/register/RegisterForm';
import { AlertSuccess } from './components/alerts/AlertSuccess';
import { HomePage } from './components/home/HomePage';
import WebFont from 'webfontloader';
import { WidgetMenu } from './components/widgets/WidgetMenu';
import { WidgetControls } from './components/widgets/WidgetControls';
import { NewWidget } from './components/widgets/NewWidget';
import { NewWeather } from './components/widgets/weather/NewWeather';
import { OAuth2RedirectGithub } from './components/auth/oauth2/OAuth2RedirectGithub';
import { OAuth2RedirectSpotify } from './components/auth/oauth2/OAuth2RedirectSpotify';

function App() {
  useEffect(() => {
    WebFont.load({
      google: {
        families: ['PT Mono']
      }
    })
  }, [])

  return (
    <>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<HomePage />}>
            <Route path="dashboard" element={<Dashboard />} />
            <Route path="widgets" element={<WidgetMenu />}>
              <Route path="controls" element={<WidgetControls />}>
                <Route path="new" element={<NewWidget />}>
                  <Route path="weather" element={<NewWeather />} />
                </Route>
              </Route>
            </Route>
          </Route>
          <Route path="/auth" element={<AuthPage />}>
            <Route path='login' element={<LoginForm />}>
              <Route path='verified' element={<AlertSuccess alertContent="Account Verified. Please log in !" />} />
            </Route>
            <Route path='register' element={<RegisterForm />} />
          </Route>
          <Route path="/oauth2">
            <Route path="redirect">
              <Route path="github" element={<OAuth2RedirectGithub />}/>
              <Route path="spotify" element={<OAuth2RedirectSpotify />}/>
            </Route>
          </Route>
        </Routes>
      </BrowserRouter>
    </>
  );
}

export default App;
