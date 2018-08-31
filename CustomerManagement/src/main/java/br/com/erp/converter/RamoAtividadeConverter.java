package br.com.erp.converter;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import br.com.erp.model.RamoAtividade;

public class RamoAtividadeConverter implements Converter<Object> {

	private List<RamoAtividade> listaRamoAtividade;

	/**
	 * @param listaRamoAtividade
	 */
	public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividade) {
		this.listaRamoAtividade = listaRamoAtividade;
	}

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		if (value == null) {
			return null;
		}

		Long id = Long.valueOf(value);

		for (RamoAtividade ramoAtividade : listaRamoAtividade) {
			if (id.equals(ramoAtividade.getId())) {
				return ramoAtividade;
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object value) {
		if (value == null) {
			return null;
		}

		RamoAtividade ramoAtividade = (RamoAtividade) value;

		return ramoAtividade.getId().toString();
	}

}
