package br.com.erp.repository;

import java.io.Serializable;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.erp.model.Empresa;

/**
 *
 * @author DanielFilho
 */
public class Empresas implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private EntityManager entityManager;

	public Empresas() {

	}

	public Empresas(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public Empresa porId(Long id) {
		return entityManager.find(Empresa.class, id);
	}

	public List<Empresa> pesquisar(String nome) {
		String jpql = "from Empresa where upper(razaoSocial) like upper(:razaoSocial) or upper(nomeFantasia) like upper(:razaoSocial)";
		TypedQuery<Empresa> query = entityManager.createQuery(jpql, Empresa.class);
		query.setParameter("razaoSocial", nome + "%");
		return query.getResultList();
	}

	public List<Empresa> todas() {
		return entityManager.createQuery("from Empresa", Empresa.class).getResultList();

	}

	public Empresa guardar(Empresa empresa) {
		return entityManager.merge(empresa);
	}

	public void remover(Empresa empresa) {
		empresa = porId(empresa.getId());
		entityManager.remove(empresa);
	}

}
