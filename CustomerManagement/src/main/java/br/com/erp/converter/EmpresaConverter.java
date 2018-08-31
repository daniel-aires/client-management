package br.com.erp.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.lang3.StringUtils;

import br.com.erp.model.Empresa;
import br.com.erp.repository.Empresas;

@FacesConverter(forClass = Empresa.class)
public class EmpresaConverter implements Converter<Object> {

	private Empresas empresas;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		Empresa retorno = null;

		if (StringUtils.isNotEmpty(value)) {
			retorno = this.empresas.porId(new Long(value));
		}

		return retorno;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value != null) {
			Empresa entidade = (Empresa) value;
			return entidade != null & entidade.getId() != null ? entidade.getId().toString() : null;
		}
		return "";
	}

}
