import { Drawer, DrawerBody, DrawerCloseButton, DrawerContent, DrawerHeader, DrawerOverlay, Flex, Text } from "@chakra-ui/react"
import { createContext, useState } from "react"
import { NewWeather } from "../weather/NewWeather";
import { WidgetTypesGrid } from "./WidgetTypeGrid";

export const WidgetType = createContext();

export const NewWidgetDrawer = ({ isOpen, onClose }) => {
    const [widgetType, setWidgetType] = useState(null);
    console.log(widgetType)
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
                    </DrawerBody>
                </DrawerContent>
            </WidgetType.Provider>
        </Drawer>
    )
}