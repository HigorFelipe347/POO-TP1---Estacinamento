public class Caminhao extends Veiculo {
    public Caminhao(String placa) {
        super(placa);
        this.tipo = "Caminhão";
        this.tarifaHora = 10.0;
        this.tempoMaximo = 48;
        this.multaHora = 10.0;
    }
}
