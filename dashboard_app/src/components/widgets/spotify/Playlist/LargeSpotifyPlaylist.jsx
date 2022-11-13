export const LargeSpotifyPlaylist = ({ playlist }) => {
    return (
        <>
            <iframe
                style={{ colorScheme: "normal", borderRadius: "20px" }}
                src={`https://open.spotify.com/embed/playlist/${playlist}`}
                width="100%"
                height="380"
                frameBorder="0"
                allowfullscreen=""
                allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture">
            </iframe>
        </>
    )
}