/**
 * 
 */
package br.com.vfs.entidade;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.sun.istack.internal.NotNull;

/**
 * @author Vinicius
 *
 */
@Entity
@Table(name="usuario")
public class Usuario extends DefaultEntidade {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4107479454541341625L;

	@Id
	@NotNull
	@Column(name = "idUsuario", insertable = false, updatable = false)
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idUsuario;
	
	@Column(name = "nome", length = 100)
	private String nome;
	
	@NotNull
	@Column(name = "email", length = 100)
	private String email;
	
	@Column(name = "senha", length = 32)
	private String senha;
	
	@Column(name = "permissao", length = 100)
	private String permissao = "ROLE_MEMBRO";
	
	@NotNull
	@Column(name = "ativo")
	private Boolean ativo = Boolean.FALSE;
		
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	private List<Questionario> listaQuestionario;
	
	/**
	 * contrutor padrao
	 */
	public Usuario() {
		super();
	}

	/**
	 * @return the idUsuario
	 */
	public Integer getIdUsuario() {
		return idUsuario;
	}

	/**
	 * @param idUsuario the idUsuario to set
	 */
	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the senha
	 */
	public String getSenha() {
		return senha;
	}

	/**
	 * @param senha the senha to set
	 */
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	/**
	 * @return
	 */
	public String getPermissao() {
		return permissao;
	}

	/**
	 * @param permissao
	 */
	public void setPermissao(String permissao) {
		this.permissao = permissao;
	}

	/**
	 * @return
	 */
	public Boolean getAtivo() {
		return ativo;
	}

	/**
	 * @param ativo
	 */
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}

	/**
	 * @return the listaQuestionario
	 */
	public List<Questionario> getListaQuestionario() {
		return listaQuestionario;
	}

	/**
	 * @param listaQuestionario the listaQuestionario to set
	 */
	public void setListaQuestionario(List<Questionario> listaQuestionario) {
		this.listaQuestionario = listaQuestionario;
	}
}
