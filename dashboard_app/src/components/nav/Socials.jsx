import { Flex } from "@chakra-ui/react";
import * as React from "react";
import { FaGithub, FaLinkedin, FaTwitter, FaUser, FaUserCircle } from "react-icons/fa";


export const Socials = () => {
    return (
        <Flex w={"10%"} justifyContent="space-around">
            <FaGithub size={"1.8rem"}/>
            <FaLinkedin size={"1.8rem"}/>
            <FaTwitter size={"1.8rem"}/>
        </Flex>
    )
}