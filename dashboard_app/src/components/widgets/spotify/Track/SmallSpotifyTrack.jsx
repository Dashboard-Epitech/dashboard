import { Flex } from "@chakra-ui/react"

export const SmallSpotifyTrack = ({ track }) => {
    console.log(track)
    return (
        <Flex my={3}>
            <iframe
                style={{ colorScheme: "normal" }}
                src={`https://open.spotify.com/embed/track/${track}?utm_source=generator`}
                width="250px"
                height="80px"
                allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture">
            </iframe>
        </Flex>
    )
}