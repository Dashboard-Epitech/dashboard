package com.dashboard.api.Entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class DashBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToMany(mappedBy = "dashBoard")
    private List<Widget> widgets = new ArrayList<>();

    public boolean addWidget(Widget widget) {
        return this.widgets.add(widget);
    }

    public boolean removeWidget(Widget widget) {
        return this.widgets.remove(widget);
    }
}