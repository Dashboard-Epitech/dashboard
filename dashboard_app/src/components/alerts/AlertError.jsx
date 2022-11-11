import {   
    Alert,
    AlertIcon,
    AlertTitle,
    AlertDescription, 
} from "@chakra-ui/react";

export const AlertError = ({ ...props }) => {
    const alertContent = props.alertContent;

    return (
        <Alert status='error' rounded={"md"} mb={3}>
            <AlertIcon />
            <AlertDescription>{alertContent}</AlertDescription>
        </Alert>
    )
}