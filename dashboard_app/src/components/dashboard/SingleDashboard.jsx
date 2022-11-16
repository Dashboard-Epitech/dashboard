import { Button, Flex, Heading, Spacer, useDisclosure } from "@chakra-ui/react"
import { useState } from "react"
import { useParams } from "react-router-dom"
import { useGlobalState } from "../../state"
import { NewWidgetDrawer } from "../widgets/controls/NewWidgetDrawer"
import * as ajax from "../../lib/ajax";
import { WidgetRenderer } from "../widgets/WidgetRenderer"
import { useEffect } from "react"


export const SingleDashboard = ({ dashboardId }) => {
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const [data, setData] = useState(null);
    const [hasError, setHasError] = useState(true);
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [widgets, setWidgets] = useState([]);

    useEffect(() => {
        getDashboardData();
        getWidgets();
    }, [])
    

    const getDashboardData = () => {
        ajax.getDashboardData(accessToken, dashboardId.id)
            .then((response) => {
                setData(response.data)
                setHasError(false);
            })
            .catch((error) => {
                setHasError(true)
            })
    }

    const getWidgets = () => {
        ajax.getDashboardWidgets(accessToken, dashboardId.id)
            .then((response) => {
                setWidgets(response.data)
                setHasError(false)
            })
            .catch((error) => {
                setHasError(true)
            })
    }

    return (
        <>
            <Flex minW={"100%"} minH={"100%"} backgroundColor={"gray.600"} borderRadius={"40px"} p={12} flexDir="column">
                <Flex w={"100%"} h={"fit-content"} justifyContent="space-between" mb={6}>
                    {data && 
                        <Heading>{data.name ?? null}</Heading>
                    }
                    <Flex>
                        <Button onClick={onOpen}>Create A Widget</Button>
                    </Flex>
                </Flex>
                <Flex w={"100%"} minH={"90%"}>
                    {widgets.length > 0 &&
                        <WidgetRenderer widgets={widgets} />
                    }
                </Flex>
            </Flex>
            <NewWidgetDrawer isOpen={isOpen} onClose={onClose} dashboardId={dashboardId.id}/>
        </>
    )
}