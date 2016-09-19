package by.contacts.app;

import com.viethoa.models.AlphabetItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CoolerBy on 19.09.2016.
 */
public class ContactApplication extends android.app.Application {
    List<AlphabetItem> alphabetItems = new ArrayList<>(32);
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public List<AlphabetItem> getAlphabetItems() {
        return alphabetItems;
    }
}
