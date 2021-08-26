package br.com.alura.forum.service;

import br.com.alura.forum.exception.TopicoException;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class TopicoService {

    @Autowired
    TopicoRepository repository;

    public Page<Topico> findAll(Pageable pageable) {
        Page<Topico> topicos = repository.findAll(pageable);
        return topicos;
    }

    public Page<Topico> filter(String nomeCurso,Pageable pageable) {

        Page<Topico> topicos;
        if (nomeCurso == null) {
            return topicos = findAll(pageable);
        } else {
            return topicos = repository.findByCurso_Nome(nomeCurso, pageable);
        }
    }


    @Transactional
    public void save(Topico topico) {
        repository.save(topico);
    }


    public Topico findById(Long id) throws TopicoException {
        Optional<Topico> obj = repository.findById(id);
        if (obj.isPresent()) {
            return obj.get();
        }
        throw new TopicoException("Topico não encontrado!");
    }

    @Transactional
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public Topico update(Topico topico) {
        return repository.save(topico);
    }
}
