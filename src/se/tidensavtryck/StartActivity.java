package se.tidensavtryck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StartActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button button = (Button) findViewById(R.id.button_route_info);
        button.setOnClickListener(new OnClickListener() {
            
            @Override
            public void onClick(View v) {
                Intent i = new Intent(StartActivity.this, RouteInfoActivity.class);
                startActivity(i);
            }
        });
    }
}