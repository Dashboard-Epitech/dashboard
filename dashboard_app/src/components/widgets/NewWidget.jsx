import {
    Menu,
    MenuButton,
    MenuList,
    MenuItem,
    MenuItemOption,
    MenuGroup,
    MenuOptionGroup,
    MenuDivider,
    Button,
    Flex,
    Text,
} from '@chakra-ui/react'
import { FaChevronDown, FaDollarSign, FaRainbow, FaSpotify, FaYoutube } from 'react-icons/fa'
import { TiWeatherPartlySunny } from 'react-icons/ti'
import { Link, Outlet } from 'react-router-dom'

export const NewWidget = () => {
    return (
        <>
            <Flex mt={8} >
                <Menu>
                    <MenuButton as={Button} rightIcon={<FaChevronDown />}>
                        Widget Type
                    </MenuButton>
                    <MenuList>
                        <MenuItem as={Link} to="./weather">
                            <Flex alignItems={"center"}>
                                <TiWeatherPartlySunny size={"1.6rem"} />
                                <Text ms={3}>Weather</Text>
                            </Flex>
                        </MenuItem>
                        <MenuItem as={Link}>
                            <Flex alignItems={"center"}>
                                <FaDollarSign size={"1.6rem"} />
                                <Text ms={3}>Currency</Text>
                            </Flex>
                        </MenuItem>
                        <MenuItem as={Link}>
                            <Flex alignItems={"center"}>
                                <FaYoutube size={"1.6rem"} />
                                <Text ms={3}>Youtube</Text>
                            </Flex>
                        </MenuItem>
                        <MenuItem as={Link}>
                            <Flex alignItems={"center"}>
                                <FaSpotify size={"1.6rem"} />
                                <Text ms={3}>Spotify</Text>
                            </Flex>
                        </MenuItem>
                    </MenuList>
                </Menu>
            </Flex>
            <Outlet />
        </>

    )
}