import { Flex } from "@chakra-ui/react"

export const LargeSpotifyTrack = ({ track }) => {
    return (
        <Flex my={3}>
            <iframe
                style={{ colorScheme: "normal" }}
                src={`https://open.spotify.com/embed/track/${track}?utm_source=generator`}
                width="100%"
                height="300"
                allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture">
            </iframe>
        </ Flex>
    )
}