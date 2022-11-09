import { Box, Center, Image } from "@chakra-ui/react";
import React from "react";

const Weather = ({ size, ...props }) => {

    switch (size) {
        case 1: return small();
    }

    function small() {
        return (
            <Box
                width="500px"
                height="100px"
                border="2px solid silver"
                borderRadius='10px'
                backgroundPosition="center"
                backgroundSize="cover"
                backgroundImage={"url('weather/back/" + props.icon + ".jpg')"}
                textColor={String(props.icon).includes("d") && !String(props.icon).includes("04") ? "black" : "white"}
                position="relative"
            >
                    <Center
                        width="200px"
                        height="100px"
                        fontSize="30px"
                        fontFamily="revert"
                        position="absolute"
                    >
                        {props.weather.city}
                    </Center>

                    <Box
                        width="150px"
                        height="100px"
                        position="absolute"
                        marginX="200px"
                    >
                        <Center
                            height="33px"
                            position="absolute"
                        >
                            Temperature: {props.weather.temp}°
                        </Center>
                        <Center
                            height="33px"
                            position="absolute"
                            marginY="33px"
                        >
                            Vent: {props.weather.wind} km/h
                        </Center>
                        <Center
                            height="33px"
                            position="absolute"
                            marginY="66px"
                        >
                            Pression: {props.weather.pressure} hPa
                        </Center>
                    </Box>

                    <Center
                        width="150px"
                        height="100px"
                        position="absolute"
                        marginX="350px"
                        backgroundColor="rgba(255, 255, 255, .3);"
                    >
                        <Image src={"./weather/icon/" + props.icon + ".png"} boxSize="60px" color={"white"}/>
                    </Center>
            </Box>
        )
    }
}

export default Weather