import { Button, VisuallyHidden } from "@chakra-ui/react"
import { FaGithub } from "react-icons/fa"
import * as ajax from "../../../lib/ajax";

export const GitHubOauth2 = () => {
    const getGithubLogin = () => {
        ajax.authGithub()
            .then((response) => {
                window.location.replace(response.data)
            })
    }
    return (
        <Button key="github" width="full" onClick={() => getGithubLogin()}>
            <VisuallyHidden>Sign in with Github</VisuallyHidden>
            <FaGithub />
        </Button>
    )
}