package com.pao_fresquim.api.Repositories;

import com.pao_fresquim.api.model.Camera;
import com.pao_fresquim.api.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CameraRepository extends JpaRepository<Camera, Long> {
}
