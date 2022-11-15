import { Flex } from "@chakra-ui/react";
import { DashboardGridItem } from "./DashboardGridItem";

export const DashboardsGrid = ({dashboards}) => {
    console.log(dashboards)

    const renderDashboardGrid = () => {
        let tmp = [];


        dashboards.forEach(e => {
            let widgetCount = 0;
            let widgetTypes = [];

            e.widgets.forEach(w => {
                widgetCount++;
            })

            tmp.push(
                <DashboardGridItem dashboardData={e} widgetCount={widgetCount} />
            )
        });

        return tmp;
    }

    return(
        <Flex w={"100%"}>
            {dashboards && renderDashboardGrid()}
        </Flex>
    )
}