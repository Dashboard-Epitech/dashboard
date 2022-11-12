package com.dashboard.api.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Repository.WidgetRepository;
import com.dashboard.api.Request.WidgetRequest;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @SuppressWarnings("unchecked")
    protected <T extends Widget> T getInstanceOf(Class<T> clazz, long id)
            throws Exception {
        Optional<Widget> temp = widgetRepository.findById(id);

        if (!temp.isPresent())
            throw new Exception(id + " no't found");

        Widget temp2 = temp.get();

        if (!temp2.getClass().equals(clazz))
            throw new Exception(id + " is not a " + clazz.getName());

        return (T) temp2;
    }

    public Object createWidget() {
        return null;
    }

    public <W extends WidgetRequest> Object updateWidget(long id, W request) throws Exception {
        return null;
    }

    public Object updateData(long id) throws Exception {
        return null;
    }

    public Object remove(long id) throws Exception {
        Optional<Widget> optional = this.widgetRepository.findById(id);

        if (!optional.isPresent())
            throw new Exception(id + " not found");

        Widget widget = optional.get();

        this.widgetRepository.delete(widget);

        return widget;
    }
}
