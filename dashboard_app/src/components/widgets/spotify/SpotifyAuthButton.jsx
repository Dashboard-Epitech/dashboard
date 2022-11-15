import { Button } from "@chakra-ui/react"
import { FaSpotify } from "react-icons/fa"
import { useGlobalState } from "../../../state"
import * as ajax from "../../../lib/ajax"

const SpotifyAuthButton = () => {
    const [user, setUser] = useGlobalState("USER");
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");

    const getSpotifyUserLogin = () => {
        ajax.authSpotify(accessToken)
            .then((response) => {
                window.location.replace(response.data)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return(
        <Button leftIcon={<FaSpotify />} onClick={() => getSpotifyUserLogin()} colorScheme="green" w={'60%'}>Authenticate Spotify</Button>
    )
}

export default SpotifyAuthButton;