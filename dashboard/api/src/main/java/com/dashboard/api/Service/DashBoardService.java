package com.dashboard.api.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.DashBoard;
import com.dashboard.api.Entity.DashboardUser;
import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Error.DashboardResponseError;
import com.dashboard.api.Repository.DashBoardRepository;
import com.dashboard.api.Repository.DashboardUserRepository;
import com.dashboard.api.Repository.WidgetRepository;
import com.dashboard.api.Request.DashBoardRequest;
import com.dashboard.api.Request.DashboardAddWidgetRequest;

@Service
public class DashBoardService {

    @Autowired
    DashBoardRepository dashBoardRepository;

    @Autowired
    DashboardUserRepository dashboardUserRepository;

    @Autowired
    WidgetRepository widgetRepository;

    public Object createDashBoard(DashBoardRequest request) throws Exception {
        DashBoard dashBoard = new DashBoard();

        Optional<DashboardUser> user = this.dashboardUserRepository.findById(request.getUser_id());
        if (!user.isPresent())
            throw new Exception("user " + request.getUser_id() + " not found");
        dashBoard.setUser(user.get());
        dashBoard.setName(request.getName());

        this.dashBoardRepository.save(dashBoard);

        return dashBoard;
    }

    public Object addWidget(DashboardAddWidgetRequest request) throws Exception {
        long dashBoardId = request.getDashboard_id();
        long widgetId = request.getWidget_id();

        Optional<DashBoard> optionalDashBoard = this.dashBoardRepository.findById(dashBoardId);
        Optional<Widget> optionalWidget = this.widgetRepository.findById(widgetId);

        Map<String, String> error = new HashMap<>();

        if (!optionalDashBoard.isPresent())
            error.put("dashboard_id", "dashboard " + dashBoardId + " not found.");
        if (!optionalWidget.isPresent())
            error.put("widget_id", "widget " + widgetId + " not found.");

        if (!error.isEmpty())
            throw new Exception(new DashboardResponseError(error).build());

        DashBoard dashBoard = optionalDashBoard.get();
        Widget widget = optionalWidget.get();

        if (widget.getDashBoard() != null) {
            throw new Exception("widget " + widgetId + " has already dashboard");
        }

        dashBoard.addWidget(widget);

        this.dashBoardRepository.save(dashBoard);

        return dashBoard;
    }

    public Object update(long id, DashBoardRequest request) throws Exception {
        Optional<DashBoard> optional = this.dashBoardRepository.findById(id);

        if (!optional.isPresent())
            throw new Exception("dashboard " + id + " not found");

        DashBoard dashBoard = optional.get();

        dashBoard.setName(request.getName());

        this.dashBoardRepository.save(dashBoard);

        return dashBoard;
    }

    public Object remove(long id) throws Exception {
        Optional<DashBoard> optional = this.dashBoardRepository.findById(id);

        if (!optional.isPresent())
            throw new Exception(id + " not found");

        DashBoard dashBoard = optional.get();

        this.dashBoardRepository.delete(dashBoard);

        return dashBoard;
    }
}
