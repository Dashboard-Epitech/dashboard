export const LargeSpotifyTrack = ({ track }) => {
    return (
        <>
            <iframe
                style={{ colorScheme: "normal" }}
                src={`https://open.spotify.com/embed/track/${track}?utm_source=generator`}
                width="100%"
                height="300"
                allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture">
            </iframe>
        </>
    )
}