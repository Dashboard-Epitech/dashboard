import { Divider, Flex, FormControl, FormLabel, Grid, GridItem, Heading, Image, Input, Select, Text } from "@chakra-ui/react"
import { useState } from "react";
import { FaQuestion } from "react-icons/fa";
import { TiWeatherPartlySunny } from "react-icons/ti";

export const SmallWeatherPreview = ({city, temp, unit, weatherDesc, weatherIcon, weatherBackground}) => {
    const renderWeatherIcon = () => {
        return weatherIcon ? <Image src={`/weather/icon/${weatherIcon}.png`} boxSize={"40px"}></Image> : <FaQuestion size="5rem"/>;
    }

    return (
        <>
            <Flex 
                w={"150px"} 
                h="100px" 
                backgroundImage={weatherBackground ? `/weather/back/${weatherBackground}.jpg` : null} 
                backgroundPosition="center" 
                backgroundSize="cover" 
                backGroundColor={"gray.600"} 
                my={6} 
                p={2} 
                borderRadius={"20px"} 
                color="white" 
            >
                <Grid templateColumns={"repeat(4, 1fr)"} w="100%">
                    <GridItem colSpan={3}>
                        <Flex w={"100%"} h="100%" p={3}>
                            <Flex w={"80%"} h="100%" flexDir="column" justifyContent={"space-between"}>
                                <Flex><Text fontSize={"md"} fontWeight="bold">{city}</Text></Flex>
                                <Flex><Text fontSize={"sm"} fontWeight="bold">{temp} {unit}</Text></Flex>
                            </Flex>
                        </Flex>
                    </GridItem>
                    <GridItem colSpan={1}>
                        <Flex w="100%" h="100%" flexDir={"column"} justifyContent="center" alignItems={"center"}>
                            <Flex>
                                {renderWeatherIcon()}
                            </Flex>
                        </Flex>
                    </GridItem>
                </Grid>
            </Flex>
        </>
    )
}