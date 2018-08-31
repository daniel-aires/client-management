package br.com.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import br.com.erp.converter.RamoAtividadeConverter;
import br.com.erp.model.Empresa;
import br.com.erp.model.RamoAtividade;
import br.com.erp.model.TipoCadastro;
import br.com.erp.model.TipoEmpresa;
import br.com.erp.repository.Empresas;
import br.com.erp.repository.RamoAtividades;
import br.com.erp.service.CadastroEmpresaService;
import br.com.erp.util.jsf.FacesMessages;

@Named
@SessionScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private Empresas empresas;

	@Inject
	private CadastroEmpresaService cadastroEmpresaService;

	@Inject
	private RamoAtividades ramoAtividades;

	@Inject
	private FacesMessages messages;

	private List<Empresa> listaEmpresas;

	private String termoPesquisa;

	private Empresa empresa;

	private Converter ramoAtividadeConverter;

	public void prepararNovaEmpresa() {
		this.empresa = new Empresa();
		this.empresa.setTipoCadastro(TipoCadastro.FISICA);
	}

	public void prepararEdicao() {
		ramoAtividadeConverter = new RamoAtividadeConverter(Arrays.asList(empresa.getRamoAtividade()));
	}

	public void salvar() {
		cadastroEmpresaService.salvar(empresa);
		atualizarRegistros();
		messages.info("Empresa salva com sucesso!");
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:empresaDataTable", "frm:mensagem"));
	}

	public void excluir() {
		cadastroEmpresaService.excluir(empresa);
		empresa = null;

		atualizarRegistros();
		messages.info("Empresa excluida com sucesso!");

	}

	private void atualizarRegistros() {
		if (jaHouvePesquisa()) {
			pesquisar();
		} else {
			todasEmpresas();
		}

	}

	private boolean jaHouvePesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}

	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);

		if (listaEmpresas.isEmpty()) {
			messages.info("Sua pesquisa não retornou registros");
		}

	}

	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}

	public List<RamoAtividade> completarRamoAtividade(String termo) {

		List<RamoAtividade> atividades = ramoAtividades.pesquisar(termo);

		ramoAtividadeConverter = new RamoAtividadeConverter(atividades);

		return atividades;
	}

	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}

	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}

	public TipoCadastro[] getTiposCadastro() {
		return TipoCadastro.values();
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}

	public boolean isEmpresaSelecionada() {
		return empresa != null && empresa.getId() != null;
	}
}
