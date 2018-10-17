package game.grid.card;

public interface ICard {

    public void activate();

    public void sleep();

    public boolean toDisplay();

    public boolean isCompatible(ICard c);

}
