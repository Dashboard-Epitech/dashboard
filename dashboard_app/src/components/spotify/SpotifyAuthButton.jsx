import { Button } from "@chakra-ui/react"
import { FaSpotify } from "react-icons/fa"

const SpotifyAuthButton = () => {
    const getSpotifyUserLogin = (e) => {
        fetch("http://localhost:8080/api/spotify/login")
        .then(response => response.text())
        .then(url => window.location.replace(url))
    }

    return(
        <Button leftIcon={<FaSpotify />} onClick={(e) => getSpotifyUserLogin(e)} colorScheme="green">Authenticate Spotify</Button>
    )
}

export default SpotifyAuthButton;