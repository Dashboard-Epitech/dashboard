import { Button, Flex, Heading, Spacer, useDisclosure } from "@chakra-ui/react"
import { useState } from "react"
import { useParams } from "react-router-dom"
import { useGlobalState } from "../../state"
import { NewWidgetDrawer } from "../widgets/controls/NewWidgetDrawer"
import * as ajax from "../../lib/ajax";


export const SingleDashboard = ({ dashboardId }) => {
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [widgets, setWidgets] = useState([]);

    const getWidgets = () => {
        ajax.getDashboardWidgets(accessToken, dashboardId.id)
            .then((response) => {
                console.log(response);
            })
            .catch((error) => {
                console.log(error)
            })
    }
    getWidgets();

    return (
        <>
            <Flex minW={"100%"} minH={"100%"} backgroundColor={"gray.600"} borderRadius={"40px"} p={12}>
                <Flex w={"100%"} h={"fit-content"} justifyContent="space-between">
                    <Heading>Dashboard : {dashboardId.id}</Heading>
                    <Flex>
                        <Button onClick={onOpen}>Create A Widget</Button>
                    </Flex>
                </Flex>
            </Flex>
            <NewWidgetDrawer isOpen={isOpen} onClose={onClose} dashboardId={dashboardId.id}/>
        </>
    )
}