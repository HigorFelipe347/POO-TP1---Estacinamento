public abstract class Veiculo {
    protected String placa;
    protected String tipo;
    protected double tarifaHora;
    protected int tempoMaximo;
    protected double multaHora;

    public Veiculo(String placa) {
        this.placa = placa;
    }

    public String getPlaca() {
        return placa;
    }

    public String getTipo() {
        return tipo;
    }

    public double getTarifaHora() {
        return tarifaHora;
    }

    public int getTempoMaximo() {
        return tempoMaximo;
    }

    public double getMultaHora() {
        return multaHora;
    }
}
