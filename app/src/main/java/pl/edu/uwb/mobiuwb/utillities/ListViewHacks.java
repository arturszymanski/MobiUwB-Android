package pl.edu.uwb.mobiuwb.utillities;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by sennajavie on 2015-05-15.
 */
public class ListViewHacks
{
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null)
        {
            return;
        }

        int desiredWidth = View.MeasureSpec
                .makeMeasureSpec(
                        listView.getWidth(),
                        View.MeasureSpec.UNSPECIFIED);
        int totalHeight = 0;
        View view = null;
        for (int i = 0; i < listAdapter.getCount(); i++)
        {
            view = listAdapter.getView(i, view, listView);
            if (i == 0)
            {
                view.setLayoutParams(
                        new ViewGroup.LayoutParams(
                                desiredWidth,
                                AbsListView.LayoutParams.WRAP_CONTENT));
            }

            view.measure(
                    desiredWidth,
                    View.MeasureSpec.UNSPECIFIED);
            totalHeight += view.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
        listView.requestLayout();
    }
}
