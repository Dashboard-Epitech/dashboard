import { Flex } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { Navigate, Outlet } from "react-router-dom"
import { useGlobalState } from "../../state"
import { Nav } from "../nav/Nav"
import { Sidebar } from "../nav/Sidebar"
import * as ajax from "../../lib/ajax";

export const HomePage = () => {
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const [user, setUser] = useGlobalState("USER");

    console.log(accessToken);

    if (!accessToken) {
        return <Navigate to="/auth/login" />
    } else if (!user) {
        ajax.getUserData(accessToken)
            .then((response) => {
                let userData = {
                    userId: response.data.id,
                    userEmail: response.data.email,
                    userUsername: response.data.username
                }

                setUser(userData)
                localStorage.setItem('USER', JSON.stringify(userData));
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return (
        <Flex p={0} flexDirection={"column"} h={"100vh"}>
            <Flex w={"100%"} p={3} alignItems={"center"} h="8%">
                <Nav />
            </Flex>
            <Flex w={'100%'} minHeight={"92%"}>
                <Sidebar />
                <Outlet />
            </Flex>
        </Flex>
    )
}