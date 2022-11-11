import { Button, ButtonGroup, VisuallyHidden } from '@chakra-ui/react'
import { FaGithub, FaGoogle, FaMicrosoft, FaTwitter } from 'react-icons/fa';

const providers = [
    { name: 'Google', icon: <FaGoogle /> },
    { name: 'Microsoft', icon: <FaMicrosoft /> },
    { name: 'GitHub', icon: <FaGithub /> },
]

export const OAuthButtonGroup = () => {
    return (
        <ButtonGroup variant="outline" spacing="4" width="full">
            {providers.map(({ name, icon }) => (
                <Button key={name} width="full">
                    <VisuallyHidden>Sign in with {name}</VisuallyHidden>
                    {icon}
                </Button>
            ))}
        </ButtonGroup>
    )
}



