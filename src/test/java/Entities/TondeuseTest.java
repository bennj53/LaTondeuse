package Entities;

import Services.Explorer;
import Services.TypeDeplacement;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class TondeuseTest {

    private static Tondeuse tondeuse;
    private static TypeDeplacement explorer;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        tondeuse = new Tondeuse();
        explorer =  Mockito.mock(Explorer.class);
    }

    @AfterEach
    public void tearDown()throws Exception {

    }
    @Test
    void execute() {
        char[] instructions = new char[]{'A','D','A','A','G','G','A','A'};
        tondeuse.setInstructions(instructions);
        tondeuse.execute(explorer);

        // verifie que la methode xxx a ete appelee exactement X fois sur l'objet tondeuse
        Mockito.verify(explorer, Mockito.times(5)).avancer(Mockito.any(Surface.class),Mockito.any(Tondeuse.class));
        Mockito.verify(explorer, Mockito.times(1)).tournerADroite(tondeuse);
        Mockito.verify(explorer, Mockito.times(2)).tournerAGauche(tondeuse);

        instructions = new char[]{'D','D','G','A','G','A','G','D'};
        tondeuse.setInstructions(instructions);
        tondeuse.execute(explorer);

        Mockito.verify(explorer, Mockito.times(7)).avancer(Mockito.any(Surface.class),Mockito.any(Tondeuse.class));
        Mockito.verify(explorer, Mockito.times(4)).tournerADroite(tondeuse);
        Mockito.verify(explorer, Mockito.times(5)).tournerAGauche(tondeuse);

    }
}