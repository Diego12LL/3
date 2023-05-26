import java.util.ArrayList;
import java.util.Scanner;

public class AppController {

    public void agregarObjeto(ArrayList<Objeto> lista)
    {
        Scanner sc = new Scanner(System.in);
        Objeto objeto= new Objeto();
        System.out.println("Indique el nombre del objeto:");
        objeto.setNombre(sc.nextLine());
        System.out.println("Indique el uso del objeto:");
        objeto.setDescripcion(sc.nextLine());
        System.out.println("Indique el tipo del objeto:");
        System.out.println("1. arma");
        System.out.println("2. objeto curativo");
        System.out.println("3. otro");
        String tipo = sc.nextLine();
        switch (tipo){
            case "1":
                //Arma arma = (Arma) objeto; No se pudo ya que estoy convirtiendo una clase padre en clase hijo uu
                Arma arma = new Arma();
                arma.setNombre(objeto.getNombre());
                arma.setDescripcion(objeto.getDescripcion());
                while (true) {
                    System.out.println("Indique el poder del arma");
                    double poder = Double.parseDouble(sc.nextLine());
                    if (poder > 0 && poder < 1000) {
                        arma.setPoder(poder);
                        lista.add(arma);
                        break;
                    }else{
                        System.out.println("El poder del arma debe ser menor a 1000");
                    }
                }

                break;
            case "2":
                //ObjetoCurativo objetoCurativo = (ObjetoCurativo) objeto;
                ObjetoCurativo objetoCurativo = new ObjetoCurativo();
                objetoCurativo.setNombre(objeto.getNombre());
                objetoCurativo.setDescripcion(objeto.getDescripcion());
                System.out.println("Indique la vida que restaura el objeto");
                objetoCurativo.setVidaRestaurada(Integer.parseInt(sc.nextLine()));
                lista.add(objetoCurativo);
                break;
            case "3":
                lista.add(objeto);
                break;
        }

    }

