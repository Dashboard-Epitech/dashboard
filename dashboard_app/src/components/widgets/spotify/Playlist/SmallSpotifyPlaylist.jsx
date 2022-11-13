export const SmallSpotifyPlaylist = ({ playlist }) => {
    return (
        <>
            <iframe
                style={{ colorScheme: "normal", borderRadius: "20px" }}
                src={`https://open.spotify.com/embed/playlist/${playlist}`}
                width="250px"
                height="80px"
                allow="autoplay; clipboard-write; encrypted-media; fullscreen; picture-in-picture">
            </iframe>
        </>
    )
}