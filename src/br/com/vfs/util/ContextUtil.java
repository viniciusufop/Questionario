package br.com.vfs.util;

import javax.faces.application.Application;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import br.com.vfs.bean.AplicacaoBean;
import br.com.vfs.bean.ContextoBean;

public final class ContextUtil {
	
	@ManagedProperty(value="#{aplicacaoBean}")
	private static AplicacaoBean aplicacaoBean;
	
	private ContextUtil() {}
	
	public static ContextoBean getContextoBean(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		ExternalContext external = contexto.getExternalContext();
		HttpSession session = (HttpSession) external.getSession(true);
		ContextoBean contextoBean = (ContextoBean) session.getAttribute("contextoBean");
		return contextoBean;
	}
	
	public static AplicacaoBean getAplicaocaoBean(){
		FacesContext contexto = FacesContext.getCurrentInstance();
		Application aplicacao = contexto.getApplication();
		
		return aplicacao.evaluateExpressionGet(contexto, "#{aplicacaoBean}", AplicacaoBean.class);
	}

}
