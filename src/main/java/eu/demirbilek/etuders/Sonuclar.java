package eu.demirbilek.etuders;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;


import java.io.Serializable;
import java.util.Hashtable;

public class Sonuclar extends Activity implements Serializable{
    String[] saatler={" 8.30-9.20"," 9.30-10.20","10.30-11.20","11.30-12.20","12.30-13.20","13.30-14.20","14.30-15.20","15.30-16.20","16.30-17.20","17.30-18.20","18.30-19.20","19.30-20.20"};
    //String[] saatler={"8.30","9.30","10.30","11.30","12.30","13.30","14.30","15.30","16.30","17.30","18.30","19.30"};
    Hashtable e= jsoup.e;
    public void init(Program[] kord, Hashtable e) {


        TableLayout stk = (TableLayout) findViewById(R.id.table_main);
        for(int c=0;c<kord.length;c++){
            TableRow tbrow2 = new TableRow(this);
            TextView tvv=new TextView(this);
            tvv.setText("Program #"+(c+1));
            tbrow2.addView(tvv);
            stk.addView(tbrow2);

            TableRow tbrow0 = new TableRow(this);
            TextView tv0 = new TextView(this);
            tv0.setText("              ");
            tv0.setTextColor(Color.WHITE);
            tbrow0.addView(tv0);
            TextView tv1 = new TextView(this);
            tv1.setText(" Pazartesi              ");
            tv1.setTextColor(Color.WHITE);
            tbrow0.addView(tv1);

            TextView tv2 = new TextView(this);
            tv2.setText(" Salı                   ");
            tv2.setTextColor(Color.WHITE);
            tbrow0.addView(tv2);

            TextView tv3 = new TextView(this);
            tv3.setText(" Çarşamba               ");
            tv3.setTextColor(Color.WHITE);
            tbrow0.addView(tv3);
            TextView tv4 = new TextView(this);
            tv4.setText(" Perşembe               ");
            tv4.setTextColor(Color.WHITE);
            tbrow0.addView(tv4);
            TextView tv5 = new TextView(this);
            tv5.setText(" Cuma                   ");
            tv5.setTextColor(Color.WHITE);
            tbrow0.addView(tv5);
            TextView tv6 = new TextView(this);
            tv6.setText(" Cumartesi              ");
            tv6.setTextColor(Color.WHITE);
            tbrow0.addView(tv6);


            stk.addView(tbrow0);
            kord[c].arrayAktar();

            for (int i = 0; i < 12; i++) {

                TableRow tbrow = new TableRow(this);
                TextView t1v = new TextView(this);
                t1v.setText(saatler[i]);
                t1v.setTextColor(Color.WHITE);
                t1v.setGravity(Gravity.RIGHT);
                tbrow.addView(t1v);


                TextView[] tv = new TextView[7];
                for(int j=0;j<6;j++){
                    //System.out.println(c+" "+i+" "+j);
                    if(kord[c].kord[i][j].equals("")){
                        tv[j] = new TextView(this);
                        tv[j].setText("|            -           ");
                        tv[j].setTextColor(Color.WHITE);
                        tv[j].setGravity(Gravity.LEFT);
                        tbrow.addView(tv[j]);
                    }
                    else {
                        String tmp = new String(kord[c].kord[i][j]);
                        String tmp2;


                        tmp2 = tmp.split(" ")[0];
                        String tmp3;
                        if (tmp.split(" ").length > 2) {
                            String tmp4 = tmp.split(" ")[2];
                            tmp3 = e.get(Integer.parseInt(tmp2)).toString().split(" ")[0] + " " + e.get(Integer.parseInt(tmp2)).toString().split(" ")[1];
                            tmp3 = tmp3.concat(tmp.substring(tmp.indexOf(" "), tmp.indexOf(" ") + tmp.substring(tmp.indexOf(" ") + 1).indexOf(" ") + 1));
                            tmp3 = tmp3 + " " + e.get(Integer.parseInt(tmp4)).toString().split(" ")[0] + " " + e.get(Integer.parseInt(tmp4)).toString().split(" ")[1];
                            tmp3 = tmp3 + " " + tmp.substring(tmp.indexOf(" ") + tmp.substring(tmp.indexOf(" ") + 1).indexOf(" ") + 1 + 1 + tmp.substring(tmp.indexOf(" ") + tmp.substring(tmp.indexOf(" ") + 1).indexOf(" ") + 1 + 1).indexOf(" ") + 1);
                        } else {
                            tmp3 = e.get(Integer.parseInt(tmp2)).toString().split(" ")[0] + " " + e.get(Integer.parseInt(tmp2)).toString().split(" ")[1];
                            tmp3 = tmp3.concat(tmp.substring(tmp.indexOf(" ")));
                        }

                        // Ders adi e.get(Integer.parseInt(tmp2));
                        //Sinif     tmp.substring(tmp.indexOf(" "));

                        //tmp3=tmp3.concat(tmp.substring(tmp.indexOf(" ")));
                        //tmp3=tmp3.concat(tmp.substring(tmp.indexOf(" "), tmp.substring(tmp.indexOf(" ")+1).indexOf(" "))+" ");
                        //tmp3=tmp3+e.get(Integer.parseInt(tmp4)).toString().split(" ")[0]+ " "+e.get(Integer.parseInt(tmp4)).toString().split(" ")[1];
                        //System.out.print(tmp3);
                        if (tmp3.length() < 23) {
                            for (int x = 0; x < 23 - tmp3.length(); x++) {
                                tmp3=tmp3+" ";
                            }
                        }
                        tmp3="|"+tmp3;
                        tv[j] = new TextView(this);
                        tv[j].setText(tmp3);
                        tv[j].setTextColor(Color.WHITE);
                        tv[j].setGravity(Gravity.LEFT);
                        tbrow.addView(tv[j]);
                    }


                }


                stk.addView(tbrow);
            }
            TableRow tbrow3 = new TableRow(this);
            TextView tvvv=new TextView(this);
            tvvv.setText(" ");
            tbrow3.addView(tvvv);
            stk.addView(tbrow3);

        }

    }

