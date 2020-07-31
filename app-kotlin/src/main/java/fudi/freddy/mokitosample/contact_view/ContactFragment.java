package fudi.freddy.mokitosample.contact_view;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import fudi.freddy.mokitosample.R;
import fudi.freddy.mokitosample.model.Contact;

/**
 * @version 1.0
 * @autor Romell Domínguez
 * @date 8/13/17
 */

public class ContactFragment extends Fragment implements ContactContract.ContactView {

    EditText editText1, editText2, editText3, editText4, editText5;
    Button save, load;

    ContactPresent contactPresent;

    private ContactFragment() {
        contactPresent = new ContactPresent(this);
    }

    public static ContactFragment getInstance() {
        return new ContactFragment();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact, container, false);
        editText1 = (EditText) view.findViewById(R.id.et_1);
        editText2 = (EditText) view.findViewById(R.id.et_2);
        editText3 = (EditText) view.findViewById(R.id.et_3);
        editText4 = (EditText) view.findViewById(R.id.et_4);
        editText5 = (EditText) view.findViewById(R.id.et_5);
        save = (Button) view.findViewById(R.id.save);
        load = (Button) view.findViewById(R.id.load);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactPresent.saveContact();
            }
        });
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                contactPresent.loadContact(editText1.getText().toString().trim());
            }
        });
        return view;
    }

    @Override
    public void clearText() {
        editText1.setText("");
        editText2.setText("");
        editText3.setText("");
        editText4.setText("");
        editText5.setText("");
    }

    @Override
    public String loadInput1() {
        return editText1.getText().toString().trim();
    }

    @Override
    public String loadInput2() {
        return editText2.getText().toString().trim();
    }

    @Override
    public String loadInput3() {
        return editText3.getText().toString().trim();
    }

    @Override
    public String loadInput4() {
        return editText4.getText().toString().trim();
    }

    @Override
    public String loadInput5() {
        return editText5.getText().toString().trim();
    }

    @Override
    public void display(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public Contact getContactView() {
        return new Contact(
                loadInput3(),
                loadInput2(),
                loadInput1(),
                loadInput4(),
                loadInput5()
        );
    }

    @Override
    public void display(Contact contact) {
        if (contact != null) {
            editText1.setText(contact.getEmail());
            editText2.setText(contact.getName());
            editText3.setText(contact.getCell());
            editText4.setText(contact.getPosition());
            editText5.setText(contact.getCompany());
            display("Datos recuperados");
        } else display("No encontrado");
    }
}
