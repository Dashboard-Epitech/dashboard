import * as React from "react";
import { useState } from "react";
import { Menu, MenuButton, Button, MenuList, MenuItem, useDisclosure, Flex } from "@chakra-ui/react";
import { FaChevronDown, FaCog, FaGithub, FaLinkedin, FaTwitter, FaUser, FaUserCircle } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useGlobalState, setGlobalState } from "../../state";
import { ColorModeSwitcher } from "../../ColorModeSwitcher";
import LogoutModal from "../modals/LogoutModal";


export const Socials = () => {
    const [user, setUser] = useGlobalState("user");
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const menuStyle = {
        backgroundColor: 'transparent'
    }

    const triggerLogoutModal = () => {
        return(
            <LogoutModal />
        )
    }

    return (
        <Flex w={"10%"} justifyContent="space-around">
            <FaGithub size={"1.8rem"}/>
            <FaLinkedin size={"1.8rem"}/>
            <FaTwitter size={"1.8rem"}/>
        </Flex>
    )
}