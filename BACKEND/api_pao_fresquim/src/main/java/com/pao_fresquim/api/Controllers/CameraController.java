package com.pao_fresquim.api.Controllers;

import com.pao_fresquim.api.Services.CameraService;
import com.pao_fresquim.api.model.Camera;
import com.pao_fresquim.api.model.Cliente;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cameras")
public class CameraController {

    private final CameraService service;

    public CameraController (CameraService service){
        this.service = service;
    }

    @PostMapping
    public Camera cadastrarCamera(@RequestBody Camera camera){
        return service.cadastrarCamera(camera);
    }

    @GetMapping("{id}")
    public ResponseEntity<Camera> buscarCameraPorId(@PathVariable Long id){
        return service.encontrarCamera(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Camera> listarCameras(){
        return service.ListarCameras();
    }

}
