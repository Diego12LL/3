import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.print("Bienvenido a Zombiweb, donde la mayor pesadilla esta por llegar");
        System.out.print("En la primera etapa vas a configurar tu partida y en la segunda los zombies van a ir a por ti buahaha");
        boolean finInicio = false;
        boolean finjuego = false;

        Scanner scanner = new Scanner(System.in);
        ArrayList<Objeto> objetos = new ArrayList<>();
        ArrayList<Ente> entes = new ArrayList<>();
        AppController controller = new AppController();


        preparandoJuego:
        while (!finInicio) {
            System.out.println("Elige la opcion que desees: ");
            System.out.println("1. Añadir objetos a la lista");
            System.out.println("2. Añadir un superviviente");
            System.out.println("3. Añadir un zombie");
            System.out.println("5. Empezar el juego");

            String opcion = scanner.nextLine();

            switch (opcion){
                case "1" -> controller.agregarObjeto(objetos);
                case "2" -> controller.agregarSuperviviente(entes,objetos);
                case "3" -> controller.agregarZombie(entes);
                case "5" -> {
                    int res = controller.verificarListaPura(entes);
                    switch (res){
                        case 0:
                            System.out.println("No se tienen supervivientes");
                            break;
                        case 1:
                            System.out.println("No se tienen zombies");
                            break;
                        case 2:
                            System.out.println("No se tienen elementos en la lista");
                            break;
                        case 3:
                            break preparandoJuego;
                    }

                }
            }
        }

        jugandoAndo:
        while (!finjuego)
        {
            int cant_supervi = 0;
            int cant_zombies = 0;
            for(Ente ente: entes){
                if(ente instanceof Superviviente){
                    cant_supervi = cant_supervi +1;
                }else{
                    cant_zombies = cant_zombies +1;
                }
            }
            if(cant_supervi == 0){
                System.out.println("No queda ningún superviviente vivo. Los zombies arrasaron con el mundo. GAME OVER");
                break jugandoAndo;
            } else if (cant_zombies==0) {
                System.out.println("No queda ningún zombie, el mundo se ha salvado de esta crisis, pero aun hay mucho por trabajar");
                break jugandoAndo;
            }else {
                System.out.println("Cantidad zombies: " + cant_zombies + "    Can supervivientes: " + cant_supervi);
                System.out.println("Juego iniciado");
                Superviviente supervivienteActivo = controller.obtenerSupervivienteMasValiente(entes);
                Zombie zombieActivo = controller.obtenerZombieMasDebil(entes);
                boolean terminoBatalla = true;
                while (terminoBatalla) {
                    int kills_superviviente = supervivienteActivo.getKills();
                    if (supervivienteActivo.getVelocidad() > zombieActivo.getVelocidad()) {
                        System.out.println("El superviviente " + supervivienteActivo.getNombre() + " ataca.");
                        controller.atacarZombie(supervivienteActivo.getPoder(), zombieActivo);
                        System.out.println("El zombie " + zombieActivo.getNombre() + " ataca.");
                        controller.defenderseDeZombie(zombieActivo.getFuerza(), supervivienteActivo);
                    } else {
                        System.out.println("El zombie " + zombieActivo.getNombre() + " ataca.");
                        controller.defenderseDeZombie(zombieActivo.getFuerza(), supervivienteActivo);
                        System.out.println("El superviviente " + supervivienteActivo.getNombre() + " ataca.");
                        controller.atacarZombie(supervivienteActivo.getPoder(), zombieActivo);
                    }

                    if (zombieActivo.getVida() <= 0) {
                        supervivienteActivo.declararVictoria();
                        if (supervivienteActivo.isTrauma() == true) {
                            System.out.println("Pero igual este mundo no tiene esperanza.");
                            double vida = supervivienteActivo.getVida();
                            vida = vida * 0.5;
                            System.out.println("Oh no! Estoy traumado. Mi vidaaaaa noooo!");
                            supervivienteActivo.setVida(vida);
                        }

                        zombieActivo.fraseDerrota(zombieActivo.getFraseVictoria());
                        System.out.println("El zombie " + zombieActivo.getNombre() + " ha sido derrotado.");
                        entes.remove(zombieActivo);
                        kills_superviviente = kills_superviviente + 1;
                        supervivienteActivo.setKills(kills_superviviente);
                        supervivienteActivo.curarse(supervivienteActivo);
                        terminoBatalla = false;


                    } else if (supervivienteActivo.getVida() <= 0) {
                        System.out.println("El zombie ezclama: " + zombieActivo.getFraseVictoria());
                        System.out.println("El superviviente " + supervivienteActivo.getNombre() + " ha muerto uu");
                        System.out.println("Pero asesinó a: " + kills_superviviente);
                        System.out.println("el zombie " + zombieActivo.getNombre() + " tiene " + zombieActivo.getVida() + " puntos de vida aún.");
                        entes.remove(supervivienteActivo);
                        terminoBatalla = false;
                    }
                }
            }
        }
    }
}