package sis.pitt.edu;
import java.util.List;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * This class uses the Item layout to populate the data in the list view
 * @author Liu
 *
 */
public class ItemAdapter extends BaseAdapter{
	private List<Item> mItemtList;
	private LayoutInflater mInflater;
	private boolean mShowCheckbox;

	public ItemAdapter(List<Item> list, LayoutInflater inflater, boolean showCheckbox) {
		mItemtList = list;
		mInflater = inflater;
		mShowCheckbox = showCheckbox;
	}

	@Override
	public int getCount() {
		return mItemtList.size();
	}
	
	@Override
	public Object getItem(int position) {
		return mItemtList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewItem item;
		
	if (convertView == null) {
		convertView = mInflater.inflate(R.layout.item, null);
	item = new ViewItem();
	item.itemImageView = (ImageView) convertView.findViewById(R.id.ImageViewItem);
	item.itemName = (TextView) convertView.findViewById(R.id.TextViewItem);
	item.itemCheckbox = (CheckBox) convertView.findViewById(R.id.CheckBoxSelected);
	convertView.setTag(item);
	} 
	else {
	item = (ViewItem) convertView.getTag();
	}

	Item curItem = mItemtList.get(position);

	item.itemImageView.setImageDrawable(curItem.getImage());
	item.itemName.setText(curItem.getName());

	if(!mShowCheckbox) {
	item.itemCheckbox.setVisibility(View.GONE);
	} else {
		if(curItem.getSelected() == true)
			item.itemCheckbox.setChecked(true);
		else
			item.itemCheckbox.setChecked(false);
	}

		return convertView;
	}

	private class ViewItem {
		ImageView itemImageView;
		TextView itemName;
		CheckBox itemCheckbox;
	}
	
}