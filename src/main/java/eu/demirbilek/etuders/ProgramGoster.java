package eu.demirbilek.etuders;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Hashtable;
public class ProgramGoster extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_program_goster);
        Intent intent=getIntent();

        //Hashtable e=(Hashtable) intent.getSerializableExtra("hashtable");
    }


    public void printProgram(){

    }

}