    @Override
    protected void onStart(){
        super.onStart();
        setContentView(R.layout.activity_sonuclar);
        int [] dersler=getIntent().getIntArrayExtra("dersler");
        Ders[] ders=jsoup.dersleriOlustur(dersler);
        Program[] tmp= jsoup.programOlustur(ders);
        Program[] tmp2=jsoup.sifirCakisma(tmp);
        //Program[] tmp2=ikiCakisma(tmp);
        if(tmp2[0].name.equals("bos")){
            tmp2=jsoup.ikiCakisma(tmp);
            if(tmp2[0].name.equals("bos")){
                tmp2=jsoup.ucCakisma(tmp);
                if(tmp2[0].name.equals("bos")){
                    tmp2=jsoup.dortCakisma(tmp);
                }
            }
        }
        init(tmp2,e);
        /*
        final Program[] tmp3=tmp2;

        View.OnClickListener a=new View.OnClickListener() {

            public void onClick(View v) {
                final
                Intent program= new Intent(Sonuclar.this, ProgramGoster.class);
                tmp3[v.getId()].arrayAktar();
                program.putExtra("program",tmp3[v.getId()]);
                startActivity(program);

            }
        };*/

        /*
        LinearLayout ll = (LinearLayout)findViewById(R.id.buttonlayoutsonuc);
        Button[] button= new Button[tmp2.length];
        for(int i = 0; i<tmp2.length;i++){
            button[i]= new Button(this);
            button[i].setId(i);
            button[i].setText("Program #"+(i+1));
            button[i].setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            ll.addView(button[i]);
            button[i].setOnClickListener(a);


        }*/


    }
}
