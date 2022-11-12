import { Button, Divider, Flex, FormControl, FormLabel, Grid, GridItem, Heading, Input, Select, Text } from "@chakra-ui/react"
import { useState } from "react";
import { TiWeatherPartlySunny } from "react-icons/ti";
import { LargeWeather } from "./LargeWeather";

export const NewWeather = () => {
    const [size, setSize] = useState("");
    const [error, setError] = useState("");
    const [map, setMap] = useState({});
    const [city, setCity] = useState("City");
    const [temp, setTemp] = useState("Temperature");
    const [unit, setUnit] = useState("Unit")
    const [weatherIcon, setWeatherIcon] = useState(null);
    const [weatherDesc, setweatherDesc] = useState("Description");

    console.log(size);

    const renderWidget = () => {
        if (size == "XL") {
            return (<LargeWeather city={city} temp={temp} unit={unit} weatherDesc={weatherDesc} weatherIcon={weatherIcon}/>)
        }
    }

    return (
        <>
            <Flex h="20%" my={6} flexDir="column" justifyContent={"center"}>
                <form>
                    <FormControl mb={6}>
                        <FormLabel>
                            Choose a City !
                        </FormLabel>
                        <Flex w={"50%"}>
                            <Input type="text" w={"50%"} me={2}></Input>
                            <Button colorScheme={"green"}>Ok</Button>
                        </Flex>
                    </FormControl>
                </form>
                <Flex w="40%" justifyContent="space-between" mb={6}>
                    <Button>
                        Small
                    </Button>
                    <Button>
                        Medium
                    </Button>
                    <Button onClick={() => { setSize("XL") }}>
                        Large
                    </Button>
                </Flex>
                <Flex w="30%" justifyContent="space-between">
                    <Button onClick={() => setUnit("C°")}>
                        Celcius
                    </Button>
                    <Button onClick={() => setUnit("F")}>
                        Fahrenheit
                    </Button>
                </Flex>
            </Flex>
            {renderWidget()}

        </>
    )
}