import { Divider, Flex, FormControl, FormLabel, Grid, GridItem, Heading, Image, Input, Select, Text } from "@chakra-ui/react"
import { useEffect, useState } from "react";
import { FaQuestion } from "react-icons/fa";
import { TiWeatherPartlySunny } from "react-icons/ti";
import { useGlobalState } from "../../../../state";
import * as ajax from '../../../../lib/ajax';


export const SmallWeather = ({city, unit, refreshRate}) => {
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const [data, setData] = useState(null);
    const [error, setError] = useState();

    console.log(refreshRate)

    useEffect(() => {
        getData()
    }, [])
    

    const getData = () => {
        ajax.getWeather(accessToken, city, unit)
            .then((response) => {
                console.log(response)
                setData({
                    widgetCity: response.data.name,
                    widgetTemp: Math.round(response.data.main.temp),
                    widgetDescription: response.data.weather[0].main,
                    widgetBackground: response.data.weather[0].icon,
                    widgetIcon: response.data.weather[0].icon,
                })
            })
            .catch((error) => {
                console.log(error)
            })
    }

    const renderWeatherIcon = () => {
        return data && data.widgetIcon ? <Image src={`/weather/icon/${data.widgetIcon}.png`} boxSize={"40px"}></Image> : <FaQuestion size="5rem"/>;
    }

    return (
        <>
            <Flex 
                m={3}
                w="200px" 
                h="100px" 
                backgroundImage={data && data.widgetBackground ? `/weather/back/${data.widgetBackground}.jpg` : null} 
                backgroundPosition="center" 
                backgroundSize="cover" 
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
                                <Flex><Text fontSize={"sm"} fontWeight="bold">{data && data.widgetTemp} {unit}</Text></Flex>
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