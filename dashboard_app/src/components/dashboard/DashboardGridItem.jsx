import { Flex, Heading, Spacer, Text } from "@chakra-ui/react"
import { Link, Navigate, useNavigate } from "react-router-dom"

export const DashboardGridItem = ({dashboardData, widgetCount, widgetTypes}) => {
    const navigate = useNavigate();

    const renderTypeIcons = () => {

    }

    return(
        <Flex w={"300px"} h={"200px"} key={dashboardData.id} borderRadius={"20px"} backgroundColor={"gray.500"} flexDirection="column" p={6} me={6}>
            <Link as={Heading} to={`./${dashboardData.id}`}>{dashboardData.name}</Link>
            <Text fontSize={"2xl"}>{widgetCount} Widgets</Text>
            <Spacer />
        </Flex>
    )
}