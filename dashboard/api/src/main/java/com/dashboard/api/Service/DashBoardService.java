package com.dashboard.api.Service;

import java.util.Optional;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.DashBoard;
import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Repository.DashBoardRepository;
import com.dashboard.api.Repository.DashboardUserRepository;
import com.dashboard.api.Repository.WidgetRepository;

@Service
public class DashBoardService {

    @Autowired
    DashBoardRepository dashBoardRepository;

    @Autowired
    DashboardUserRepository dashboardUserRepository;

    @Autowired
    WidgetRepository widgetRepository;

    public Object createDashBoard(String body) throws Exception {
        DashBoard dashBoard = new DashBoard();
        long idUser = new JSONObject(body).getLong("user_id");
        Optional<DashboardUser> user = this.dashboardUserRepository.findById(idUser);
        if (!user.isPresent())
            throw new Exception("user " + idUser + " not found");
        dashBoard.setUser(user.get());

        this.dashBoardRepository.save(dashBoard);

        return dashBoard;
    }

    public Object addWidget(String body) throws Exception {
        JSONObject input = new JSONObject(body);
        int dashBoardId = input.getInt("dashboard_id");
        int widgetId = input.getInt("widget_id");

        Optional<DashBoard> optionalDashBoard = this.dashBoardRepository.findById(dashBoardId);
        Optional<Widget> optionalWidget = this.widgetRepository.findById(widgetId);

        if (!optionalDashBoard.isPresent())
            throw new Exception("dashboard " + dashBoardId + " not found.");
        if (!optionalWidget.isPresent())
            throw new Exception("widget " + widgetId + " not found.");

        DashBoard dashBoard = optionalDashBoard.get();
        Widget widget = optionalWidget.get();

        if (widget.getDashBoard() != null) {
            throw new Exception("widget " + widgetId + " have already dashboard");
        }

        dashBoard.addWidget(widget);

        this.dashBoardRepository.save(dashBoard);

        return dashBoard;
    }
}
