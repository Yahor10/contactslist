package adapters;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cocosw.bottomsheet.BottomSheet;

import java.util.List;

import by.contacts.app.R;
import data.BaseContact;
import data.BaseEntity;
import data.BaseGroup;
import data.ItemType;

/**
 * Created by CoolerBy on 17.09.2016.
 */
public abstract class BaseContactAdapter<T extends BaseEntity> extends RecyclerView.Adapter<BaseContactAdapter.ViewHolder>  {

    protected final List<T> data;

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
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final T contact = data.get(position);
        ItemType itemType = holder.getItemType();
        switch (itemType){
            case Element:
                ItemViewHolder itemViewHolder = (ItemViewHolder) holder;
                itemViewHolder.mContactName.setText(contact.getName());
                itemViewHolder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                       final Activity activity = (Activity) v.getContext();
                        new BottomSheet.Builder(activity).title(contact.getName()).sheet(R.menu.bottom_sheet)
                                .listener(new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case R.id.share:
                                        Intent share = new Intent(android.content.Intent.ACTION_SEND);
                                        share.setType("text/plain");
                                        share.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);

                                        // Add data to the intent, the receiving app will decide
                                        // what to do with it.
                                        share.putExtra(Intent.EXTRA_SUBJECT, "Contact share");
                                        share.putExtra(Intent.EXTRA_TEXT, contact.getName());
                                        activity.startActivity(Intent.createChooser(share, "Share contact!"));

                                        break;
                                    case R.id.delete:
                                        T prev = data.get(position - 1);
                                        T next = data.get(position + 1);

                                        int notifyPos = position;
                                        if(prev instanceof BaseGroup && next!= null &&
                                                next.getName().charAt(0) !=
                                                        contact.getName().charAt(0)){ // not same group
                                            notifyPos = position - 1;
                                            data.remove(position - 1);
                                        }else if(prev instanceof BaseGroup && next == null) {
                                            data.remove(position - 1);
                                            notifyPos = position - 1;
                                        }
                                        data.remove(contact);

                                        notifyItemRemoved(notifyPos);
                                        notifyItemRangeChanged(notifyPos, getItemCount());
                                        break;
                                    case R.id.close:
                                        break;
                                }
                            }
                        }).show();
                        return true;
                    }
                });
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
