package adapters;

import com.viethoa.RecyclerViewFastScroller;

import java.util.List;

import data.BaseEntity;

public class ContactsAdapter extends  BaseContactAdapter<BaseEntity> implements RecyclerViewFastScroller.BubbleTextGetter {
 public ContactsAdapter(List<BaseEntity> data) {
  super(data);
 }

 @Override
 public String getTextToShowInBubble(int pos) {
  if (pos < 0 || pos >= data.size())
   return null;

  String name = data.get(pos).getName();
  if (name == null || name.length() < 1)
   return null;

  return name.substring(0, 1);
 }
}
