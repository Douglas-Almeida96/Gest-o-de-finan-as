package br.com.alura.forum.Controller;

import br.com.alura.forum.Controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.Controller.dto.TopicoDTO;
import br.com.alura.forum.Controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.CursoService;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoService service;

    @Autowired
    CursoService cursoService;


    @GetMapping
    public List<TopicoDTO> lista(String nomeCurso){
        List<Topico> list;
        list = service.filter(nomeCurso);
        return TopicoDTO.coverter(list);
    }

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder){
        Topico topico = form.converte(cursoService);
        service.save(topico);
        URI uri = uriComponentsBuilder.path("topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public DetalhesDoTopicoDto detalhar(@PathVariable("id") Long id){
        Topico topico = service.findById(id);
        return  new DetalhesDoTopicoDto(topico);
    }


}

