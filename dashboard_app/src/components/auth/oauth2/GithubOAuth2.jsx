import { Button, Link, VisuallyHidden } from "@chakra-ui/react"
import { FaGithub } from "react-icons/fa"
import * as ajax from "../../../lib/ajax";

export const GitHubOauth2 = () => {

    return (
        <Button as={Link} key="github" width="full" href="/auth/login/github">
            <VisuallyHidden>Sign in with Github</VisuallyHidden>
            <FaGithub />
        </Button>
    )
}