    public void agregarZombie(ArrayList<Ente> lista)
    {
        Scanner sc = new Scanner(System.in);
        Zombie zombie= new Zombie();
        System.out.println("Indique el nombre del zombie:");
        zombie.setNombre(sc.nextLine());
        System.out.println("Indique la fuerza del zombie:");
        zombie.setFuerza(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique la defensa del zombie:");
        zombie.setDefensa(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique la velocidad del zombie:");
        zombie.setVelocidad(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique la la vida del zombie:");
        zombie.setVida(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique una frase de victoria que diría:");
        zombie.setFraseVictoria(sc.nextLine());
        lista.add(zombie);
        System.out.println("Zombie añadido con nombre: " + zombie.getNombre());
    }

    public void agregarSuperviviente(ArrayList<Ente> lista,ArrayList<Objeto> objetos)
    {
        Scanner sc = new Scanner(System.in);
        Superviviente superviviente= new Superviviente();
        System.out.println("Indique el nombre del superviviente:");
        superviviente.setNombre(sc.nextLine());
        System.out.println("Indique la fuerza del superviviente:");
        superviviente.setFuerza(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique la defensa del superviviente:");
        superviviente.setDefensa(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique la velocidad del superviviente:");
        superviviente.setVelocidad(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique la la vida del superviviente:");
        superviviente.setVida(Double.parseDouble(sc.nextLine()));
        System.out.println("Indique la valentía del superviviente:");
        superviviente.setValentia(Integer.parseInt(sc.nextLine()));
        System.out.println("Indique una frase de victoria que diría:");
        superviviente.setFraseVictoria(sc.nextLine());

        int sanidad = 255 - (int)superviviente.getFuerza() - (int)superviviente.getDefensa();
        superviviente.setSanidad(sanidad);

        ArrayList<Objeto> inventario = new ArrayList<>();
        System.out.println("Indique la cantidad de objetos que tendrá:");
        int cantidad_objetos = Integer.parseInt(sc.nextLine());
        int cont=1;

        while (cont<=cantidad_objetos) {
            double aaa = Math.random() * 10;
            //System.out.println(aaa);
            int num_obj=(int) aaa;
            //System.out.println(num_obj);
            if(num_obj<objetos.size()){
                System.out.println(objetos.get(num_obj).getNombre() + " añadido");
                inventario.add(objetos.get(num_obj));
                cont=cont+1;
            }
        }
        superviviente.setInventario(inventario);
        lista.add(superviviente);

    }

    public int verificarListaPura(ArrayList<Ente> lista){
        int validacion=6;
        int cont_supervivientes=0;
        int cont_zombies =0;

        for(Ente ente : lista) {
            if (ente instanceof Superviviente) {
                cont_supervivientes = cont_supervivientes + 1;
            }else{
                cont_zombies = cont_zombies + 1;
            }
        }
        //System.out.println(cont_supervivientes);
        //System.out.println(cont_zombies);
        if(cont_supervivientes == 0){
            validacion=0;
        }
        if(cont_zombies==0){
            validacion=1;
        }
        if(cont_zombies == 0 && cont_supervivientes ==0){
            validacion=2;
        }
        if(cont_zombies != 0 && cont_supervivientes !=0){
            validacion=3;
        }
        return  validacion;
    }

    public Superviviente obtenerSupervivienteMasValiente(ArrayList<Ente> lista){
        double valentía_mas_alta=0;
        for (Ente ente : lista){
            if(ente instanceof Superviviente){
                Superviviente s = (Superviviente) ente;
                int valentia = s.getValentia();
                if(valentia>=valentía_mas_alta) {
                    valentía_mas_alta = valentia;   //ya encontre a la fuerza mas debil
                }
            }
        }
        Superviviente masValiente = new Superviviente();
        for(Ente ente :lista){
            if(ente instanceof Superviviente) {
                Superviviente s = (Superviviente) ente;
                if (s.getValentia() == valentía_mas_alta) {
                    masValiente = s;
                }
            }
        }
        System.out.println("El superviviente más valiente es: " + masValiente.getNombre());

        return masValiente;
    }

    public Zombie obtenerZombieMasDebil(ArrayList<Ente> lista){
        double fuerza_mas_debil=10000;
        for (Ente ente : lista){
            if(ente instanceof Zombie){
                Zombie z = (Zombie) ente;
                double fuerza=z.getFuerza();
                if(fuerza<=fuerza_mas_debil) {
                    fuerza_mas_debil = fuerza;   //ya encontre a la fuerza mas debil
                }
            }
        }
        Zombie zombie_mas_debil = new Zombie();
        for(Ente ente :lista){
            if(ente instanceof Zombie) {
                Zombie z = (Zombie) ente;
                if (z.getFuerza() == fuerza_mas_debil) {
                    zombie_mas_debil = z;
                }
            }
        }
        System.out.println("El zombie más débil es: " + zombie_mas_debil.getNombre());
        return zombie_mas_debil;
    }

    public void atacarZombie(double poder, Zombie zombie) {
        double vida_zombie = zombie.getVida();
        double daño_recibido=zombie.getDefensa()-poder;         //Profe, esta parte con el maximo no la entendí, ya que si usaba Max, llega un punto en que la defensa
        if(daño_recibido>=0) {                                  //menos el daño es negativa, entonces el Max(diferencia, 0) será 0 por que no se le restará nada a la vida
            vida_zombie = vida_zombie - daño_recibido;          //del zombie y pues se quedará con la misma vida lo mismo sucedería con el superviviente, cuando la diferencia
        }else {                                                 //entre su defensa y daño recibido sea negativo. Por ello consideré de esta manera la actualización de las vidas
            vida_zombie = vida_zombie + daño_recibido;  //en un momento el daño recibido será negativo
        }
        System.out.println("El zombie recibe: " + poder + " puntos de daño.");
        System.out.println("El zombie " + zombie.getNombre() + " aún le queda " + vida_zombie + " puntos de vida");

        if(Math.abs(zombie.getVida()- vida_zombie)<0.1*zombie.getVida()) {
            double nueva_defensa = zombie.getDefensa();
            nueva_defensa = 0.75 * zombie.getDefensa();
            zombie.setDefensa(nueva_defensa);
            System.out.println("La defensa del zombie se ha roto");
        }
        zombie.setVida(vida_zombie);
    }

    public void defenderseDeZombie(double poder, Superviviente superviviente)
    {

        double vida_superviviente = superviviente.getVida();
        double daño_recibido=superviviente.getDefensa()-poder;
        if(daño_recibido>=0) {
            vida_superviviente = vida_superviviente - daño_recibido;
        }else {
            vida_superviviente = vida_superviviente + daño_recibido;  //en un momento el daño recibido será negativo
        }
        System.out.println("El  " + superviviente.getNombre() + " aún le queda " + vida_superviviente + " puntos de vida");
        superviviente.setVida(vida_superviviente);

        double probabilidad = Math.random();
        if(probabilidad>0.5) {
            int sanidad = superviviente.getSanidad();
            sanidad = sanidad - (int)superviviente.getFuerza();
            superviviente.setSanidad(sanidad);
            if(sanidad<0){
                superviviente.setTrauma(true);
                System.out.println(superviviente.getNombre() + ": Ha quedado traumado");
            }
        }


    }

}
