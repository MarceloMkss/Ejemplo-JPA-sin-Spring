package com.ipartek.formacion.jpa.ejemplojpa.accesodatos;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ipartek.formacion.jpa.ejemplojpa.entidades.Usuario;

public class UsuarioJpaDao implements Dao<Usuario> {

	private final EntityManagerFactory emf;

	public UsuarioJpaDao() {
		try {
			emf = Persistence.createEntityManagerFactory("com.ipartek.formacion.jpa.ejemplojpa");
		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido conectar con el origen de datos", e);
		}
	}
	
	@Override
	public Iterable<Usuario> obtenerTodos() {
		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			Iterable<Usuario> usuarios = em.createQuery("from Usuario", Usuario.class).getResultList();
			em.getTransaction().commit();

			em.close();

			return usuarios;
		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido obtener todos los usuarios", e);
		}
	}

	@Override
	public Usuario obtenerPorId(Long id) {
		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			Usuario usuario = em.find(Usuario.class, id);
			em.getTransaction().commit();

			em.close();

			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido obtener el usuario cuyo id es " + id, e);
		}
	}

	@Override
	public Usuario agregar(Usuario usuario) {
		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.persist(usuario);
			em.getTransaction().commit();

			em.close();

			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido agregar el usuario " + usuario, e);
		}
	}

	@Override
	public Usuario modificar(Usuario usuario) {
		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.merge(usuario);
			em.getTransaction().commit();

			em.close();

			return usuario;
		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido modificar el usuario " + usuario, e);
		}
	}

	@Override
	public void borrar(Long id) {
		try {
			EntityManager em = emf.createEntityManager();

			em.getTransaction().begin();
			em.remove(em.find(Usuario.class, id));
			em.getTransaction().commit();

			em.close();
		} catch (Exception e) {
			throw new AccesoDatosException("No se ha podido borrar el usuario cuyo id es " + id, e);
		}
	}

}
