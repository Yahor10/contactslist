package loaders;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;

import com.viethoa.models.AlphabetItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import by.contacts.app.ContactApplication;
import by.contacts.app.R;
import data.BaseContact;
import data.BaseEntity;
import data.BaseGroup;

/**
 * Created by CoolerBy on 17.09.2016.
 */
public class ContactsLoader<T extends BaseEntity> extends AsyncTaskLoader<List<BaseEntity>> {
    /**
     * Stores away the application context associated with context.
     * Since Loaders can be used across multiple activities it's dangerous to
     * store the context directly; always use {@link #getContext()} to retrieve
     * the Loader's Context, don't use the constructor argument directly.
     * The Context returned by {@link #getContext} is safe to use across
     * Activity instances.
     *
     * @param context used to retrieve the application context.
     */
    public ContactsLoader(Context context) {
        super(context);
    }

    @Override
    public List<BaseEntity> loadInBackground() {
        ContactApplication context = (ContactApplication) getContext();
        String[] list = context.getResources().getStringArray(R.array.fruits_array);

        final List<BaseEntity>result = new ArrayList<>(list.length + 32);
        final LinkedHashMap mapIndex = new LinkedHashMap(32);
        for (int i = 0; i < list.length; i++) {
            String fruit = list[i];
            String index = fruit.substring(0, 1);

            if (mapIndex.get(index) == null){
                result.add(new BaseGroup(i,index));
                mapIndex.put(index, i);
                context.getAlphabetItems().add(new AlphabetItem(i, index, false));
            }
            result.add(new BaseContact(0,fruit,""));
        }

        return result;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
