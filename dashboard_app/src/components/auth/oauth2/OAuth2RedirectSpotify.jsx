import { Navigate, useNavigate, useSearchParams } from "react-router-dom"
import { useGlobalState } from "../../../state";
import * as ajax from '../../../lib/ajax';
import { useEffect, useState } from "react";

export const OAuth2RedirectSpotify = () => {
    const [accessToken, setAccessToken] = useGlobalState('ACCESS_TOKEN');
    const [queryParam, setQueryParam] = useSearchParams();
    const navigate = useNavigate();

    useEffect(() => {
        if (queryParam.get("token") && queryParam.get("refresh")) {
            setUserSpotifyToken();
        }
        navigate("/dashboard")
    }, [])
    

    const setUserSpotifyToken = () => {
        ajax.bindSpotifyToken(accessToken, queryParam.get("token"), queryParam.get("refresh"))
    }
}