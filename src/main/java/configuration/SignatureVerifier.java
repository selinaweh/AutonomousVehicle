package configuration;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class SignatureVerifier {

    public SignatureVerifier() {
    }

    public boolean verifySign(String pathToJavaArchive) {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("C:\\Program Files\\Java\\jdk-21\\bin\\jarsigner", "-verify", pathToJavaArchive);
            Process process = processBuilder.start();
            process.waitFor();

            InputStream inputStream = process.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                if (line.contains("JAR-Datei verifiziert.")) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}

