import { Button, Flex, FormControl, FormLabel, Input, Text } from "@chakra-ui/react"
import { useState } from "react";
import * as ajax from "../../../lib/ajax";
import { useGlobalState } from "../../../state";
import { MediumWeatherPreview } from "./preview/MediumWeatherPreview";
import { SmallWeatherPreview } from "./preview/SmallWeatherPreview";
import { LargeWeatherPreview } from "./preview/LargeWeatherPreview";

export const NewWeather = ({dashboardId}) => {
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const [size, setSize] = useState("");
    const [error, setError] = useState("");
    const [city, setCity] = useState("City");
    const [temp, setTemp] = useState("Temperature");
    const [unit, setUnit] = useState("Unit");
    const [refreshRate, setRefreshRate] = useState();
    const [weatherIcon, setWeatherIcon] = useState(null);
    const [weatherDesc, setWeatherDesc] = useState("Description");
    const [weatherBackground, setWeatherBackground] = useState(null);

    const renderWidget = () => {
        if (error) {
            return <Flex><Text>{error}</Text></Flex>
        }
        switch (size) {
            case "XL":
                return (<LargeWeatherPreview city={city} temp={temp} unit={unit} weatherDesc={weatherDesc} weatherIcon={weatherIcon} weatherBackground={weatherBackground} />)
            case "MD":
                return (<MediumWeatherPreview city={city} temp={temp} unit={unit} weatherDesc={weatherDesc} weatherIcon={weatherIcon} weatherBackground={weatherBackground} />)
            case "SM":
                return (<SmallWeatherPreview city={city} temp={temp} unit={unit} weatherDesc={weatherDesc} weatherIcon={weatherIcon} weatherBackground={weatherBackground} />)
            default:
                return
        }
    }

    const checkWeatherApi = () => {
        ajax.getWeather(accessToken, city, unit)
            .then((response) => {
                if (response.data.cod == 200) {
                    setTemp(Math.round(response.data.main.temp))
                    setWeatherDesc(response.data.weather[0].description)
                    setWeatherIcon(response.data.weather[0].icon)
                    setWeatherBackground(response.data.weather[0].icon)
                    setError(null);
                } else {
                    setError("City not found. Please enter a valid city")
                }
            })
            .catch((error) => {
                setError("City not found. Please enter a valid city")
            })
    }

    const submitWidgetForm = () => {
        ajax.newWeatherWidget(accessToken, dashboardId, city, unit, size, refreshRate)
            .then((response) => {
                console.log(response)
            })
            .catch((error) => {
                console.log(error)
            })
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
                    <Flex w="30%" justifyContent="space-between" mb={6}>
                        <Button onClick={() => setUnit("C")}>
                            Celcius
                        </Button>
                        <Button onClick={() => setUnit("F")}>
                            Fahrenheit
                        </Button>
                    </Flex>
                    <Flex w="30%" justifyContent="space-between">
                        <Button onClick={() => setRefreshRate(60 * 1000)}>
                            1min
                        </Button>
                        <Button onClick={() => setRefreshRate(60 * 10 * 1000)}>
                            10min
                        </Button>
                    </Flex>
                </Flex>
                {renderWidget()}
                {
                    !error &&
                    <Flex><Button colorScheme={"green"} onClick={() => submitWidgetForm()}>Submit</Button></Flex>
                }
            </form>
        </>
    )
}