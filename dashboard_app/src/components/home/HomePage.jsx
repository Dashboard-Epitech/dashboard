import { Flex } from "@chakra-ui/react"
import { useEffect, useState } from "react"
import { Navigate, Outlet } from "react-router-dom"
import { useGlobalState } from "../../state"
import { Nav } from "../nav/Nav"
import { Sidebar } from "../nav/Sidebar"

export const HomePage = () => {
    const [user, setUser] = useGlobalState("user");
    if (!user) {
        return <Navigate to="/auth/login" />
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