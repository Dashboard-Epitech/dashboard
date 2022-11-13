import { Button, ButtonGroup, VisuallyHidden } from '@chakra-ui/react'
import { FaGithub, FaGoogle, FaMicrosoft, FaTwitter } from 'react-icons/fa';
import { GitHubOauth2 } from './oauth2/GithubOAuth2';

export const OAuthButtonGroup = () => {
    return (
        <>
            <ButtonGroup variant="outline" spacing="4" width="full">
                <Button key="google" width="full">
                    <VisuallyHidden>Sign in with Google</VisuallyHidden>
                    <FaGoogle />
                </Button>
                <Button key="microsoft" width="full">
                    <VisuallyHidden>Sign in with Microsoft</VisuallyHidden>
                    <FaMicrosoft />
                </Button>
                <GitHubOauth2 />
            </ButtonGroup>
        </>

    )
}



