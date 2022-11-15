import { Button, Flex, FormControl, FormLabel, Grid, GridItem, Heading, Input, useBreakpointValue, useDisclosure } from "@chakra-ui/react";
import { useGlobalState } from "../../state";
import * as ajax from '../../lib/ajax'
import { useState } from "react";
import { Navigate, useNavigate } from "react-router-dom";

export const NewDashboard = () => {
    const [accessToken, setAccessToken] = useGlobalState('ACCESS_TOKEN');
    const [dashboardName, setDashboardName] = useState("");
    const navigate = useNavigate();

    const updateName = (v) => {
        setDashboardName(v);
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        ajax.newDashboard(accessToken, dashboardName)
            .then((response) => {
                navigate("../")
            })
            .catch((error) => {
                console.log(error)
            })
    }

    return (
        <Flex flexDir={"column"} justifyContent="center" alignItems={"center"}>
            <Heading mb={6}>No dashboards found. Please create a dashboard</Heading>
            <form onSubmit={(e) => { handleSubmit(e) }}>
                <FormControl mb={6}>
                    <FormLabel>Dashboard Name</FormLabel>
                    <Input type={"text"} required={true} placeholder="Name" onChange={(e) => { updateName(e.target.value) }}></Input>
                </FormControl>
                <Button type="submit">Create</Button>
            </form>
        </Flex>
    )
}