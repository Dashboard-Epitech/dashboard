import { Avatar, Button, Divider, Flex, Heading, IconButton, Spacer, Switch, Text, useColorMode, useColorModeValue, useDisclosure } from "@chakra-ui/react"
import { useState } from "react";
import { FaCaretLeft, FaChevronLeft, FaChevronRight, FaCog, FaHome, FaMoon, FaSun, FaUser, FaUserCircle } from "react-icons/fa"
import { MdSecurity, MdWidgets } from "react-icons/md";
import { ImExit } from "react-icons/im";
import { SidebarItem } from "./SidebarItem";
import { useGlobalState } from "../../state";
import LogoutModal from "../modals/LogoutModal";
import { SidebarLogout } from "./SidebarLogout";

export const Sidebar = () => {
    const [accessToken, setAccessToken] = useGlobalState('ACCESS_TOKEN');
    const [user, setUser] = useGlobalState("USER");
    const [navSize, setNavSize] = useState("large");
    const { toggleColorMode } = useColorMode();
    const {isOpen, onOpen, onClose} = useDisclosure();
    const colorIcons = useColorModeValue(<FaSun />, <FaMoon />);
    const sideBarBackground = useColorModeValue("gray.300", "gray.600");

    return (
        <Flex
            pos="sticky"
            h="100%"
            boxShadow="md"
            w={navSize == "small" ? "75px" : "350px"}
            borderRadius={navSize == "small" ? "0px 15px 15px 0px" : "0px 30px 30px 0px"}
            flexDir={"column"}
            justifyContent="space-between"
            backgroundColor={sideBarBackground}
        >
            <Flex
                p={"5%"}
                flexDir="column"
                w={"100%"}
                alignItems="flex-start"
                mb={4}
            >
                <IconButton
                    alignSelf={navSize == "small" ? "center" : "end"}
                    mt={2}
                    backgroundColor={"transparent"}
                    _hover={{ backgroundColor: 'transparent' }}
                    icon={navSize == "small" ? <FaChevronRight /> : <FaChevronLeft />}
                    onClick={() => {
                        if (navSize == "small") 
                            setNavSize("large")
                        else 
                            setNavSize("small")
                    }}
                />
                <SidebarItem navSize={navSize} icon={<FaHome size={"1.3rem"} />} title="Dashboard" target="/dashboard"/>
                <SidebarItem navSize={navSize} icon={<MdWidgets size={"1.3rem"} />} title="Widgets" target="/widgets/controls" />
                <SidebarItem navSize={navSize} icon={<MdSecurity size={"1.3rem"} />} title="Authorizations" target="/authorize"/>
                <SidebarItem navSize={navSize} icon={<FaCog size={"1.3rem"} />} title="Settings" target="/settings"/>
                <SidebarLogout navSize={navSize} icon={<ImExit size={"1.3rem"} />} title="Logout" onClick={onOpen}/>
            </Flex>
            <Flex
                p={"5%"}
                flexDir="column"
                w={"100%"}
                alignItems={navSize == "small" ? "center" : "flex-start"}
                mb={4}
            >   
                <Flex w={"100%"} alignItems="center" mb={3}>
                    {colorIcons}
                    <Switch
                        ms={3}
                        id="dark_mode"
                        colorScheme="teal"
                        size="md"
                        onChange={toggleColorMode}
                    />
                </Flex>
                <Divider display={navSize == "small" ? "none" : "flex"}/>
                <Flex mt={4} align="center">
                    <Avatar icon={<FaUserCircle size={"sm"} />} />
                    <Flex
                        flexDir={"column"}
                        ml={4}
                        display={navSize == "small" ? "none" : "flex"}
                    >
                        <Heading size={"xl"}>{user ? user.userUsername : null}</Heading>
                    </Flex>
                </Flex>
            </Flex>
            <LogoutModal isOpen={isOpen} onClose={onClose} />
        </Flex>
    )
}