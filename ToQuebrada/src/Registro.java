import java.time.LocalDateTime;

public class Registro {
    private Veiculo veiculo;
    private LocalDateTime entrada;
    private LocalDateTime saida;
    private double valorPago;
    private double multa;

    public Registro(Veiculo veiculo, LocalDateTime entrada) {
        this.veiculo = veiculo;
        this.entrada = entrada;
        this.saida = null;
        this.valorPago = 0;
        this.multa = 0;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public LocalDateTime getEntrada() {
        return entrada;
    }

    public LocalDateTime getSaida() {
        return saida;
    }

    public void registrarSaida(LocalDateTime saida) {
        this.saida = saida;
    }

    public double getValorPago() {
        return valorPago;
    }

    public double getMulta() {
        return multa;
    }

    public double calcularTempoHoras() {
        return java.time.Duration.between(entrada, saida).toMinutes() / 60.0;
    }

    public double calcularTotal() {
        double tempo = calcularTempoHoras();
        double totalTarifa = tempo * veiculo.getTarifaHora();
        double excesso = Math.max(0, tempo - veiculo.getTempoMaximo());
        this.multa = excesso * veiculo.getMultaHora();
        this.valorPago = totalTarifa + multa;
        return valorPago;
    }

    public boolean estaEstacionado() {
        return saida == null;
    }
}
