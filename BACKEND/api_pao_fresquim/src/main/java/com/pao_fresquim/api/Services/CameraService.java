package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.Repositories.CameraRepository;
import com.pao_fresquim.api.Repositories.VendaRepository;
import com.pao_fresquim.api.model.Camera;
import com.pao_fresquim.api.model.Cliente;
import com.pao_fresquim.api.model.Venda;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CameraService {

    private final CameraRepository repository;


    public CameraService(CameraRepository repository) {
        this.repository = repository;
    }

    public List<Camera> ListarCameras(){
        return repository.findAll();
    }

    public Optional<Camera> encontrarCamera(Long id){
        return repository.findById(id);
    }

    public Camera cadastrarCamera(Camera camera){
        return repository.save(camera);
    }






    // metodo visualizar ao vivo

    
}
