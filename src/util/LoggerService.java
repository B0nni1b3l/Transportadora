package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoggerService {
    public static void log(String nivel, String mensagem) {
        String dataHora = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"));
        System.out.println("[" + dataHora + "] [" + nivel + "] " + mensagem);
    }
}