import { Navigate, useSearchParams } from "react-router-dom"
import { useGlobalState } from "../../../state";
import { Nav } from "../../nav/Nav";

export const OAuth2RedirectGithub = () => {
    const [accessToken, setAccessToken] = useGlobalState('ACCESS_TOKEN');
    const [queryParam, setQueryParam] = useSearchParams();

    if (queryParam.get("token")) {
        setAccessToken(queryParam.get("token"));
        localStorage.setItem("ACCESS_TOKEN", accessToken);
        return <Navigate to="/dashboard" />
    } else if (queryParam.get("error")) {
        return <Navigate to="/auth/login/oauth2error" />
    }
}