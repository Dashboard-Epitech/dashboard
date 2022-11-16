import { Flex } from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { useGlobalState } from "../../state"
import { LargeSpotifyPlaylist } from "./spotify/Playlist/LargeSpotifyPlaylist";
import { MediumSpotifyPlaylist } from "./spotify/Playlist/MediumSpotifyPlaylist";
import { SmallSpotifyPlaylist } from "./spotify/Playlist/SmallSpotifyPlaylist";
import { LargeSpotifyTrack } from "./spotify/Track/LargeSpotifyTrack";
import { MediumSpotifyTrack } from "./spotify/Track/MediumSpotifyTrack";
import { SmallSpotifyTrack } from "./spotify/Track/SmallSpotifyTrack";
import { LargeWeather } from "./weather/dasbhoard/LargeWeather";
import { MediumWeather } from "./weather/dasbhoard/MediumWeather";
import { SmallWeather } from "./weather/dasbhoard/SmallWeather";

export const WidgetRenderer = ({ widgets }) => {
    const [isRendered, setIsRendered] = useState(false);
    const [rendered, setRendered] = useState([]);

    useEffect(() => {
        renderWidgets()
    }, [])
    

    const renderWidgets = () => {
        let tmp = [];
        if (widgets.length > 0) {
            widgets.forEach((widget, index) => {
                if (widget.category == "WEATHER") {
                    if (widget.size == "XL") {
                        tmp.push(<LargeWeather key={index} city={widget.city} unit={widget.unit} refreshRate={widget.refreshRate} />)
                    } else if (widget.size == "MD") {
                        tmp.push(<MediumWeather key={index} city={widget.city} unit={widget.unit} refreshRate={widget.refreshRate} />)
                    } else if (widget.size == "SM") {
                        tmp.push(<SmallWeather key={index} city={widget.city} unit={widget.unit} refreshRate={widget.refreshRate} />)
                    }
                } else if (widget.category == "SPOTIFY") {
                    if (widget.type == "TRACK") {
                        if (widget.size == "XL") {
                            tmp.push(<LargeSpotifyTrack track={widget.trackId}/>)
                        } else if (widget.size == "MD") {
                            tmp.push(<MediumSpotifyTrack track={widget.trackId} />)
                        } else if (widget.size == "SM") {
                            tmp.push(<SmallSpotifyTrack track={widget.trackId} />)
                        }
                    } else if (widget.type == "PLAYLIST") {
                        if (widget.size == "XL") {
                            tmp.push(<LargeSpotifyPlaylist playlist={widget.playlistId}/>)
                        } else if (widget.size == "MD") {
                            tmp.push(<MediumSpotifyPlaylist playlist={widget.playlistId} />)
                        } else if (widget.size == "SM") {
                            tmp.push(<SmallSpotifyPlaylist playlist={widget.playlistId} />)
                        }
                    }
                }
            });
        }

        console.log(tmp);
        setRendered(tmp);
    }

    return (
        <Flex minW={"100%"} minH={"100%"} wrap="wrap">
            {rendered}
        </Flex>
    )
}