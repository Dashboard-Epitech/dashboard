import { Drawer, DrawerBody, DrawerCloseButton, DrawerContent, DrawerHeader, DrawerOverlay, Flex, Text } from "@chakra-ui/react"
import { createContext, useState } from "react"
import { NewCurrency } from "../currency/NewCurrency";
import { NewSpotify } from "../spotify/NewSpotify";
import { NewWeather } from "../weather/NewWeather";
import { WidgetTypesGrid } from "./WidgetTypeGrid";

export const WidgetType = createContext();

export const NewWidgetDrawer = ({ isOpen, onClose }) => {
    const [widgetType, setWidgetType] = useState(null);
    return (
        <Drawer onClose={onClose} isOpen={isOpen} size="xl">
            <DrawerOverlay />
            <WidgetType.Provider value={{ widgetType, setWidgetType }}>
                <DrawerContent fontFamily={"PT Mono"}>
                    <DrawerCloseButton />
                    <DrawerHeader>Create new widget</DrawerHeader>
                    <DrawerBody>
                        <WidgetTypesGrid />
                        {
                            widgetType && widgetType == "weather" && 
                            <NewWeather />
                        }
                        {
                            widgetType && widgetType == "spotify" && 
                            <NewSpotify />
                        }
                        {
                            widgetType && widgetType == "currency" && 
                            <NewCurrency />
                        }
                    </DrawerBody>
                </DrawerContent>
            </WidgetType.Provider>
        </Drawer>
    )
}