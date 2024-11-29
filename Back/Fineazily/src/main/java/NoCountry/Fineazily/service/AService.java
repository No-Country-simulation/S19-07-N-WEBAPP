package NoCountry.Fineazily.service;

import java.util.List;

public abstract class AService<T,D> {

    public abstract void create(T entity);

    public abstract T findById(D id);

    public abstract List<T> findAll();

    public abstract void update(T entity);

    public abstract void delete(D id);


}
