package com.algaworks.financeiro.controller;

import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import com.algaworks.financeiro.model.Lancamento;
import com.algaworks.financeiro.model.Pessoa;
import com.algaworks.financeiro.model.TipoLancamento;
import com.algaworks.financeiro.repository.Pessoas;
import com.algaworks.financeiro.service.CadastroLancamentos;
import com.algaworks.financeiro.service.NegocioException;

@Named
@javax.faces.view.ViewScoped
public class CadastroLancamentoBean implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Inject
	private CadastroLancamentos cadastro;
	
	@Inject
	private Pessoas pessoas;
	
	private Lancamento lancamento = new Lancamento();
	private List<Pessoa> todasPessoas;
	
	public void prepararCadastro() {
		this.todasPessoas = this.pessoas.todas();
	}
	public void salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		
		try {
			this.cadastro.salvar(this.lancamento);
			this.lancamento = new Lancamento();
			context.addMessage(null, new FacesMessage(
					"Lançamento salvo com sucesso!"));
		} catch (NegocioException e) {
			
			FacesMessage mensagem = new FacesMessage(e.getMessage());
			mensagem.setSeverity(FacesMessage.SEVERITY_ERROR);
			context.addMessage(null, mensagem);
		}
	}
	
	public List<Pessoa> getTodasPessoas() {
		return this.todasPessoas;
	}
	
	public TipoLancamento[] getTiposLancamentos() {
		return TipoLancamento.values();
	}
	
	public Lancamento getLancamento() {
		return lancamento;
	}
	
	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public void descricaoModificada(ValueChangeEvent event) {
		System.out.println("Valor antigo: " + event.getOldValue());
		System.out.println("Novo valor: " + event.getNewValue());
	}
	
}