import { Button, Divider, Flex, FormControl, FormLabel, Grid, GridItem, Heading, Input, Select, Text } from "@chakra-ui/react"
import { useState } from "react";
import { TiWeatherPartlySunny } from "react-icons/ti";
import { LargeWeather } from "./LargeWeather";
import * as ajax from "../../../lib/ajax";
import { MediumWeather } from "./MediumWeather";
import { SmallWeather } from "./SmallWeather";

export const NewWeather = () => {
    const [size, setSize] = useState("");
    const [error, setError] = useState("");
    const [map, setMap] = useState({});
    const [city, setCity] = useState("City");
    const [temp, setTemp] = useState("Temperature");
    const [unit, setUnit] = useState("Unit")
    const [weatherIcon, setWeatherIcon] = useState(null);
    const [weatherDesc, setWeatherDesc] = useState("Description");
    const [weatherBackground, setWeatherBackground] = useState(null);

    console.log(size);

    const renderWidget = () => {
        if (error) {
            return <Flex><Text>{error}</Text></Flex>
        }
        switch (size) {
            case "XL":
                return (<LargeWeather city={city} temp={temp} unit={unit} weatherDesc={weatherDesc} weatherIcon={weatherIcon} weatherBackground={weatherBackground} />)
            case "MD":
                return (<MediumWeather city={city} temp={temp} unit={unit} weatherDesc={weatherDesc} weatherIcon={weatherIcon} weatherBackground={weatherBackground} />)
            case "SM":
                return (<SmallWeather city={city} temp={temp} unit={unit} weatherDesc={weatherDesc} weatherIcon={weatherIcon} weatherBackground={weatherBackground} />)

        }
        if (size == "XL") {
        }
    }

    const checkWeatherApi = () => {
        ajax.getWeather(city)
            .then((response) => {
                setError(null)
                let weather = response.data.weather[0];
                console.log(weather)
                setTemp(response.data.main.temp);
                setWeatherDesc(weather.main);
                setWeatherIcon(`/weather/icon/${weather.icon}.png`);
                setWeatherBackground(`/weather/back/${weather.icon}.jpg`)
                console.log(temp, weatherDesc, weatherIcon)
            })
            .catch((error) => {
                setError("City not found. Please enter a valid city")
            })
    }

    const submitForm = () => {

    }

    return (
        <>
            <form>
                <Flex h="20%" my={6} flexDir="column" justifyContent={"center"}>

                    <FormControl mb={6}>
                        <FormLabel>
                            Choose a City !
                        </FormLabel>
                        <Flex w={"50%"}>
                            <Input type="text" w={"50%"} me={2} onChange={(e) => setCity(e.target.value)}></Input>
                            <Button colorScheme={"green"} onClick={() => checkWeatherApi()}>Ok</Button>
                        </Flex>
                    </FormControl>

                    <Flex w="40%" justifyContent="space-between" mb={6}>
                        <Button onClick={() => { setSize("SM") }}>
                            Small
                        </Button>
                        <Button onClick={() => { setSize("MD") }}>
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
                {
                    !error &&
                    <Flex><Button colorScheme={"green"} onClick={() => submitForm()}>Submit</Button></Flex>
                }
            </form>
        </>
    )
}