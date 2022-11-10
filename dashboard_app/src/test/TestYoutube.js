import Youtube from "../Widget/Youtube";

const TestYoutube = () => {
    const channel = {
        title: "SQEEZIE",
        logo: "https://yt3.ggpht.com/ytc/AMLnZu8dvxf4ywWYTa37GHrWwfyWipBHW-8sVeZ7PLrkSw=s88-c-k-c0x00ffffff-no-rj",
        viewCount: "9464944996",
        subscriberCount: "17,5M",
        videoCount: "1485"
    }

    return (
        <Youtube size={1} channel={channel}/>
    )
}

export default TestYoutube