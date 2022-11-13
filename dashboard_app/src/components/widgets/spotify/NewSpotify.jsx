import { useState } from "react";
import { useGlobalState } from "../../../state"
import SpotifyAuthButton from "./SpotifyAuthButton";
import * as ajax from "../../../lib/ajax"
import { Button, Flex, FormControl, FormLabel, Select, Text } from "@chakra-ui/react";
import { LargeSpotifyTrack } from "./Track/LargeSpotifyTrack";
import { MediumSpotifyTrack } from "./Track/MediumSpotifyTrack";
import { SmallSpotifyTrack } from "./Track/SmallSpotifyTrack";
import { MediumSpotifyPlaylist } from "./Playlist/MediumSpotifyPlaylist";
import { SmallSpotifyPlaylist } from "./Playlist/SmallSpotifyPlaylist";
import { LargeSpotifyPlaylist } from "./Playlist/LargeSpotifyPlaylist";

export const NewSpotify = () => {
    const [size, setSize] = useState();
    const [user, setUser] = useGlobalState("user");
    const [userSpotifyToken, setUserSpotifyToken] = useState(null);
    const [isTokenExpired, setIsTokenExpired] = useState(false)
    const [error, setError] = useState(null);
    const [playlists, setPlaylists] = useState([]);
    const [randomTrack, setRandomTrack] = useState();
    const [widgetType, setWidgetType] = useState();
    const [playlist, setPlaylist] = useState();

    console.log(playlist);

    const getUserToken = () => {
        ajax.getSpotifyToken(user.token)
            .then((response) => {
                setUserSpotifyToken(response.data.token)
                setIsTokenExpired(response.data.tokenExpired)
            })
            .catch((error) => {
                setUserSpotifyToken(null);
            })
    }

    const refreshUserToken = () => {
        ajax.refreshSpotifyToken(user.token)
            .then((response) => {
                setUserSpotifyToken(response.data.token);
                setIsTokenExpired(response.data.tokenExpired);
            })
            .catch((error) => {
                console.log(error);
            })
    }


    const getUserPlaylists = () => {
        ajax.getUserPlaylists(user.token)
            .then((response) => {
                setPlaylists(response.data.items)
            })
            .catch((error) => {
                console.log(error)
                setPlaylists(null);
            })
    }

    const getRandomEminemTrack = () => {
        ajax.getRandomEminemTrack(user.token)
            .then((response) => {
                setRandomTrack(response.data.id)
            })
            .catch((error) => {
                console.log(error);
            })
    }

    const renderPlaylists = () => {
        let tmp = [];
        playlists.forEach((e) => {
            tmp.push(<option value={e.id}>{e.name}</option>)
        })

        return tmp;
    }

    const renderWidget = () => {
        if (widgetType == "track") {
            if (size == "XL") {
                return (
                    <LargeSpotifyTrack track={randomTrack} />
                )
            } else if (size == "MD") {
                return (
                    <MediumSpotifyTrack track={randomTrack} />
                )
            } else {
                return (
                    <SmallSpotifyTrack track={randomTrack} />
                )
            }
        } else if (widgetType == "playlist") {
            if (size == "XL") {
                return (
                    <LargeSpotifyPlaylist playlist={playlist} />
                )
            } else if (size == "MD") {
                return (
                    <MediumSpotifyPlaylist playlist={playlist} />
                )
            } else {
                return (
                    <SmallSpotifyPlaylist playlist={playlist} />
                )
            }
        }
    }

    if (!userSpotifyToken) {
        getUserToken();
    }
    if (isTokenExpired) {
        refreshUserToken();
    }

    if (userSpotifyToken && !isTokenExpired && playlists.length == 0) {
        getUserPlaylists();
    }

    return (
        <>
            <Flex my={6}>
                {!userSpotifyToken &&
                    <SpotifyAuthButton />
                }
                {playlists.length > 0 &&
                    <FormControl w={"30%"}>
                        <FormLabel>Playlists</FormLabel>
                        <Select placeholder="Playlist" onChange={(e) => { setPlaylist(e.target.value) }}>
                            {renderPlaylists()}
                        </Select>
                    </FormControl>
                }
            </Flex>
            <Flex w={"40%"} justifyContent={"space-between"} mb={6}>
                <Button onClick={() => { setWidgetType("track"); getRandomEminemTrack() }}>Random Track</Button>
                <Button onClick={() => { setWidgetType("playlist"); }}>Full Playlist</Button>
            </Flex>
            <Flex w="40%" justifyContent="space-between" mb={6}>
                <Button onClick={() => { setSize("SM") }}>
                    Small
                </Button>
                <Button onClick={() => { setSize("MD") }}>
                    Medium
                </Button>
                <Button onClick={() => { setSize("XL") }}>
                    Large
                </Button>
            </Flex>
            <Flex backgroundColor={"transparent"}>
                {renderWidget()}
            </Flex>
        </>
    )
}