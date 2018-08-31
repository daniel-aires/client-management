package br.com.erp.model;

/**
 *
 * @author DanielFilho
 */

public enum TipoEmpresa {

	MEI("Microempreendedor Individual"), EIRELI("Empresa Individual de Responsabilidade Limitada"), LTDA(
			"Sociedade Limitada"), SA("Sociedade Anônima");

	private String descricao;

	TipoEmpresa(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
