package repository;

public interface Repository {
    public void add(Object object);
    public void remove(Object object);
    public void update(Object object);
    public Object get(Object object);

}
