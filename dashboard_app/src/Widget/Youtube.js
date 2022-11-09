import { Box, Center, Image, Text } from "@chakra-ui/react";
import React from "react";

const Youtube = ({ size, ...props }) => {
    switch (size) {
        case 1: return defaultSize();
    }

    function defaultSize() {
        return (
            <Box
                 width="500px"
                 height="100px"
                 border="2px solid red"
                 borderRadius='10px'
                 position="relative"
                 backgroundColor="rgba(255, 120, 120, .5)"
             >
                <Center width="100px" height="100px" position="absolute">
                    <Image 
                        src={props.channel.logo}
                        boxSize="60px"
                        rounded="30px"
                    />
                </Center>

                <Box width="150px" height="100px" marginX="110px" position="absolute">
                        <Text fontFamily="revert" fontSize="17px" margin="4" marginTop="15" noOfLines={1}>
                            {props.channel.title}
                        </Text>
                        <Text fontFamily="revert" color="gray" fontSize="12px" margin="4" noOfLines={1}>
                            {props.channel.abonne} abonnés
                        </Text>
                </Box>


                <Box width="190px" height="100px" marginX="260px" position="absolute">
                    <Center
                        height="33px"
                        position="absolute"
                        fontSize="14px"
                    >
                        nombre de vues: {props.channel.viewCount}
                    </Center>
                    <Center
                        height="33px"
                        position="absolute"
                        fontSize="14px"
                        marginY="33px"
                    >
                        nombre de videos: {props.channel.videoCount}
                    </Center>
                    <Center
                        height="33px"
                        position="absolute"
                        marginY="66px"
                    >
                        
                    </Center>
                </Box>
             </Box>
        )
    }
}

export default Youtube