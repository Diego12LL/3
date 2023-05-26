public class Ente {

    private String nombre;
    private double fuerza;
    private double defensa;
    private double velocidad;
    private double vida;
    private String fraseVictoria;

    public void declararVictoria(){
        System.out.println(getNombre() + ": " + getFraseVictoria());
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getFuerza() {
        return fuerza;
    }

    public void setFuerza(double fuerza) {
        this.fuerza = fuerza;
    }

    public double getDefensa() {
        return defensa;
    }

    public void setDefensa(double defensa) {
        this.defensa = defensa;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }

    public String getFraseVictoria() {
        return fraseVictoria;
    }

    public void setFraseVictoria(String fraseVictoria) {
        this.fraseVictoria = fraseVictoria;
    }
}
