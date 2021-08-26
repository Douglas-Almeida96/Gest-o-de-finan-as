package br.com.alura.forum.Controller;

import br.com.alura.forum.Controller.dto.DetalhesDoTopicoDto;
import br.com.alura.forum.Controller.dto.TopicoDTO;
import br.com.alura.forum.Controller.form.AtualiazacaoTopicoForm;
import br.com.alura.forum.Controller.form.TopicoForm;
import br.com.alura.forum.exception.TopicoException;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.CursoService;
import br.com.alura.forum.service.TopicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    TopicoService service;

    @Autowired
    CursoService cursoService;


    @GetMapping
    public Page<TopicoDTO> lista(@RequestParam(required = false) String nomeCurso,
        @PageableDefault(sort = "id",page = 0,size = 10,direction = Sort.Direction.DESC)  Pageable pageable) {
        Page<Topico> topicos = service.filter(nomeCurso, pageable);
        return TopicoDTO.coverter(topicos);
    }

    @PostMapping
    public ResponseEntity<TopicoDTO> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriComponentsBuilder) {
        Topico topico = form.converte(cursoService);
        service.save(topico);
        URI uri = uriComponentsBuilder.path("topico/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDTO(topico));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalhesDoTopicoDto> detalhar(@PathVariable("id") Long id) {
        try {
            Topico topico = service.findById(id);
            return ResponseEntity.ok(new DetalhesDoTopicoDto(topico));
        } catch (TopicoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoDTO> atualizar(@RequestBody @Valid AtualiazacaoTopicoForm form, @PathVariable Long id) {
        try {
            Topico topico = form.atualizar(id, service);
            return ResponseEntity.ok(new TopicoDTO(topico));
        } catch (TopicoException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

