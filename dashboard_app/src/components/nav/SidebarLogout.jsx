import { Flex, Icon, Menu, MenuButton, Text } from "@chakra-ui/react"
import { Link } from "react-router-dom"

export const SidebarLogout = ({ navSize, title, icon, onClick }) => {
    return (
        <Flex
            mt={30}
            flexDir="column"
            w={"100%"}
            alignItems={navSize == "small" ? "center" : "flex-start"}
            onClick={onClick}
        >
            <Menu placement="right">
                    <MenuButton>
                        <Flex alignItems={"center"} p={3} w={navSize == "large" && "100%"}>
                            {icon}
                            <Text display={navSize == "small" ? "none" : "flex"} ms={2} mt={1} fontWeight={"bold"}>{title}</Text>
                        </Flex>
                    </MenuButton>
            </Menu>
        </Flex>
    )
}