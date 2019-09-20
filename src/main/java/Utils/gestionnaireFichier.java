package Utils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.stream.Stream;


public class gestionnaireFichier {

    /**
     * Fournit un tableau constitué des lignes d un fichier
     * @param fileName : chemin du fichier à lire
     * @return tableau des lignes du fichier
     */
    public static String[] getInstructionList(String fileName){
        String[] stringArray = new String[1];
        if(fileName != null && Files.exists(Paths.get(fileName))) {
            try (Stream<String> sf = Files.lines(Paths.get(fileName))) {
                stringArray = sf.toArray(size -> new String[size]);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NullPointerException e) {
                e.printStackTrace();
            }
        }
        return stringArray;
    }

    public static void ecrireLignes(List<String> lines, String fileName){
        try {
            Files.write(Paths.get(fileName), lines, StandardCharsets.UTF_8, StandardOpenOption.CREATE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
