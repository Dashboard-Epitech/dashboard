import { Button } from "@chakra-ui/react"
import { FaSpotify } from "react-icons/fa"
import { useGlobalState } from "../../../state"
import * as ajax from "../../../lib/ajax"

const SpotifyAuthButton = () => {
    const [user, setUser] = useGlobalState("user");

    const getSpotifyUserLogin = () => {
        ajax.authSpotify(user.token)
            .then((response) => {
                window.location.replace(response.data)
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return(
        <Button leftIcon={<FaSpotify />} onClick={(e) => getSpotifyUserLogin(e)} colorScheme="green">Authenticate Spotify</Button>
    )
}

export default SpotifyAuthButton;