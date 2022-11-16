import { Flex } from "@chakra-ui/react"

export const LargeSpotifyPlaylist = ({ playlist }) => {
    return (
        <Flex w={"100%"}>
            <iframe
                style={{ colorScheme: "normal", borderRadius: "20px" }}
                src={`https://open.spotify.com/embed/playlist/${playlist}`}
                width="100%"
                height="380"
                frameBorder="0"
                allowfullscreen=""
                allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture">
            </iframe>
        </ Flex>
    )
}