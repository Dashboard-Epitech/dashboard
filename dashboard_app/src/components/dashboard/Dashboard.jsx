import { Button, Flex, Grid, GridItem, useBreakpointValue, useDisclosure } from "@chakra-ui/react";
import { Navigate, Outlet } from "react-router-dom";
import { useGlobalState } from "../../state"
import { Sidebar } from "../nav/Sidebar";
import { NewWidgetDrawer } from "../widgets/controls/NewWidgetDrawer";

export const Dashboard = () => {
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const [user, setUser] = useGlobalState("USER");
    const { isOpen, onOpen, onClose } = useDisclosure();
    const gridColLayout = useBreakpointValue(
        {
            sm: 'repeat(3, 1fr)',
            md: 'repeat(4, 1fr)',
            lg: 'repeat(5, 1fr)'
        }
    )

    return (
        <>
            <Flex w={"100%"} flexDir="column" p={6}>
                <Button w="100px" mb={6} onClick={onOpen}>Create</Button>
                <Grid
                    h='100%'
                    w='100%'
                    templateRows='repeat(8, 1fr)'
                    templateColumns={gridColLayout}
                    gap={4}
                >
                    <GridItem rowSpan={2} colSpan={5} rowStart={2} colStart={1} bg='tomato'>
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
            </Flex>
            <NewWidgetDrawer isOpen={isOpen} onClose={onClose} />
        </>
    )
}