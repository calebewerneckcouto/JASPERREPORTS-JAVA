package projeto.dto.bean;

import java.io.Serializable;
import java.util.Objects;

public class UserGraficoPizza implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer qtde;
	private Integer idade;
	private String estado;

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEstado() {
		return estado;
	}

	public Integer getQtde() {
		return qtde;
	}

	public void setQtde(Integer qtde) {
		this.qtde = qtde;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idade, qtde);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserGraficoPizza other = (UserGraficoPizza) obj;
		return Objects.equals(idade, other.idade) && Objects.equals(qtde, other.qtde);
	}

	@Override
	public String toString() {
		return "UserGraficoPizza [qtde=" + qtde + ", idade=" + idade + ", estado=" + estado + "]";
	}

}
