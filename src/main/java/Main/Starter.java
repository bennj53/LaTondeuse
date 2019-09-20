package Main;

import Entities.Machine;
import Entities.Position;
import Entities.Surface;
import Entities.Tondeuse;
import Services.Explorer;
import Services.TypeDeplacement;
import Utils.gestionnaireFichier;
import Utils.Orientation;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Pattern;

/**
 * Classe de demarrage de l'application de gestion des tondeuses automatiques
 */
public class Starter {
    private static List<Machine> tondeuses = new ArrayList<>();
    private static List<String> resultats = new ArrayList<>();
    private static Properties appProps;

    public static void main(String[] args) {

        //chargement des properties
        String rootPath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        String appConfigPath = rootPath + "app.properties";
        appProps = new Properties();
        try {
            InputStream input = new FileInputStream(appConfigPath);
            appProps.load(input);
            input.close();

            String fichierIn = appProps.getProperty("file.path");
            String fichierOut = appProps.getProperty("fileOut.path");
            String regexPositionTondeuse = appProps.getProperty("file.regexPositionTondeuse");
            String regexInstructions = appProps.getProperty("file.regexInstructions");
            String regexGazon = appProps.getProperty("file.regexGazon");
            int vitesse = Integer.valueOf(appProps.getProperty("app.vitesse"));

            //lecture du fichier
            String[] data = gestionnaireFichier.getInstructionList(fichierIn);

            //creation de la surface
            Surface gazon = initSurface(data[0], regexGazon);

            //initialisation du type de deplacement
            TypeDeplacement typeDeplacement = new Explorer();

            if (gazon != null) {
                //creation des tondeuses
                Machine tondeuse;
                for (int i = 1; i < data.length - 1; i += 2) {
                    tondeuse = initTondeuse(data[i], data[i + 1], regexPositionTondeuse,regexInstructions, vitesse);
                    if(tondeuse != null)
                        tondeuses.add(tondeuse);
                }
//synchronized () {
    for (Machine machine : tondeuses) {
        // postionnement de la tondeuse
        gazon.addMachine(machine);
        // lancement de la tondeuse
        //System.out.println("--------------START TONDEUSE-------------");
        machine.execute(typeDeplacement);
        //System.out.println("---------------------------------------");
        System.out.println(machine.getPosition().toString());
        //System.out.println("--------------END TONDEUSE-------------");
        resultats.add(machine.getPosition().toString());
        initSurface(data[0], regexGazon);
    }
//}
                //écrit le résultat dans un fichier
                //gestionnaireFichier.ecrireLignes(resultats, fichierOut);

            }else{
                System.out.println("Erreur format surface invalide.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     * Creation tondeuses
     * @param s : position tondeuse
     * @param s1 : instructions tondeuse s
     * @param regexS : regex applique au param s
     * @param regexS2 : regex applique au param s1
     */
    public static Machine initTondeuse(String s, String s1, String regexS, String regexS2, int vitesse) {
        if(s != null
                && s1 != null
                && regexS != null
                && regexS2 != null
                && Pattern.matches(regexS, s)
                && Pattern.matches(regexS2, s1)){

            String[] position = s.split(" ");
            int x = Integer.valueOf(position[0]);
            int y = Integer.valueOf(position[1]);
            Orientation orientation = Orientation.valueOf(position[2]);
            char[] instructions = s1.toCharArray();

           return (new Tondeuse(new Position(x,y,orientation),vitesse,instructions));
        }

        return null;
    }

    /**
     * Creation surface
     * @param dimension : dimensions de la surface
     * @return : singleton surface
     */
    public static Surface initSurface(String dimension, String regex){
        if(dimension != null
                && regex != null
                && Pattern.matches(regex, dimension)){

            String[] position = dimension.split(" ");
            int x = Integer.valueOf(position[0])+1;
            int y = Integer.valueOf(position[1])+1;

            Surface.getSurface().setMachines(new Machine[x][y]);
            Surface.getSurface().setAbscisseMax(x);
            Surface.getSurface().setOrdonneeMax(y);

        }else{
            return null;
        }

        return Surface.getSurface();
    }
}
