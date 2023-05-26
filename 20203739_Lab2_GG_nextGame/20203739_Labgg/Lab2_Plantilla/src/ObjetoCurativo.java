public class ObjetoCurativo extends  Objeto{

    private int vidaRestaurada;

    public ObjetoCurativo(String nombre, String descripcion, int vidaRestaurada) {
        super(nombre, descripcion);
        this.vidaRestaurada = vidaRestaurada;
    }
    public ObjetoCurativo() {
    }


    public int getVidaRestaurada() {
        return vidaRestaurada;
    }

    public void setVidaRestaurada(int vidaRestaurada) {
        this.vidaRestaurada = vidaRestaurada;
    }
}
