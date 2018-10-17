package serialiseur;

public interface ISerialiseur<T> {

    public String serialiser(T element);

    public T deserialiser(String s);

    public String getSeparateur();

}
