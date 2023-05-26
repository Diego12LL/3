public class Zombie extends Ente {
    public void fraseDerrota(String frase){
        int cantidad_caracteres = frase.length();
        int grupos_tres = cantidad_caracteres/3;
        int caracteres_sobrantes = cantidad_caracteres % 3;
        int cont=1;

        while (cont<=grupos_tres){
            System.out.print("Raw");
            cont = cont+1;
        }
        if(caracteres_sobrantes == 1){
            System.out.println("R");
        } else if (caracteres_sobrantes == 2) {
            System.out.println("Ra");
        }
    }
}
