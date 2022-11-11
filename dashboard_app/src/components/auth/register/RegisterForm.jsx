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
import { Form, Link } from 'react-router-dom'
import { useGlobalState } from '../../../state'
import { AlertError } from '../../alerts/AlertError'
import { AlertWarning } from '../../alerts/AlertWarning'
import { FaMoon, FaSun } from 'react-icons/fa';
import * as ajax from '../../../lib/ajax'
import { AlertSuccess } from '../../alerts/AlertSuccess'

export const RegisterForm = () => {
    const { toggleColorMode } = useColorMode();
    const [user, setUser] = useGlobalState("user");
    const [errors, setErrors] = useState(null);
    const [userEmail, setUserEmail] = useState('');
    const [userPassword, setUserPassword] = useState('');
    const [username, setUsername] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const [isRegisterSuccess, setIsRegisterSuccess] = useState(false);

    const setUsernameVal = (e) => {
        setUsername(e.target.value)
    }

    const setEmailVal = (e) => {
        setUserEmail(e.target.value)
    }

    const setPasswordVal = (e) => {
        setUserPassword(e.target.value)
    }

    const handleRegisterSubmit = (e) => {
        e.preventDefault();
        setIsLoading(true);

        ajax.authRegister(username, userEmail, userPassword)
            .then((response) => {
                console.log(response);
                setErrors(null);
                setIsRegisterSuccess(true)
            })
            .catch((error) => {
                console.log(error);
                let errorBody = error.response.data;

                setErrors(errorBody);
            })
    }

    const renderAlerts = () => {
        let tmp = [];
        if (errors) {
            if (errors.errorType == "uniqueUsernameError") {
                tmp.push(<AlertError alertContent={errors.errorContents} />)
            } else if (errors.errorType == "badUserFormError") {
                for (const [k, v] of Object.entries(errors.errorContents)) {
                    tmp.push(<AlertError alertContent={v} />);
                }
            }
        } else if (isRegisterSuccess) {
            tmp.push(<AlertSuccess alertContent="Registered successfully. Please check your mailbox for your verification link."/>)
        }

        console.log(tmp)
        return tmp;
    }

return (
    <>
        <form onSubmit={(e) => handleRegisterSubmit(e)}>
            <Heading mb={6} textAlign={"center"}>Register</Heading>
            <FormControl p={3}>
                <FormLabel>Username</FormLabel>
                <Input
                    placeholder="johndoe@gmail.com"
                    type="text"
                    variant="filled"
                    mb={3}
                    onChange={(e) => setUsernameVal(e)}
                />
            </FormControl>
            <FormControl p={3}>
                <FormLabel>Email</FormLabel>
                <Input
                    placeholder="johndoe@gmail.com"
                    type="email"
                    variant="filled"
                    mb={3}
                    onChange={(e) => setEmailVal(e)}
                />
            </FormControl>
            <FormControl p={3}>
                <FormLabel>Password</FormLabel>

                <Input
                    placeholder="**********"
                    type="password"
                    variant="filled"
                    mb={6}
                    onChange={(e) => setPasswordVal(e)}
                />
            </FormControl>
            <Flex>
                <Button type={"submit"} colorScheme={"whatsapp"} mb={8} w="200px" mx={"auto"}>
                    Register
                </Button>
            </Flex>
            <Flex flexDirection={"column"}>
                {renderAlerts(errors)}
            </Flex>
        </form>
    </>
)

}
