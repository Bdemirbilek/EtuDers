package eu.demirbilek.etuders;

import android.app.Activity;
import android.content.Intent;

public class YuklemeEkrani extends Activity {




    protected void onStart() {
        super.onStart();
        Intent intent= getIntent();
        if(!intent.hasExtra("method")){
            startActivity(new Intent(this,DersListesi.class));
        }
        else {
            int [] dersler=intent.getIntArrayExtra("dersler");

            Intent sonuclar= new Intent(this,Sonuclar.class);
            sonuclar.putExtra("deresler", dersler);
            startActivity(sonuclar);

        }
    }
}