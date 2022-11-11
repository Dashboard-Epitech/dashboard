import * as React from "react";
import { useState } from "react";
import { Menu, MenuButton, Button, MenuList, MenuItem, useDisclosure } from "@chakra-ui/react";
import { FaChevronDown, FaCog, FaUser, FaUserCircle } from "react-icons/fa";
import { Link } from "react-router-dom";
import { useGlobalState, setGlobalState } from "../../state";
import { ColorModeSwitcher } from "../../ColorModeSwitcher";


const NavMenu = () => {
    const [user, setUser] = useGlobalState("user");
    const { isOpen, onOpen, onClose } = useDisclosure();
    const [isLoggedIn, setIsLoggedIn] = useState(false);
    const menuStyle = {
        backgroundColor: 'transparent'
    }

    return (
        <Menu>
            <MenuButton as={Button} leftIcon={<FaUserCircle size={"1.5rem"}/>} rightIcon={<FaChevronDown />} style={menuStyle}>
                {user.username}
            </MenuButton>
            <MenuList>
                {!user &&
                    <>
                        <MenuItem as={Link} to="/auth/login">
                            Login
                        </MenuItem>
                        <MenuItem>
                            Register
                        </MenuItem>
                    </>
                }
                {user &&
                    <>
                        <MenuItem as={Link} to="dashboard/logout">
                            Logout
                        </MenuItem>
                        <MenuItem as={Button} leftIcon={<FaCog />} style={menuStyle}>
                            Settings
                        </MenuItem>
                    </>
                }
            </MenuList>
        </Menu >
    )
}

export default NavMenu;