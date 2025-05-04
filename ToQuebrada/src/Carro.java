public class Carro extends Veiculo {
    public Carro(String placa) {
        super(placa);
        this.tipo = "Carro";
        this.tarifaHora = 5.0;
        this.tempoMaximo = 12;
        this.multaHora = 5.0;
    }
}
