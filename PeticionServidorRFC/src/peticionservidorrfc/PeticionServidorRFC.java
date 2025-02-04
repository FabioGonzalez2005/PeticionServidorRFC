package peticionservidorrfc;

import java.net.URL;
import java.net.URLConnection;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.PrintWriter;

public class PeticionServidorRFC {
    static String urlStr = "https://www.rfc-editor.org/search/rfc_search_detail.php";

    public static void main(String[] args) {
        try {
            URL url = new URL(urlStr);
            try {
                URLConnection urlConn = url.openConnection();
                urlConn.setDoOutput(true);

                PrintWriter salPW = new PrintWriter(urlConn.getOutputStream());
                salPW.write("rfc=793");
                salPW.close();

                try (BufferedReader br = new BufferedReader(
                        new InputStreamReader(urlConn.getInputStream()))) {
                    System.out.printf("Contenidos de %s\n", urlStr);
                    System.out.printf("------------------------------\n");
                    String linea;
                    while ((linea = br.readLine()) != null) {
                        System.out.println(linea);
                    }
                    System.out.println("------------------------------\n");
                }

            } catch (IOException ex) {
                System.out.printf("Error de E/S obteniendo contenidos de URL.\n");
                ex.printStackTrace();
            }

        } catch (MalformedURLException ex) {
            System.out.printf("URL mal formada: %s.\n", urlStr);
            ex.printStackTrace();
        }
    }
}