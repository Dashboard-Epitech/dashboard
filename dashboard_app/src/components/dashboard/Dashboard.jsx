import { Grid, GridItem, Text, useBreakpointValue } from "@chakra-ui/react";
import { Navigate, Outlet } from "react-router-dom";
import { useGlobalState } from "../../state"
import { Sidebar } from "../nav/Sidebar";

export const Dashboard = () => {
    const [user, setUser] = useGlobalState("user");
    const gridColLayout = useBreakpointValue(
        {
            sm: 'repeat(3, 1fr)',
            md: 'repeat(4, 1fr)',
            lg: 'repeat(5, 1fr)'
        }
    )

    return (
        <>  
            <Grid
                h='100%'
                w='100%'
                p={4}
                templateRows='repeat(8, 1fr)'
                templateColumns={gridColLayout}
                gap={4}
            >
                <GridItem rowSpan={2} colSpan={2} rowStart={2} colStart={2} bg='tomato'>
                    One
                </GridItem>
                <GridItem colSpan={1} bg='papayawhip'>
                    Two
                </GridItem>
                <GridItem colSpan={1} bg='papayawhip'>
                    Three
                </GridItem>
                <GridItem colSpan={1} bg='tomato'>
                    Four
                </GridItem>
            </Grid>
        </>
    )
}