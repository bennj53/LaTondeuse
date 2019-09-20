package Entities;

import Services.TypeDeplacement;

public interface Machine {

    void execute(TypeDeplacement typeDeplacement);

    Position getPosition();

    void setPosition(Position position);

    int getVitesse();

    void setVitesse(int vitesse);

    char[] getInstructions();
}
