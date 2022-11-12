import { Box, Flex, Grid, GridItem, Image, Text } from "@chakra-ui/react"
import { WidgetType } from "./NewWidgetDrawer"; 
import { useContext } from "react";
import { FaDollarSign, FaSpotify, FaYoutube } from "react-icons/fa";
import { TiWeatherPartlySunny } from 'react-icons/ti'
import epitechLogo from "../../../icons/gege.png"

import { useGlobalState } from "../../../state"

export const WidgetTypesGrid = () => {
    const [user, setUser] = useGlobalState("user");
    const { widgetType, setWidgetType } = useContext(WidgetType);

    return (
        <Flex minWidth={"100%"} flexDir="column">
            <Flex mb={6}>
                <Text fontSize={"3xl"}>Choose a widget type</Text>
            </Flex>
            <Grid w={"100%"} templateColumns={"repeat(3, 1fr)"} templateRows={"repeat(2, 1fr)"} gap={6}>
                <GridItem onClick={(e) => setWidgetType("weather")}>
                    <Flex h={"100%"} justifyContent={"space-between"} alignItems={"center"} flexDir="column" p={4} backgroundColor="gray.600" borderRadius={"20px"}>
                        <Flex>
                            <TiWeatherPartlySunny size={"4rem"} />
                        </Flex>
                        <Flex>
                            <Text fontWeight={"bold"} fontSize="2xl">Weather</Text>
                        </Flex>
                    </Flex>
                </GridItem>
                <GridItem>
                    <Flex h={"100%"} justifyContent={"space-between"} alignItems={"center"} flexDir="column" p={4} backgroundColor="gray.600" borderRadius={"20px"}>
                        <Flex>
                            <FaDollarSign size={"4rem"} />
                        </Flex>
                        <Flex>
                            <Text fontWeight={"bold"} fontSize="2xl">Currencies</Text>
                        </Flex>
                    </Flex>
                </GridItem>
                <GridItem>
                    <Flex h={"100%"} justifyContent={"space-between"} alignItems={"center"} flexDir="column" p={4} backgroundColor="gray.600" borderRadius={"20px"}>
                        <Flex>
                            <FaYoutube size={"4rem"} />
                        </Flex>
                        <Flex>
                            <Text fontWeight={"bold"} fontSize="2xl">Youtube</Text>
                        </Flex>
                    </Flex>
                </GridItem>
                <GridItem>
                    <Flex h={"100%"} justifyContent={"space-between"} alignItems={"center"} flexDir="column" p={4} backgroundColor="gray.600" borderRadius={"20px"}>
                        <Flex>
                            <Image boxSize={"60px"} src='https://assets.stickpng.com/images/5845cd430b2a3b54fdbaecf8.png' />
                        </Flex>
                        <Flex>
                            <Text fontWeight={"bold"} fontSize="2xl">Memes</Text>

                        </Flex>
                    </Flex>
                </GridItem>
                <GridItem>
                    <Flex h={"100%"} justifyContent={"space-between"} alignItems={"center"} flexDir="column" p={4} backgroundColor="gray.600" borderRadius={"20px"}>
                        <Flex>
                            <FaSpotify size={"4rem"} />
                        </Flex>
                        <Flex>
                            <Text fontWeight={"bold"} fontSize="2xl">Spotify</Text>
                        </Flex>
                    </Flex>
                </GridItem>
                <GridItem>
                    <Flex h={"100%"} justifyContent={"space-between"} alignItems={"center"} flexDir="column" p={4} backgroundColor="gray.600" borderRadius={"20px"}>
                        <Flex>
                            <Image boxSize={"60px"} src={epitechLogo} />
                        </Flex>
                        <Flex>
                            <Text fontWeight={"bold"} fontSize="2xl">Epitech</Text>
                        </Flex>
                    </Flex>
                </GridItem>
            </Grid>
        </Flex>
    )
}