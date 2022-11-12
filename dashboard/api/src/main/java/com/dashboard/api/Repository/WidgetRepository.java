package com.dashboard.api.Repository;

import org.springframework.data.repository.CrudRepository;
import com.dashboard.api.Entity.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Long> {

}
