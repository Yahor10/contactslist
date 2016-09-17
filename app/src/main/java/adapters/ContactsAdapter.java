package adapters;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

public class ContactsAdapter extends  RecyclerView.Adapter<ContactsAdapter.ViewHolder> {

 Cursor dataCursor;
 Context ctx;

 public static class ViewHolder extends RecyclerView.ViewHolder {
  private int section;
  private int numberOfItemsInSection;

  public ViewHolder(View itemView) {
   super(itemView);
  }

  public boolean isHeader() {
   return false;
  }

  public boolean isGhostHeader() {
   return false;
  }

  public boolean isFooter() {
   return false;
  }

  public int getSection() {
   return section;
  }

  private void setSection(int section) {
   this.section = section;
  }

  public int getNumberOfItemsInSection() {
   return numberOfItemsInSection;
  }

  void setNumberOfItemsInSection(int numberOfItemsInSection) {
   this.numberOfItemsInSection = numberOfItemsInSection;
  }
 }

 public static class ItemViewHolder extends ViewHolder {
  private int positionInSection;

  public ItemViewHolder(View itemView) {
   super(itemView);
  }

  public int getPositionInSection() {
   return positionInSection;
  }

  private void setPositionInSection(int positionInSection) {
   this.positionInSection = positionInSection;
  }
 }

 public static class HeaderViewHolder extends ViewHolder {

  public HeaderViewHolder(View itemView) {
   super(itemView);
  }

  @Override
  public boolean isHeader() {
   return true;
  }
 }

 @Override
 public ContactsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
  return null;
 }

 @Override
 public void onBindViewHolder(ContactsAdapter.ViewHolder holder, int position) {

 }

 @Override
 public int getItemCount() {
  return (dataCursor == null) ? 0 : dataCursor.getCount();
 }

}