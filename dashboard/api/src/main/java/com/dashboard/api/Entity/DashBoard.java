package com.dashboard.api.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DashBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private DashboardUser user;

    @OneToMany(mappedBy = "dashBoard", cascade = CascadeType.REMOVE)
    private List<Widget> widgets = new ArrayList<>();

    private String name;

    public boolean addWidget(Widget widget) {
        widget.setDashBoard(this);
        return this.widgets.add(widget);
    }

    public boolean removeWidget(Widget widget) {
        return this.widgets.remove(widget);
    }
}
