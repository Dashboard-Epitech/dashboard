import {   
    Alert,
    AlertIcon,
    AlertTitle,
    AlertDescription, 
} from "@chakra-ui/react";

export const AlertSuccess = ({ ...props }) => {
    const alertTitle = props.alertTitle;
    const alertContent = props.alertContent;

    return (
        <Alert status='success' rounded={"md"} mb={3}>
            <AlertIcon />
            <AlertDescription>{alertContent}</AlertDescription>
        </Alert>
    )
}