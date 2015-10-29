/**
 * 
 */
package br.com.vfs.bean;

import java.util.logging.Level;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import br.com.vfs.bo.UsuarioBO;
import br.com.vfs.entidade.Usuario;
import br.com.vfs.exception.DefaultException;

/**
 * Classe Action para tratamento do usuario
 * 
 * @author Vinicius
 *
 */
@ManagedBean(name="usuarioBean")
@RequestScoped
public class UsuarioBean extends DefaultBean {
	
	private Usuario usuario = new Usuario();
	private String confirmarSenha;

	/**
	 * construtor default
	 */
	public UsuarioBean() {
		super(UsuarioBean.class);
	}
	
	/**
	 * @return
	 */
	public String novo(){
		return "usuario";
	}
	
	/**
	 * @return
	 */
	public String salvar(){
		FacesContext context = FacesContext.getCurrentInstance();
		if(!this.usuario.getSenha().equalsIgnoreCase(this.confirmarSenha)){
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Senha confirmada incorretamente",""));
			return "usuario";
		}
		UsuarioBO bo = new UsuarioBO();
		
		try {
			usuario.setAtivo(Boolean.TRUE);
			bo.salvar(usuario);
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao inserir usuario no banco",""));
			return "usuario";
		}
		
		return "sucesso";
	}
	
	/**
	 * @return
	 */
	public String logar(){
		FacesContext context = FacesContext.getCurrentInstance();
		UsuarioBO bo = new UsuarioBO();
		
		try {
			usuario = bo.logar(usuario);
		} catch (DefaultException e) {
			getLogger().log(Level.SEVERE, e.getMessage(), e);
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erro ao logar com usuario",""));
			return "index";
		}
		
		return "sucesso";
	}
	
	/**
	 * @return the usuario
	 */
	public Usuario getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario the usuario to set
	 */
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the confirmarSenha
	 */
	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	/**
	 * @param confirmarSenha the confirmarSenha to set
	 */
	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}
