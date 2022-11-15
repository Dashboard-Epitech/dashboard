import {
    Alert,
    AlertIcon,
    AlertTitle,
    AlertDescription,
    Box,
    Button,
    Checkbox,
    Container,
    Divider,
    FormControl,
    FormLabel,
    Heading,
    HStack,
    Input,
    Stack,
    Text,
    useBreakpointValue,
    useColorModeValue,
    Flex,
    Switch,
    useColorMode,
    Icon,
} from '@chakra-ui/react'
import { useState } from 'react'
import { Link, Navigate, Outlet, useNavigate } from 'react-router-dom'
import { useGlobalState } from '../../../state'
import { AlertError } from '../../alerts/AlertError'
import { AlertWarning } from '../../alerts/AlertWarning'
import { FaMoon, FaSun } from 'react-icons/fa';
import * as ajax from '../../../lib/ajax'

export const LoginForm = () => {
    const { toggleColorMode } = useColorMode();
    const navigate = useNavigate();
    const [accessToken, setAccessToken] = useGlobalState("ACCESS_TOKEN");
    const [errors, setErrors] = useState(null);
    const [userEmail, setUserEmail] = useState();
    const [userPassword, setUserPassword] = useState();
    const [isLoading, setIsLoading] = useState(false);

    const setEmail = (e) => {
        setUserEmail(e.target.value)
    }

    const setPassword = (e) => {
        setUserPassword(e.target.value)
    }

    const handleLogin = (e) => {
        e.preventDefault();
        setIsLoading(true);

        ajax.authLogin(userEmail, userPassword)
            .then((response) => {
                setAccessToken(response.data.token);
                localStorage.setItem("ACCESS_TOKEN", response.data.token)
                navigate("/dashboard")
            })
            .catch((error) => {
                console.log(error)
                setErrors(error.response.data)
            })
    }

    const renderAlerts = (errors) => {
        if (!errors) return null;

        let tmp = [];
        switch (errors.errorType) {
            case "uniqueUsernameError":
                tmp.push(<AlertError alertContent={errors.errorContents} />)
                break;
            case "badUserFormError":
                for (const [k, v] of Object.entries(errors.errorContents)) {
                    tmp.push(<AlertError alertContent={v} />);
                }
                break;
            case "userNotVerifiedError":
                tmp.push(<AlertWarning alertContent={errors.errorContents} />)
                break;
            case "badCredentialsError":
                tmp.push(<AlertError alertContent={errors.errorContents} />)
                break;
            default:
                break;
        }

        return tmp;
    }

    return (
        <>
            <form onSubmit={(e) => handleLogin(e)}>
                <Heading mb={6} textAlign={"center"}>Log In</Heading>
                <FormControl p={3}>
                    <Input
                        placeholder="johndoe@gmail.com"
                        type="text"
                        variant="filled"
                        mb={3}
                        onChange={(e) => setEmail(e)}
                    />
                </FormControl>
                <FormControl p={3}>
                    <Input
                        placeholder="**********"
                        type="password"
                        variant="filled"
                        mb={6}
                        onChange={(e) => setPassword(e)}
                    />
                </FormControl>
                <Flex>
                    <Button type="submit" colorScheme={"whatsapp"} mb={8} w="200px" mx={"auto"}>
                        Log In
                    </Button>
                </Flex>
                <Flex flexDirection={"column"}>
                    {renderAlerts(errors)}
                </Flex>
                <Outlet />
                <Flex p={3} mx="auto">
                    <Text fontWeight={"bold"}>No account yet ? Register {' '} <Text as={Link} to={"../register"} textDecoration="underline">here</Text> ! </Text>
                </Flex>
            </form>
        </>
    )

}
