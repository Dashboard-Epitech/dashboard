package com.dashboard.api.Repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.dashboard.api.Entity.DashBoard;

public interface DashBoardRepository extends CrudRepository<DashBoard, Long> {
}
