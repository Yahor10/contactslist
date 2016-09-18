package data;

/**
 * Created by CoolerBy on 17.09.2016.
 */
public abstract class BaseEntity {

    public BaseEntity(long id, String name) {
        this.id = id;
        this.name = name;
    }

    protected long id;
    protected  String name;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "BaseEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
