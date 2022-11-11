import {   
    Alert,
    AlertIcon,
    AlertTitle,
    AlertDescription, 
} from "@chakra-ui/react";

export const AlertWarning = ({ ...props }) => {
    const alertTitle = props.alertTitle;
    const alertContent = props.alertContent;

    return (
        <Alert status='warning' rounded={"md"} mb={3}>
            <AlertIcon />
            <AlertDescription>{alertContent}</AlertDescription>
        </Alert>
    )
}