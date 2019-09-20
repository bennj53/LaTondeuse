package Utils;

public enum Orientation {
    N,E,W,S;

    public Orientation plusADroite(){
        switch(this){
            case N:
                return E;
            case E:
                return S;
            case W:
                return N;
            case S:
                return W;
            default:
                return this;
        }
    }

    public Orientation plusAGauche(){
        switch(this){
            case N:
                return W;
            case E:
                return N;
            case W:
                return S;
            case S:
                return E;
            default:
                return this;
        }
    }
}
