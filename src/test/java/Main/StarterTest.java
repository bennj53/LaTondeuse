package Main;

import Entities.Machine;
import Entities.Position;
import Entities.Surface;
import Entities.Tondeuse;
import Utils.Orientation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.*;

class StarterTest {

    private static String regexGazon;
    private static String regexPositionTondeuse;
    private static String regexInstructions;
    private static int vitesse;

    @BeforeEach
    public void setUp() throws Exception {
        regexGazon = "[0-9]*\\h[0-9]*";
        regexPositionTondeuse = "[0-9]*\\h[0-9]*\\h[NEWS]";
        regexInstructions = "[AGD]*";
        vitesse = 1;
    }


    @Test
    void initTondeuse() {
        String postion = null;
        String instruction = null;
        Machine machine = Starter.initTondeuse(postion,instruction,regexPositionTondeuse,regexInstructions,vitesse);
        assertNull(machine);

        postion = "1 N";
        instruction = "A";
        machine = Starter.initTondeuse(postion,instruction,regexPositionTondeuse,regexInstructions,vitesse);
        assertNull(machine);

        postion = "10 10 Z";
        instruction = "AAG";
        machine = Starter.initTondeuse(postion,instruction,regexPositionTondeuse,regexInstructions,vitesse);
        assertNull(machine);

        postion = "10 10 N";
        instruction = "ZAG";
        machine = Starter.initTondeuse(postion,instruction,regexPositionTondeuse,regexInstructions,vitesse);
        assertNull(machine);

        postion = "10 10 S";
        Position pos = new Position(10,10, Orientation.S);
        instruction = "AAGDDA";
        char[] instructionTab = new char[]{'A','A','G','D','D','A'};
        machine = Starter.initTondeuse(postion,instruction,regexPositionTondeuse,regexInstructions,vitesse);
        Machine machineResultatAttendu = new Tondeuse(pos,vitesse,instructionTab);
        assertEquals(machineResultatAttendu, machine);

    }

    @Test
    void initSurface() {
        String dimension = null;

        Surface surface = Starter.initSurface(dimension, regexGazon);
        assertNull(surface);
        assertNotEquals(surface, Surface.getSurface());

        dimension = "5 A";

        surface = Starter.initSurface(dimension, regexGazon);
        assertNull(surface);
        assertNotEquals(surface, Surface.getSurface());

        dimension = "5 5";

        surface = Starter.initSurface(dimension, regexGazon);
        assertNotNull(surface);
        assertEquals(surface, Surface.getSurface());
    }
}