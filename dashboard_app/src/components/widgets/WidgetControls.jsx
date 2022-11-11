import { Flex, Grid, GridItem, Text, useBreakpointValue } from "@chakra-ui/react"
import { FaEdit, FaPlus, FaShare, FaTrash } from "react-icons/fa"
import { Link, Outlet } from "react-router-dom"

export const WidgetControls = () => {
    const gridColLayout = useBreakpointValue(
        {
            sm: 'repeat(2, 1fr)',
            md: 'repeat(6, 1fr)',
            lg: 'repeat(8, 1fr)'
        }
    )

    const blockSize = useBreakpointValue(
        {
            sm: 1,
            md: 2
        }
    )

    const displayTest = useBreakpointValue(
        {
            sm: "none",
            md: "flex",
            lg: "flex",
        }
    )

    return (
        <Grid
            h='100%'
            w='100%'
            p={4}
            templateRows='repeat(4, 1fr)'
            templateColumns={gridColLayout}
            gap={4}
            fontFamily="PT Mono"
        >
            <GridItem as={Link} rowSpan={1} colSpan={blockSize} bg='gray.600' p={4} borderRadius={"20px"} _hover={{bg: "gray.500", textDecoration: "none"}} to="./new">
                <Flex h="100%" w="100%">
                    <Flex alignItems={"center"} mx="auto">
                        <FaPlus size={"2rem"} />
                        <Text ms={4} fontSize="2xl" display={displayTest}>Create</Text>
                    </Flex>
                </Flex>
            </GridItem>
            <GridItem as={Link} rowSpan={1} colSpan={blockSize} bg='gray.600' p={4} borderRadius={"20px"} _hover={{bg: "gray.500", textDecoration: "none"}} to="/edit">
                <Flex h="100%" w="100%">
                    <Flex alignItems={"center"} mx="auto">
                        <FaEdit size={"2rem"} />
                        <Text ms={4} fontSize="2xl" display={displayTest}>Edit</Text>
                    </Flex>
                </Flex>
            </GridItem>
            <GridItem as={Link} rowSpan={1} colSpan={blockSize} bg='gray.600' p={4} borderRadius={"20px"} _hover={{bg: "gray.500", textDecoration: "none"}} to="/delete">
                <Flex h="100%" w="100%">
                    <Flex alignItems={"center"} mx="auto">
                        <FaTrash size={"2rem"} />
                        <Text ms={4} fontSize="2xl" display={displayTest}>Delete</Text>
                    </Flex>
                </Flex>
            </GridItem>
            <GridItem as={Link} rowSpan={1} colSpan={blockSize} bg='gray.600' p={4} borderRadius={"20px"} _hover={{bg: "gray.500", textDecoration: "none"}} to="/share">
                <Flex h="100%" w="100%">
                    <Flex alignItems={"center"} mx="auto">
                        <FaShare size={"2rem"} />
                        <Text ms={4} fontSize="2xl" display={displayTest}>Share</Text>
                    </Flex>
                </Flex>
            </GridItem>
            <Outlet />
        </Grid>
    )
}