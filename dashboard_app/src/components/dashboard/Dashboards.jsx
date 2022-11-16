import { Button, Flex, FormControl, FormLabel, Grid, GridItem, Heading, Input, useBreakpointValue, useDisclosure, usePanGesture } from "@chakra-ui/react";
import { useState } from "react";
import { Navigate, Outlet, useNavigate, useParams, useSearchParams } from "react-router-dom";
import { useGlobalState } from "../../state"
import { Sidebar } from "../nav/Sidebar";
import { NewWidgetDrawer } from "../widgets/controls/NewWidgetDrawer";
import * as ajax from '../../lib/ajax'
import { NewDashboard } from "./NewDashboard";
import { Nav } from "../nav/Nav";
import { DashboardsGrid } from "./DashboardsGrid";
import { SingleDashboard } from "./SingleDashboard";

export const Dashboards = () => {
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const dashboardId = useParams();
    const [user, setUser] = useGlobalState("USER");
    const [isNew, setIsNew] = useState();
    const [dashboards, setDashboards] = useState([]);
    const [selectedDashboard, setSelectedDashboard] = useState();
    const [checked, setChecked] = useState(false);
    const { isOpen, onOpen, onClose } = useDisclosure();
    const navigate = useNavigate();

    const getDashboards = () => {
        ajax.getUserDashboards(accessToken)
            .then((response) => {
                setDashboards(response.data)
                setChecked(true);
            })
            .catch((error) => {
                console.log(error)
            })
    }

    if (dashboards.length == 0 && !checked) {
        getDashboards();
    }

    return (
        <Flex w={"100%"} h={"100%"}>
            <Flex  w={"100%"} h={"100%"} flexDir="column" p={6}>
                {dashboards.length == 0 && checked && 
                    <NewDashboard />
                }
                {dashboards.length > 0 && !dashboardId.id &&
                    <DashboardsGrid dashboards={dashboards} />
                }
                {dashboardId.id && 
                    <SingleDashboard dashboardId={dashboardId}/>
                }
            </Flex>
            <NewWidgetDrawer isOpen={isOpen} onClose={onClose} />
        </Flex>
    )
}