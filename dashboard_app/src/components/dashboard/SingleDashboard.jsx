import { Button, Flex, Heading, Spacer } from "@chakra-ui/react"
import { useState } from "react"
import { useParams } from "react-router-dom"
import { useGlobalState } from "../../state"

export const SingleDashboard = ({dashboardId}) => {
    const [widgets, setWidgets] = useState([]);

    const getWidgets = () => {

    }

    return(
        <Flex minW={"100%"} minH={"100%"} backgroundColor={"gray.600"} borderRadius={"40px"} p={12}>
            <Flex w={"100%"} h={"fit-content"} justifyContent="space-between">
                <Heading>Dashboard : {dashboardId.id}</Heading>
                <Flex>
                    <Button>Create A Widget</Button>
                </Flex>
            </Flex>
        </Flex>
    )
}