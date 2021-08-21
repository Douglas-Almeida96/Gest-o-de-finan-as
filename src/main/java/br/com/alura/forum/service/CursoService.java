package br.com.alura.forum.service;

import br.com.alura.forum.modelo.Curso;
import br.com.alura.forum.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CursoService {

    @Autowired
    CursoRepository repository;

    public Curso findByName(String nomeCurso){
      return repository.findByNome(nomeCurso);
    }
}
