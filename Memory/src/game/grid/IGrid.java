package game.grid;

import game.grid.card.ICard;

public interface IGrid<T extends ICard> {

    public T pick(int index);

    public T delete(int index);

    public T set(int index, T element);

    public void activate(int index);

    public void sleep(int index);
}
