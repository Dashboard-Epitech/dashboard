import { Center, Divider, Flex, Grid, GridItem, Spacer, Text } from "@chakra-ui/react"


export const SmallCurrency = ({from, to, mid}) => {

    return (
        <Center 
            w={"60%"} 
            h="150px" 
            backgroundPosition="center" 
            backgroundSize="cover" 
            backGroundColor={"gray"} 
            my={6} 
            p={6} 
            borderRadius="20px"
            border="1px solid red"
            >
                <Spacer/>
                <Text>1 {from}</Text>
                <Spacer/>
                <Text>{" = "}</Text>
                <Spacer/>
                <Text>{mid} {to}</Text>
                <Spacer/>
        </Center>
    )
}