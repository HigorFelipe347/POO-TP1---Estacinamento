import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Estacionamento {
    private Map<String, Registro> registros;
    private int vagasCarro = 50, vagasMoto = 30, vagasCaminhao = 10;
    private double totalArrecadado = 0;
    private List<Registro> historico = new ArrayList<>();

    public Estacionamento() {
        registros = new HashMap<>();
    }

    public boolean temVaga(String tipo) {
        switch (tipo) {
            case "Carro": return vagasCarro > 0;
            case "Moto": return vagasMoto > 0;
            case "Caminhão": return vagasCaminhao > 0;
        }
        return false;
    }

    public void ocuparVaga(String tipo) {
        if (tipo.equals("Carro")) vagasCarro--;
        else if (tipo.equals("Moto")) vagasMoto--;
        else if (tipo.equals("Caminhão")) vagasCaminhao--;
    }

    public void liberarVaga(String tipo) {
        if (tipo.equals("Carro")) vagasCarro++;
        else if (tipo.equals("Moto")) vagasMoto++;
        else if (tipo.equals("Caminhão")) vagasCaminhao++;
    }

    public void registrarEntrada(Veiculo veiculo, LocalDateTime entrada) {
        if (!temVaga(veiculo.getTipo())) {
            System.out.println("Sem vagas disponíveis para " + veiculo.getTipo());
            return;
        }
        Registro reg = new Registro(veiculo, entrada);
        registros.put(veiculo.getPlaca(), reg);
        ocuparVaga(veiculo.getTipo());
        System.out.println("Entrada registrada com sucesso.");
    }

    public void registrarSaida(String placa, LocalDateTime saida) {
        Registro reg = registros.get(placa);
        if (reg == null || !reg.estaEstacionado()) {
            System.out.println("Veículo não encontrado ou já saiu.");
            return;
        }

        if (saida.isBefore(reg.getEntrada())) {
            System.out.println("Erro: Hora de saída anterior à entrada.");
            return;
        }

        reg.registrarSaida(saida);
        double total = reg.calcularTotal();
        totalArrecadado += total;
        liberarVaga(reg.getVeiculo().getTipo());
        historico.add(reg);
        registros.remove(placa);

        System.out.printf("Saída registrada. Total a pagar: R$ %.2f (inclui multa de R$ %.2f)\n", total, reg.getMulta());
    }

    public void mostrarVagas() {
        System.out.println("Vagas disponíveis:");
        System.out.printf("Carros: %d/50\n", vagasCarro);
        System.out.printf("Motos: %d/30\n", vagasMoto);
        System.out.printf("Caminhões: %d/10\n", vagasCaminhao);
    }

    public void veiculosEstacionados() {
        System.out.println("Veículos Estacionados:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (Registro reg : registros.values()) {
            System.out.printf("%s | Placa: %s | Entrada: %s\n",
                reg.getVeiculo().getTipo(),
                reg.getVeiculo().getPlaca(),
                reg.getEntrada().toLocalTime().format(formatter));
        }
    }

    public void relatorioTarifas() {
        System.out.println("Relatório de Tarifas:");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        for (Registro r : historico) {
            System.out.printf("Veículo: %s | Placa: %s | Entrada: %s | Saída: %s | Total: R$ %.2f\n",
                    r.getVeiculo().getTipo(),
                    r.getVeiculo().getPlaca(),
                    r.getEntrada().toLocalTime().format(formatter),
                    r.getSaida().toLocalTime().format(formatter),
                    r.getValorPago());
        }
        System.out.printf("Total arrecadado: R$ %.2f\n", totalArrecadado);
    }
}
