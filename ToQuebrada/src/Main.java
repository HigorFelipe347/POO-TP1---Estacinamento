import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Estacionamento est = new Estacionamento();
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter formatoHora = DateTimeFormatter.ofPattern("HH:mm");

        while (true) {
            System.out.println("\n--- SISTEMA ESTACIONAMENTO TÔ QUEBRADA ---");
            System.out.println("1. Registrar Entrada");
            System.out.println("2. Registrar Saída");
            System.out.println("3. Vagas Disponíveis");
            System.out.println("4. Veículos Estacionados");
            System.out.println("5. Relatório de Tarifas");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            int op = sc.nextInt();
            sc.nextLine();

            switch (op) {
                case 1:
                    System.out.print("Tipo de veículo (Carro/Moto/Caminhão): ");
                    String tipo = sc.nextLine();
                    System.out.print("Placa: ");
                    String placa = sc.nextLine();
                    System.out.print("Data de entrada (dd/MM/yyyy): ");
                    String entradaDataStr = sc.nextLine();
                    System.out.print("Hora de entrada (HH:mm): ");
                    String entradaHoraStr = sc.nextLine();

                    LocalDate entradaData = LocalDate.parse(entradaDataStr, formatoData);
                    LocalTime entradaHora = LocalTime.parse(entradaHoraStr, formatoHora);
                    LocalDateTime entrada = LocalDateTime.of(entradaData, entradaHora);

                    Veiculo v;
                    if (tipo.equalsIgnoreCase("Carro")) v = new Carro(placa);
                    else if (tipo.equalsIgnoreCase("Moto")) v = new Moto(placa);
                    else if (tipo.equalsIgnoreCase("Caminhão")) v = new Caminhao(placa);
                    else {
                        System.out.println("Tipo inválido.");
                        break;
                    }

                    est.registrarEntrada(v, entrada);
                    break;

                case 2:
                    System.out.print("Placa do veículo: ");
                    String saidaPlaca = sc.nextLine();
                    System.out.print("Data de saída (dd/MM/yyyy): ");
                    String saidaDataStr = sc.nextLine();
                    System.out.print("Hora de saída (HH:mm): ");
                    String saidaHoraStr = sc.nextLine();

                    LocalDate saidaData = LocalDate.parse(saidaDataStr, formatoData);
                    LocalTime saidaHora = LocalTime.parse(saidaHoraStr, formatoHora);
                    LocalDateTime saida = LocalDateTime.of(saidaData, saidaHora);

                    est.registrarSaida(saidaPlaca, saida);
                    break;

                case 3:
                    est.mostrarVagas();
                    break;

                case 4:
                    est.veiculosEstacionados();
                    break;

                case 5:
                    est.relatorioTarifas();
                    break;

                case 0:
                    System.out.println("Saindo do sistema.");
                    sc.close();
                    return;

                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
