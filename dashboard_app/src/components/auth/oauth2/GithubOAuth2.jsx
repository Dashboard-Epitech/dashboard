import { Button, Link, VisuallyHidden } from "@chakra-ui/react"
import { FaGithub } from "react-icons/fa"
import * as ajax from "../../../lib/ajax";

export const GitHubOauth2 = () => {

    return (
        <Button as={Link} key="github" width="full" href="http://localhost:8080/api/oauth2/authorize/github?redirect_uri=http://localhost:3000/oauth2/redirect">
            <VisuallyHidden>Sign in with Github</VisuallyHidden>
            <FaGithub />
        </Button>
    )
}