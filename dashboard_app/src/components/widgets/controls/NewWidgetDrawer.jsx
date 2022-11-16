import { Drawer, DrawerBody, DrawerCloseButton, DrawerContent, DrawerHeader, DrawerOverlay, Flex, Text } from "@chakra-ui/react"
import { createContext, useState } from "react"
import { NewCurrency } from "../currency/NewCurrency";
import { NewSpotify } from "../spotify/NewSpotify";
import { NewWeather } from "../weather/NewWeather.jsx";
import { WidgetTypesGrid } from "./WidgetTypeGrid";

export const WidgetType = createContext();

export const NewWidgetDrawer = ({ isOpen, onClose, dashboardId }) => {
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
                            <NewWeather dashboardId={dashboardId}/>
                        }
                        {
                            widgetType && widgetType == "spotify" && 
                            <NewSpotify dashboardId={dashboardId}/>
                        }
                        {
                            widgetType && widgetType == "currency" && 
                            <NewCurrency dashboardId={dashboardId}/>
                        }
                    </DrawerBody>
                </DrawerContent>
            </WidgetType.Provider>
        </Drawer>
    )
}