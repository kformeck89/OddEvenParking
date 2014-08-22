package com.kformeck.oddevenparking;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

public class TimeSelectionFragment extends Fragment {
	@Override
	public View onCreateView(
			LayoutInflater inflater, ViewGroup container, Bundle savedInsanceState) {
		return inflater.inflate(R.layout.fragment_time_selection, container, false);
	}
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		return super.onOptionsItemSelected(item);
	}
}
