package com.example.emergencycalljambi;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class RumahSakit extends ListActivity {
    //Buat arrray 2 dimensi untuk data taksi yang terdiri dari nama dan nomor telepon
    private String[][] pelayan_data = { { "RS Raden Mattaher", "(0741)63394" },
            { "RS Bratanata (DKT)", "(0741)23164" }, { "RS Bhayangkara", "(0741)23246" },
            { "Siloam Hospital Jambi", "(0741)573333" }, { "Budhi Graha Jambi", "(0741)22449" },
            {"Santa Theresia","(0741) 23119"},{"RS. Kambang","(0741) 61714"},
            {"RS. Jiwa Jambi","(0741)580254"},{"RSIA Annisa","(0741)445226"}
             };
    //Buat arrray 1 dimensi untuk keperluan pembuatan menu list provider taxi
    private String[] pelayan_name = { "RS Raden Mattaher", "RS Bratanata (DKT)",
    		"RS Bhayangkara","Siloam Hospital Jambi", "Budhi Graha Jambi",
    		"Santa Theresia","RS. Kambang","RS. Jiwa Jambi","RSIA Annisa","Kembali" };
     
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        // Create an ArrayAdapter, that will actually make the Strings above
        // appear in the ListView
        // Menset nilai array ke dalam list adapater sehingga data pada array
        // akan dimunculkan dalam list
        this.setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pelayan_name));
    }
     
     
    @Override
    /**method ini akan mengoveride method onListItemClick yang ada pada class List Activity
     * method ini akan dipanggil apabilai ada salah satu item dari list menu yang dipilih
     */
    protected void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        // Get the item that was clicked
        // Menangkap nilai text yang dklik
//        Object o = this.getListAdapter().getItem(position);
//        String pilihan = o.toString();
        String pilihan = this.getListAdapter().getItem(position).toString();
        // Mencek pilihan, apabila pilihan = Exit maka akan keluar dari aplikasi
        if (pilihan.equals("Kembali")) {
            finish();
        } else {// pilihan != exit maka akan memanggil method 
            callpelayanan(pilihan);
        }
    }
    /**
     * Launches the activity to make phone call to taxi provider based on
     * selected taxi
     *
     */
    protected void callpelayanan(String pilihan) {
        try {
            // Intent digunakan untuk sebagai pengenal suatu activity untuk
            // membuat panggilan telepon
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            String phonenumber = "";
            for (int i = 0; i < pelayan_data.length; i++) {
                if (pilihan.equals(pelayan_data[i][0])) {
                    phonenumber = pelayan_data[i][1];
                }
            }
            if (phonenumber.equals("")) {
                Toast.makeText(this, "Provider  is not register",
                        Toast.LENGTH_LONG).show();
                return;
            }
            callIntent.setData(Uri.parse("tel:" + phonenumber));
            startActivity(callIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
