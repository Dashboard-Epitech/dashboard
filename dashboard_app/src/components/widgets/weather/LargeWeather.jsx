import { Divider, Flex, FormControl, FormLabel, Grid, GridItem, Heading, Image, Input, Select, Text } from "@chakra-ui/react"
import { useState } from "react";
import { FaQuestion } from "react-icons/fa";
import { TiWeatherPartlySunny } from "react-icons/ti";

export const LargeWeather = ({city, temp, unit, weatherDesc, weatherIcon, weatherBackground}) => {
    const renderWeatherIcon = () => {
        return weatherIcon ? <Image src={weatherIcon} boxSize={"80px"}></Image> : <FaQuestion size="5rem"/>;
    }

    return (
        <>
            <Flex 
                w={"100%"} 
                h="200px" 
                backgroundImage={weatherBackground ?? null} 
                backgroundPosition="center" 
                backgroundSize="cover" 
                backGroundColor={"gray.600"} 
                my={6} 
                p={6} 
                borderRadius={"20px"} 
                color="green.400"
                >
                <Grid templateColumns={"repeat(4, 1fr)"} w="100%">
                    <GridItem colSpan={3}>
                        <Flex w={"100%"} h="100%">
                            <Flex w={"80%"} h="100%" flexDir="column" justifyContent={"space-between"}>
                                <Flex><Text fontSize={"3xl"}>{city}</Text></Flex>
                                <Flex><Text fontSize={"2xl"}>{temp} {unit}</Text></Flex>
                            </Flex>
                            <Flex w="20%" justifyContent={"center"}>
                                <Divider orientation="vertical" backgroundColor={"black"}></Divider>
                            </Flex>
                        </Flex>
                    </GridItem>
                    <GridItem colSpan={1}>
                        <Flex w="100%" h="100%" flexDir={"column"} justifyContent="center" alignItems={"center"}>
                            <Flex>
                                {renderWeatherIcon()}
                            </Flex>
                            <Text fontWeight={"bold"} mt={2}>
                                {weatherDesc}
                            </Text>
                        </Flex>
                    </GridItem>
                </Grid>
            </Flex>
        </>
    )
}