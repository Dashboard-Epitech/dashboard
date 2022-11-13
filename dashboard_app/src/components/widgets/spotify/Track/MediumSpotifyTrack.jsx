export const MediumSpotifyTrack = ({ track }) => {
    return (
        <>
            <iframe
                style={{ colorScheme: "normal" }}
                src={`https://open.spotify.com/embed/track/${track}?utm_source=generator`}
                width="50%"
                height="200"
                allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture">
            </iframe>
        </>
    )
}