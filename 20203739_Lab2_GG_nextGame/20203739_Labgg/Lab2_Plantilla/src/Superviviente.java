import java.util.ArrayList;

public class Superviviente extends Ente {

        private ArrayList<Objeto> inventario;
    private int kills = 0;
    private int sanidad;
    private int valentia;
    private boolean trauma = false;

    public void declararVictoria(){
        System.out.println("El superviviente exclama: " + getFraseVictoria());
    }

    public double getPoder(){
        double poder_superviviente = this.getFuerza();
        for(Objeto objeto: inventario){
            if(objeto instanceof Arma){
                System.out.println(getNombre() + " usa " + objeto.getNombre());
                poder_superviviente = poder_superviviente + ((Arma) objeto).getPoder();
            }
        }
        return poder_superviviente;
    }

    public void curarse(Superviviente superviviente){
        double vida_superviviente = superviviente.getVida();
        for(Objeto ob : getInventario()){
            if(ob instanceof  ObjetoCurativo){
                ObjetoCurativo objetoCurativo = (ObjetoCurativo) ob;
                vida_superviviente = vida_superviviente + objetoCurativo.getVidaRestaurada();
            }
        }
        int cont=0;
        while(cont<getInventario().size()){                                     //Hago esto en dos iteraciones ya que me aparecía el error .ConcurrentModificationException
            if(getInventario().get(cont) instanceof  ObjetoCurativo){          //El cual se da por estar recorriendo el inventario y modificándolo al mismo tiempo
                                                                                //Entonces primero uso todas las pociones y luego las remuevo de la lista
                getInventario().remove(cont);
            }
            cont = cont+1;
        }

        superviviente.setVida(vida_superviviente);
        System.out.println("La vida del superviviente luego de curarse es de: " + vida_superviviente + " puntos");
    }

    public ArrayList<Objeto> getInventario() {
        return inventario;
    }

    public void setInventario(ArrayList<Objeto> inventario) {
        this.inventario = inventario;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getSanidad() {
        return sanidad;
    }

    public void setSanidad(int sanidad) {
        this.sanidad = sanidad;
    }

    public int getValentia() {
        return valentia;
    }

    public void setValentia(int valentia) {
        this.valentia = valentia;
    }

    public boolean isTrauma() {
        return trauma;
    }

    public void setTrauma(boolean trauma) {
        this.trauma = trauma;
    }
}
