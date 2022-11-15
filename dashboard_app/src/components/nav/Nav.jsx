import { Flex, Spacer } from '@chakra-ui/react';
import * as React from 'react'
import Brand from './Brand';
import { Socials } from './Socials';

export const Nav = () => {
    return (
        <Flex w={"100%"} justifyContent={"space-between"}>    
            <Brand />
            <Socials />
        </Flex>
    )
}