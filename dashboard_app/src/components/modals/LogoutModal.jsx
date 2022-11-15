import React from 'react';
import {
    Modal,
    ModalOverlay,
    ModalContent,
    ModalHeader,
    ModalCloseButton,
    ModalBody,
    ModalFooter,
    Heading,
    Button,
    useDisclosure,
    Text
} from '@chakra-ui/react';
import { Navigate } from 'react-router';
import { useGlobalState } from '../../state';

const LogoutModal = ({isOpen, onClose}) => {
    const [user, setUser] = useGlobalState('USER');

    const handleLogout = (e) => {
        e.preventDefault();
        localStorage.clear();
        setUser(null);
        onClose();
        return <Navigate to="/auth/login" />
    }

    return (
        <Modal closeOnOverlayClick={false} isOpen={isOpen} onClose={onClose} size={"3xl"} isCentered>
            <ModalOverlay />
                <ModalContent>
                    <ModalHeader>
                        <Heading>Logout</Heading>
                    </ModalHeader>
                    <ModalBody p={4}>
                        <Text>Do you wish to logout ?</Text>
                    </ModalBody>

                    <ModalFooter>
                        <Button colorScheme='blue' mr={3} onClick={(e) => {handleLogout(e)}}>
                            Logout
                        </Button>
                        <Button colorScheme='blue' mr={3} onClick={onClose}>
                            Cancel
                        </Button>
                    </ModalFooter>
                </ModalContent>
        </Modal>
    )
}

export default LogoutModal;