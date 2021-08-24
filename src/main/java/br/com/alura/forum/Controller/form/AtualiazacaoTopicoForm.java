package br.com.alura.forum.Controller.form;

import br.com.alura.forum.exception.TopicoException;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.service.TopicoService;
import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.Length;

import javax.transaction.Transactional;
import javax.validation.constraints.NotEmpty;

public class AtualiazacaoTopicoForm {

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String titulo;

    @NotNull
    @NotEmpty
    @Length(min = 10)
    private String mensagem;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    @Transactional
    public Topico atualizar(Long id, TopicoService service) throws TopicoException {
        Topico topico = service.findById(id);

        topico.setTitulo(this.titulo);
        topico.setMensagem(this.mensagem);

        topico = service.update(topico);

        return topico;
    }
}
