public class Moto extends Veiculo {
    public Moto(String placa) {
        super(placa);
        this.tipo = "Moto";
        this.tarifaHora = 3.0;
        this.tempoMaximo = 24;
        this.multaHora = 3.0;
    }
}
