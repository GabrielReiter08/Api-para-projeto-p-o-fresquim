package com.pao_fresquim.api.Services;

import com.pao_fresquim.api.Repositories.ClienteRepository;
import com.pao_fresquim.api.Repositories.FuncionarioRepository;
import com.pao_fresquim.api.model.Cliente;
import com.pao_fresquim.api.model.Funcionario;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository){
        this.repository = repository;
    }

    public List<Funcionario> listarFuncionarios(){
        return repository.findAll();
    }

    public Optional<Funcionario> buscarPorId(Long id){
        return repository.findById(id);
    }


    public Funcionario cadastrarFuncionario( Funcionario funcionario){
        return repository.save(funcionario);
    }


    // metodo registrar ponto


    // metodo anexar atestado

    public Funcionario anexarAtestado(Long id, MultipartFile atestado) throws IOException {

        Funcionario f = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));


        if (atestado != null && !atestado.isEmpty()) {
            f.setAtestadoMedico(atestado.getBytes());
            f.setNomeArquivo(atestado.getOriginalFilename());
            f.setTipoArquivo(atestado.getContentType());
        }


        return repository.save(f);
    }



    public boolean excluirFuncionario(Long id){
        if(repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }

        return false;
    }

    // metodo p editar funcionário

    public Funcionario editarFuncionario(Long id, Funcionario funcionario){
        Funcionario funcionarioExiste = repository.findById(id).orElseThrow(()-> new RuntimeException("Funcionário não encontrado"));

        funcionarioExiste.setNome(funcionario.getNome());
        funcionarioExiste.setTelefone(funcionario.getTelefone());
        funcionarioExiste.setEndereco(funcionario.getEndereco());
        funcionarioExiste.setContato_emergencia(funcionario.getContato_emergencia());
        funcionarioExiste.setLicensas(funcionario.getLicensas());

        // Por lógica não coloquei como editar data de admissão

        return repository.save(funcionarioExiste);
    }

}
