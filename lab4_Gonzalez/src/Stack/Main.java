package Stack;

import Stack.controllers.StackController;
import Stack.models.Etiqueta;
import Stack.models.Stack;
import Stack.views.MenuPrincipal;

import java.awt.EventQueue;
import java.util.ArrayList;


public class Main {
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Stack stack = new Stack();
			        ArrayList<Etiqueta> listaEtiquetas = new ArrayList<>();
			        StackController controller = new StackController(stack, listaEtiquetas);
			        crearStackInicial(controller);
			        
					MenuPrincipal window = new MenuPrincipal(controller);
					window.getFrame().setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

    /**
     * Crea el stack inicial con 5 usuarios registrados, 3 etiquetas, 5 preguntas y 10 respuestas.
     * @param controller StackController, controlador del stack.
     */
    private static void crearStackInicial(StackController controller){
        controller.registrarUsuario("jorge","123");
        controller.registrarUsuario("kamila","zxc");
        controller.registrarUsuario("pedro","qwerty");
        controller.registrarUsuario("juan","gatojuanito");
        controller.registrarUsuario("sofia","harrystyles");

        controller.crearEtiqueta("python","lenguaje python");
        controller.crearEtiqueta("C","lenguaje C");
        controller.crearEtiqueta("java","lenguaje java");

        controller.loginUsuario("jorge","123");
        controller.ask("Compilar en Java","Como puedo compilar multiples archivos de java?","java");
        controller.logoutUsuario();

        controller.loginUsuario("kamila","zxc");
        controller.ask("Error segmentation fault","Porque me sale el error segmentation fault? como lo puedo arreglar","C");
        controller.logoutUsuario();

        controller.loginUsuario("pedro","qwerty");
        controller.ask("Problema con variables por referencia","Tengo problemas con unas variables que paso por referencia en mi codigo *CODIGO* ","python");
        controller.logoutUsuario();

        controller.loginUsuario("juan","gatojuanito");
        controller.ask("Pregunta de relleno 1","Contenido relleno.","");
        controller.logoutUsuario();

        controller.loginUsuario("sofia","harrystyles");
        controller.ask("Pregunta de relleno 2","Contenido relleno, sin imaginacion.","");
        controller.logoutUsuario();

        controller.loginUsuario("jorge","123");
        controller.answer("2","Este error se debe a que estas trantando de acceder a una direccion de memoria que no tienes el control.");
        controller.logoutUsuario();

        controller.loginUsuario("jorge","123");
        controller.answer("5","Respuesta relleno.");
        controller.logoutUsuario();

        controller.loginUsuario("kamila","zxc");
        controller.answer("1","En la terminal con el comando javac Archivo1.java Archivo2.java, y así con todos los demás.");
        controller.logoutUsuario();

        controller.loginUsuario("kamila","zxc");
        controller.answer("3","Primero en la linea *insetar codigo de esa linea* esta declarando mal ...");
        controller.logoutUsuario();

        controller.loginUsuario("pedro","qwerty");
        controller.answer("4","Respuesta relleno.");
        controller.logoutUsuario();

        controller.loginUsuario("pedro","qwerty");
        controller.answer("2","Significa violacion de acceso, lo mas probable es que estes utilizando un puntero que no esta correcto.");
        controller.logoutUsuario();

        controller.loginUsuario("juan","gatojuanito");
        controller.answer("3","Respuesta relleno.");
        controller.logoutUsuario();

        controller.loginUsuario("juan","gatojuanito");
        controller.answer("5","Respuesta relleno.");
        controller.logoutUsuario();

        controller.loginUsuario("sofia","harrystyles");
        controller.answer("1","puedes utilizar javac *.java en el directorio donde tienes todas las clases.");
        controller.logoutUsuario();

        controller.loginUsuario("sofia","harrystyles");
        controller.answer("4","Respuesta relleno.");
        controller.logoutUsuario();
    }

}
