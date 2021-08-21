package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TopicoService{

    @Autowired
    TopicoRepository repository;

    public List<Topico> findAll(){
        List<Topico> list=  repository.findAll();
        return list;
    }

    public List<Topico> filter(String nomeCurso) {
        List<Topico> list;
        if(nomeCurso == null){
            list = findAll();
        }else{
          list= repository.findByCurso_Nome(nomeCurso);
        }
        return list;
    }

    public void save(Topico topico) {
        repository.save(topico);
    }


    public Topico findById(Long id) {
        Optional<Topico> obj = repository.findById(id);
        return obj.orElseThrow(RuntimeException::new);
    }
}
