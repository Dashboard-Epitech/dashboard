import { Flex } from "@chakra-ui/react"
import { Outlet } from "react-router-dom"
import { WidgetControls } from "./WidgetControls"

export const WidgetMenu = () => {
    return(
        <Flex h={"92vh"} w="100%" p={5}>
            <Outlet />
        </Flex>
    )
}