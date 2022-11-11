package com.dashboard.api.Service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dashboard.api.Entity.Widget;
import com.dashboard.api.Repository.WidgetRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public abstract class WidgetService {

    @Autowired
    WidgetRepository widgetRepository;

    @SuppressWarnings("unchecked")
    protected <T extends Widget> T getInstanceOf(Class<T> clazz, int id)
            throws Exception {
        Optional<Widget> temp = widgetRepository.findById(id);

        if (!temp.isPresent())
            throw new Exception(id + " no't found");

        Widget temp2 = temp.get();

        if (!temp2.getClass().equals(clazz))
            throw new Exception(id + " is not a " + clazz.getName());

        return (T) temp2;
    }

    public Object createWidget(String body) {
        return null;
    }

    public Object updateWidget(int id, String body) throws Exception {
        return null;
    }

    public Object updateData(int id) throws Exception {
        return null;
    }
}
