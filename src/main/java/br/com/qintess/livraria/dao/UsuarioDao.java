package br.com.qintess.livraria.dao;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import br.com.qintess.livraria.modelo.Usuario;

@Repository
@SuppressWarnings("serial")
public class UsuarioDao implements Serializable {

	@PersistenceContext
	EntityManager manager;

	public boolean existe(Usuario usuario) {

		TypedQuery<Usuario> query = manager.createQuery(
				" select u from Usuario u "
						+ " where u.email = :pEmail and u.senha = :pSenha",
				Usuario.class);

		query.setParameter("pEmail", usuario.getEmail());
		query.setParameter("pSenha", usuario.getSenha());
		try {
			@SuppressWarnings("unused")
			Usuario resultado = query.getSingleResult();
		} catch (NoResultException ex) {
			return false;
		}

		return true;
	}

}
