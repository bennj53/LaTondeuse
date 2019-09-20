package Entities;

import lombok.*;

@ToString
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Surface{
    private Machine[][] machines;
    private int ordonneeMax;
    private int abscisseMax;
    private static Surface INSTANCE = new Surface();

    public static Surface getSurface(){
        return INSTANCE;
    }

    /**
     * Changer la postion d une tondeuse sur la surface
     * @param machine : tondeuse avec sa postion actuelle
     * @param newPosition : nouvelle position demandee
     */
    public void changerPosition(Machine machine, Position newPosition) {
        if(positionValide(machine.getPosition(), newPosition)
                && machines[machine.getPosition().getX()][machine.getPosition().getY()] == machine){
            machines[machine.getPosition().getX()][machine.getPosition().getY()] = null;
            //System.out.print(machine.getPosition().toString() + "---->");
            machine.setPosition(newPosition);
            //System.out.println(machine.getPosition().toString());
            machines[newPosition.getX()][newPosition.getY()] = machine;
        }
    }

    /**
     * Ajouter une tondeuse sur la surface
     * @param machine : tondeuse a ajouter
     */
    public void addMachine(Machine machine) {
            Position positionTondeuse = machine.getPosition();
            if(positionValide(positionTondeuse)){
                machines[positionTondeuse.getX()][positionTondeuse.getY()] = machine;
            }
    }

    /**
     * Indique si la postion existe sur la surface
     * @param position
     * @return : true si position valide sinon false
     */
    private boolean positionValide(Position position) {
        if (position.getX() <= abscisseMax
                && position.getY() <= ordonneeMax
                && position.getX() >= 0
                && position.getY() >= 0){
            return true;
        }

        return false;
    }

    /**
     * Indique si toutes les postions existent sur la surface
     * @param : List of Positions
     * @return : true si position valide sinon false
     */
    private boolean positionValide(Position... positions) {
        boolean positionValide = true;

        if (positions == null){
            positionValide = false;
        }else{
            for (Position position : positions){
                if (position.getX() >= abscisseMax
                        || position.getY() >= ordonneeMax
                        || position.getX() < 0
                        || position.getY() < 0  ){

                    return positionValide = false;
                }
            }
        }

        return positionValide;
    }
}
