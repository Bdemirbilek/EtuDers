package eu.demirbilek.etuders;

        import android.app.Activity;
        import android.content.Intent;
        import android.os.StrictMode;
        import android.view.View;
        import android.widget.Button;
        import android.widget.CheckBox;
        import android.widget.LinearLayout;

        import org.jsoup.Connection;
        import org.jsoup.Jsoup;
        import org.jsoup.nodes.Document;
        import org.jsoup.nodes.Element;
        import org.jsoup.select.Elements;

        import java.io.ByteArrayInputStream;
        import java.io.IOException;
        import java.util.ArrayList;
        import java.util.Hashtable;

public class DersListesi extends Activity {




    CheckBox[] checkbox=null;
    Hashtable e;
    public Hashtable getHashtable(){
        return e;
    }
    @Override
    protected void onStart() {
        super.onStart();
        setContentView(R.layout.activity_ders_listesi);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        e=dersListele(checkbox);
        jsoup.dersListele();

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ArrayList<Integer> dersler=new ArrayList<Integer>();
                int sayac=0;
                for(int x=0;x<checkbox.length;x++){
                    if(checkbox[x].isChecked()){
                        dersler.add(sayac,Integer.parseInt(e.get(x).toString()));
                        sayac++;
                    }
                }
                Intent intent= new Intent(DersListesi.this, Sonuclar.class);
                intent.putExtra("method",true);
                intent.putExtra("hashtable", e);

                int[] ints = new int[dersler.size()];
                for(int i=0, len = dersler.size(); i < len; i++)
                    ints[i] = dersler.get(i);




                intent.putExtra("dersler",ints);
                startActivity(intent);
                // Perform action on click
            }
        });

    }
    public Hashtable<Integer, String> dersListele(CheckBox[] checkbox) {

        LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayout);

        Hashtable<Integer, String> hashtable = new Hashtable<Integer, String>();
        Document doc = null;
        try {
            // doc =
            // Jsoup.connect("http://kayit.etu.edu.tr/Ders/_Ders_prg_start.php").get();
            String requestUrl = "http://kayit.etu.edu.tr/Ders/_Ders_prg_start.php";
            Connection connection = Jsoup.connect(requestUrl).method(Connection.Method.GET);
            Connection.Response response = connection.execute();
            doc = Jsoup.parse(new ByteArrayInputStream(response.bodyAsBytes()), "ISO8859-9", requestUrl);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // String title = doc.title();
        // System.out.println(title);

        Elements options = null;
        if (doc != null) {
            options = doc.getElementsByAttributeValue("name", "dd_ders").get(0).children();
        }
        if (options != null) {
            DersListesi.this.checkbox= new CheckBox[options.size()];
        }
        int count=0;
        for (Element option : options) {
            DersListesi.this.checkbox[count]= new CheckBox(this);
            DersListesi.this.checkbox[count].setText(option.text().toString());
            DersListesi.this.checkbox[count].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.addView(DersListesi.this.checkbox[count]);
            //System.out.println(option.val() + "   " + option.text().toString());
            hashtable.put(Integer.parseInt(option.val()), option.text().toString());
            //reverse.put(option.text().toString(),Integer.parseInt(option.val()));
            hashtable.put(count,option.val());
            count++;
        }
        return hashtable;
    }
}