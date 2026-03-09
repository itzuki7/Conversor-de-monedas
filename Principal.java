import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        ConsultaMoneda consulta = new ConsultaMoneda();
        List<String> historial = new ArrayList<>();
        int opcion = 0;

        while (opcion != 7) {
            System.out.println("""
                ***************************************************
                Bienvenido/a al conversor de monedas!!
                
                1) Dólar =>> Peso argentino
                2) Peso argentino =>> Dólar
                3) Dólar =>> Real brasileño
                4) Real brasileño =>> Dólar
                5) Dólar =>> Peso colombiano
                6) Peso colombiano =>> Dólar
                7) Salir
                Elija una opción válida:
                ***************************************************
                """);

            try {
                opcion = Integer.parseInt(lectura.nextLine());

                if (opcion == 7) {
                    JsonHistorial generador = new JsonHistorial();
                    generador.guardarJson(historial);
                    System.out.println("Guardando transacciones, saliendo del programa");
                    break;
                }

                System.out.println("Ingrese el monto que desea convertir:");
                double valor = Double.parseDouble(lectura.nextLine());
                Moneda moneda = consulta.buscarMoneda();
                double resultado = 0;
                String mensaje = "";

                switch (opcion) {
                    case 1 -> {
                        double tasa = moneda.conversion_rates().get("ARS");
                        resultado = valor * tasa;
                        mensaje = String.format("%.2f [USD] => %.2f [ARS]", valor, resultado);
                    }
                    case 2 -> {
                        double tasa = moneda.conversion_rates().get("ARS");
                        resultado = valor / tasa;
                        mensaje = String.format("%.2f [ARS] => %.2f [USD]", valor, resultado);
                    }
                    case 3 -> {
                        double tasa = moneda.conversion_rates().get("BRL");
                        resultado = valor * tasa;
                        mensaje = String.format("%.2f [USD] => %.2f [BRL]", valor, resultado);
                    }
                    case 4 -> {
                        double tasa = moneda.conversion_rates().get("BRL");
                        resultado = valor / tasa;
                        mensaje = String.format("%.2f [BRL] => %.2f [USD]", valor, resultado);
                    }
                    case 5 -> {
                        double tasa = moneda.conversion_rates().get("COP");
                        resultado = valor * tasa;
                        mensaje = String.format("%.2f [USD] => %.2f [COP]", valor, resultado);
                    }
                    case 6 -> {
                        double tasa = moneda.conversion_rates().get("COP");
                        resultado = valor / tasa;
                        mensaje = String.format("%.2f [COP] => %.2f [USD]", valor, resultado);
                    }
                    default -> System.out.println("Opción no válida.");
                }

                if (!mensaje.isEmpty()) {
                    System.out.println("Resultado: " + mensaje);
                    historial.add(mensaje);
                }

            } catch (NumberFormatException e) {
                System.out.println("Error: Ingrese un número válido.");
            } catch (IOException | RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}