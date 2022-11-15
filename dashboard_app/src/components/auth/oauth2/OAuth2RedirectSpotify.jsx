import { Navigate, useNavigate, useSearchParams } from "react-router-dom"
import { useGlobalState } from "../../../state";
import * as ajax from '../../../lib/ajax';
import { useState } from "react";

export const OAuth2RedirectSpotify = () => {
    const [accessToken, setAccessToken] = useGlobalState('ACCESS_TOKEN');
    const [queryParam, setQueryParam] = useSearchParams();
    const navigate = useNavigate();

    const setUserSpotifyToken = () => {
        ajax.bindSpotifyToken(accessToken, queryParam.get("token"), queryParam.get("refresh"))
            .then((response) => {
                console.log(response);
                navigate("/dashboard")
            })
            .error((error) => {
                console.log(error);
            })
    }

    if (queryParam.get("token") && queryParam.get("refresh")) {
        setUserSpotifyToken()
        return <Navigate to="/dashboard" />
    } else if (queryParam.get("error")) {
        return <Navigate to="/auth/login/oauth2error" />
    }
}