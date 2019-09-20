package Services;

import Entities.Machine;
import Entities.Surface;

public interface TypeDeplacement {
    void avancer(Surface gazon, Machine machine);

    void tournerAGauche(Machine machine);

    void tournerADroite(Machine machine);
}
