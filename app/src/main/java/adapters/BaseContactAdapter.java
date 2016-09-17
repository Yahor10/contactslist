package adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import by.contacts.app.R;
import data.BaseContact;
import data.BaseEntity;
import data.ItemType;

/**
 * Created by CoolerBy on 17.09.2016.
 */
public abstract class BaseContactAdapter<T extends BaseEntity> extends RecyclerView.Adapter<BaseContactAdapter.ViewHolder>  {

    final List<T> data;

    public BaseContactAdapter(List<T> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final ItemType[] vals = ItemType.values();//copy the values(), calling values() clones the array

        ViewHolder holder = null;
        View view = null;

        switch (vals[viewType]){
            case Element:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_contact, parent, false);
                holder = new ItemViewHolder(view);

                break;
            case Header:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_header, parent, false);
                holder = new HeaderViewHolder(view);
                break;

        }
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final T contact = data.get(position);
        ItemType itemType = holder.getItemType();
        switch (itemType){
            case Element:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.mContactName.setText(contact.getName());
                break;
            case Header:
                HeaderViewHolder groupViewHolder = (HeaderViewHolder) holder;
                groupViewHolder.mGroupText.setText(contact.getName());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return data.isEmpty() ? 0 :  data.size();
    }

    @Override
    public int getItemViewType(int position) {
        return data.get(position) instanceof BaseContact ? ItemType.Element.ordinal(): ItemType.Header.ordinal();
    }

    public abstract static class ViewHolder extends RecyclerView.ViewHolder {
        protected ItemType mtype;
        public ViewHolder(View itemView,ItemType type) {
            super(itemView);
            mtype = type;
        }
        public ItemType getItemType() {
            return mtype;
        }
    }

    public static class ItemViewHolder extends ViewHolder {
        public TextView mContactName;
        public ItemViewHolder(View itemView) {
            super(itemView,ItemType.Element);
            LinearLayout layout = (LinearLayout) itemView;
            mContactName = (TextView) layout.findViewById(R.id.contactsname);
        }
    }

    public static class HeaderViewHolder extends ViewHolder {
        public TextView mGroupText;
        public HeaderViewHolder(View itemView) {
            super(itemView,ItemType.Header);
            mGroupText = (TextView) itemView;
        }
    }
}
