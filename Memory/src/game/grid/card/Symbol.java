package game.grid.card;

public enum Symbol {
    CLUB(">8"), SPADE("->"), DIAMOND("<>"), HEART("<3"), EMPTY("XX");

    private String message;

    Symbol(String s){
        message = s;
    }

    public static int getNumberOfSymbols(){
        return values().length - 1;
    }

    public static Symbol[] getSymbols(){
        return values();
    }

    @Override
    public String toString() {
        return message;
    }
}
