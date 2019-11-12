package org.angmarc.app;

import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import org.angmarch.views.NiceSpinner;
import org.angmarch.views.OnSpinnerItemSelectedListener;
import org.angmarch.views.SpinnerTextFormatter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private NiceSpinner spinner2;
    private NiceSpinner spinner3;
    private NiceSpinner spinner1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupDefault();
        setupTintedWithCustomClass();
        setupXml();

        NiceSpinner sp = new NiceSpinner(this);

        ((FrameLayout)findViewById(R.id.fl)).addView(sp);
    }

    private void setupXml() {
        spinner3 = findViewById(R.id.niceSpinnerXml);
        spinner3.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected: " + item, Toast.LENGTH_SHORT).show();



                List<Person> people = new ArrayList<>();

                people.add(new Person("TonyTonyTonyTonyTonyTonyTony", "TonyTonyTonyTonyTonyTony"));
                people.add(new Person("Steve", "Rogers"));
                people.add(new Person("Bruce", "Banner"));
                spinner2.attachDataSource(people);
            }
        });
    }

    private void setupTintedWithCustomClass() {
        spinner2 = findViewById(R.id.tinted_nice_spinner);
        List<Person> people = new ArrayList<>();

        people.add(new Person("Tony", "Stark"));
        people.add(new Person("Steve", "Rogers"));
        people.add(new Person("Bruce", "Banner"));

        SpinnerTextFormatter textFormatter = new SpinnerTextFormatter<Person>() {
            @Override
            public Spannable format(Person person) {
                if (person == null) {
                    return null;
                }
                return new SpannableString(person.getName() + " " + person.getSurname());
            }
        };

        spinner2.setSpinnerTextFormatter(textFormatter);
        spinner2.setSelectedTextFormatter(textFormatter);
        spinner2.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                Person person = (Person) spinner2.getSelectedItem();
                if (person == null) {
                    Toast.makeText(MainActivity.this, "null: ", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "Selected: " + person.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        spinner2.attachDataSource(people);
    }

    /**
     * yg
     * 1. attachDataSource
     * 2. setOnSpinnerItemSelectedListener
     * 3. setSelectedIndex(0)
     *
     * 或者
     * 1. setOnSpinnerItemSelectedListener
     * 2. attachDataSource
     */
    private void setupDefault() {
        spinner1 = findViewById(R.id.nice_spinner);
        // List<String> dataset = new LinkedList<>(Arrays.asList("One"));
        List<String> dataset = new LinkedList<>(Arrays.asList("Oneddddddddddddddddd", "Twoafafa打发发发发啊发发发发发发发发发发发发发", "Three", "Four", "Five"));
        spinner1.attachDataSource(dataset);
        spinner1.setOnSpinnerItemSelectedListener(new OnSpinnerItemSelectedListener() {
            @Override
            public void onItemSelected(NiceSpinner parent, View view, int position, long id) {
                String item = parent.getItemAtPosition(position).toString();
                Toast.makeText(MainActivity.this, "Selected: " + item, Toast.LENGTH_SHORT).show();
                spinner2.attachDataSource(null);
            }
        });
        spinner1.setSelectedIndex(0);
    }
}

class Person {

    private String name;
    private String surname;

    Person(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    String getName() {
        return name;
    }

    String getSurname() {
        return surname;
    }

    @Override
    public String toString() {
        return name + " " + surname;
    }
}
