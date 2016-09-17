package data;

/**
 * Created by CoolerBy on 17.09.2016.
 */
public class BaseContact extends BaseEntity {

    private String secondName;

    public BaseContact(long id,String name ,String secondName) {
        super(id,name);
        this.secondName = secondName;
    }

    public String getSecondName() {
        return secondName;
    }
}
