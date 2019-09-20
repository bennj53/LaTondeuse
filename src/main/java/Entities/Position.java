package Entities;

import Utils.Orientation;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class Position {
    private int x;
    private int y;
    private Orientation orientation;

    @Override
    public String toString(){
        return this.x + " " + this.y + " " + this.orientation;
    }
}
