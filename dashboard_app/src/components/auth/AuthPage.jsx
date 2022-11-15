import {
    Button,
    Divider,
    FormControl,
    Heading,
    Input,
    Text,
    useColorModeValue,
    Flex,
    Switch,
    useColorMode,
} from '@chakra-ui/react'
import { useState } from 'react'
import { Outlet } from 'react-router-dom'
import { useGlobalState } from '../../state'
import { OAuthButtonGroup } from './OAuthButtonGroup'
import * as ajax from '../../lib/ajax'
import { FaMoon, FaSun } from 'react-icons/fa'

export const AuthPage = () => {
    const { toggleColorMode } = useColorMode();
    const formBackground = useColorModeValue("gray.100", "gray.700");
    const pageBackground = useColorModeValue("gray.300", "gray.500");
    const colorIcons = useColorModeValue(<FaSun />, <FaMoon />)

    return (
        <Flex h="100vh" alignItems="center" justifyContent="center" backgroundColor={pageBackground}>
            <Flex
                flexDirection="column"
                bg={formBackground}
                p={12}
                borderRadius={"20px"}
                boxShadow="lg"
            >
                <Outlet />
                <Divider />
                <Flex flexDirection={"column"} mb={8}>
                    <Flex p={3} mx="auto">
                        <Text fontSize={"sm"}>Or login with one of these ! </Text>
                    </Flex>
                    <OAuthButtonGroup />
                </Flex>
                <Flex w={"100%"} alignItems="center">
                    {colorIcons}
                    <Switch
                        ms={3}
                        id="dark_mode"
                        colorScheme="teal"
                        size="md"
                        onChange={toggleColorMode}
                    />
                </Flex>
            </Flex>
        </Flex>
    )

}
