package fudi.freddy.mokitosample.share;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import fudi.freddy.mokitosample.R;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class ShareFragment extends Fragment implements ShareContract.ShareView {

    EditText editText;
    Button save, load;
    ListView listView;
    ArrayAdapter<String> stringArrayAdapter;

    SharePresent sharePresent;

    ShareFragment() {
        sharePresent = new SharePresent(this);
    }

    public static ShareFragment getInstance() {
        return new ShareFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_share, container, false);
        editText = (EditText) view.findViewById(R.id.et);
        save = (Button) view.findViewById(R.id.save);
        load = (Button) view.findViewById(R.id.load);
        listView = (ListView) view.findViewById(R.id.list);
        stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1);
        listView.setAdapter(stringArrayAdapter);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stringArrayAdapter.add(editText.getText().toString());
                sharePresent.saveInput(editText.getText().toString());
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharePresent.loadInput();
            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                sharePresent.display((String) adapterView.getItemAtPosition(i));
            }
        });
        return view;
    }

    @Override
    public void clearText() {
        editText.setText("");
    }

    @Override
    public void loadText(String text) {
        editText.setText(text);
    }

    @Override
    public void reLoadList() {
        stringArrayAdapter.notifyDataSetChanged();
    }
}
