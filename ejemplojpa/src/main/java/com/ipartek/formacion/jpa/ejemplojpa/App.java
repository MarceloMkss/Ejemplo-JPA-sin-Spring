package com.ipartek.formacion.jpa.ejemplojpa;

import java.util.Scanner;

import com.ipartek.formacion.jpa.ejemplojpa.accesodatos.AccesoDatosException;
import com.ipartek.formacion.jpa.ejemplojpa.accesodatos.Dao;
import com.ipartek.formacion.jpa.ejemplojpa.accesodatos.UsuarioJpaDao;
import com.ipartek.formacion.jpa.ejemplojpa.entidades.Usuario;

public class App {
	
	static final protected String OP_LISTAR = "1";
	static final protected String OP_CREAR = "2";
	static final protected String OP_MODIFICAR = "3";
	static final protected String OP_BUSCAR_ID = "4";
	static final protected String OP_ELIMINAR = "5";

	static final protected String OP_SALIR = "s";

	private static boolean isValid = false;
	private static String opc = "";
	private static Scanner entrada = null;
	private static Dao<Usuario> dao = new UsuarioJpaDao();
	
    public static void main( String[] args ) {
    	
    	entrada = new Scanner(System.in);
    	
do {
    		
    		menu();

			System.out.println("Introduzca una Opcion del menu: ");
			opc = entrada.nextLine();
			
			switch (opc) {

			case OP_LISTAR:

				listar();

				break;

			case OP_CREAR:
				
				crear();

				break;

			case OP_MODIFICAR:
				
				modificar();

				break;

			case OP_BUSCAR_ID:

				buscar();

				break;

			case OP_ELIMINAR:

				eliminar();

				break;

			case OP_SALIR:
				isValid = true;
				System.out.println("Has salido del programa, gracias...");

				break;

			default:
				System.out.println("no has introducido una Opcion Valida!!!!! Vuele a introducir el numero: \n");
				break;
			}
    		
			
		} while (!isValid);
    	
    
       	
    }

	private static void eliminar() {
		
		try {
			System.out.println("Introduzca la id que desea Eliminar: ");
			Long id = Long.parseLong(entrada.nextLine());
			
			dao.borrar(id);
		} catch (NumberFormatException e) {
			System.out.println("Error de acceso a datos:" + e.getMessage());
		}
		
	}

	private static void buscar() {
		
		try {
			System.out.println("Introduzca el id que desea buscar");
			Long id = Long.parseLong(entrada.nextLine());
			
			System.out.println(dao.obtenerPorId(id));
		} catch (NumberFormatException e) {
			System.out.println("Error de acceso a datos:" + e.getMessage());
		}
		
	}

	private static void modificar() {
		
		Long id = null;
		
		System.out.println("----------Modificar Cliente----------");
		
		try {
			System.out.println("introduzca la id a Modificar: ");
			id = Long.parseLong(entrada.nextLine());
			
			System.out.println("Introduzca su email: ");
			String email = entrada.nextLine();
			
			System.out.println("Introduzca su contraseña: ");
			String password = entrada.nextLine();
			
			dao.modificar(new Usuario(id, email, password));
		} catch (NumberFormatException e) {
			System.out.println("Error de acceso a datos:" + e.getMessage());
		}
		
	}
	
	

	private static void crear() {
		
		
		System.out.println("----------Agregar Cliente----------");
		
		try {
			System.out.println("Introduzca su email: ");
			String email = entrada.nextLine();
			
			System.out.println("Introduzca su contraseña: ");
			String password = entrada.nextLine();
			
			dao.agregar(new Usuario(null, email, password));
		} catch (AccesoDatosException e) {
			System.out.println("Error de acceso a datos:" + e.getMessage());
		}
		
	}

	private static void listar() {
		
		try {
			for(Usuario usuario: dao.obtenerTodos()) {
				System.out.println(usuario);
			}
		} catch (Exception e) {
			System.out.println("Error de acceso a datos:" + e.getMessage());
		}		
		
	}

	private static void menu() {
		
		System.out.println("*******************MENU*****************");
		System.out.println(" " + OP_LISTAR + ".- Listar");
		System.out.println(" " + OP_CREAR + ".- Crear un Cliente ");
		System.out.println(" " + OP_MODIFICAR + ".- Editar un Cliente");
		System.out.println(" " + OP_BUSCAR_ID + ".- Buscar Cliente por Id ");
		System.out.println(" " + OP_ELIMINAR + ".- Dar de baja un Cliente ");
		System.out.println(" ");
		System.out.println(" " + OP_SALIR + " - Salir");
		System.out.println("************************************");
		
	}
}
