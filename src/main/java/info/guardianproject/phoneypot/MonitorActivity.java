
/*
 * Copyright (c) 2017 Nathanial Freitas / Guardian Project
 *  * Licensed under the GPLv3 license.
 *
 * Copyright (c) 2013-2015 Marco Ziccardi, Luca Bonato
 * Licensed under the MIT license.
 */
package info.guardianproject.phoneypot;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.FragmentActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import info.guardianproject.phoneypot.service.MonitorService;

public class MonitorActivity extends FragmentActivity {
	
	private PreferenceManager preferences = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		preferences = new PreferenceManager(getApplicationContext());

		setContentView(R.layout.layout_running);


        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                startService(new Intent(MonitorActivity.this, MonitorService.class));

            }
        }, 10 * 1000);

	}


	
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.monitor_activity, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected (MenuItem item) {
	    switch (item.getItemId()){
	          case R.id.menu_settings:
				  close();
	        	  break;
	    }
	    return true;
    }
    
    /**
     * Closes the monitor activity and unset session properties
     */
    private void close() {
    	Intent intent = new Intent(
  			  getApplicationContext(), ListActivity.class);
  	  intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
  	  startActivity(intent);
  	  stopService(new Intent(this, MonitorService.class));
  	  preferences.unsetAccessToken();
  	  preferences.unsetDelegatedAccessToken();
  	  preferences.unsetPhoneId();
    	
    }
    
    /**
     * When user closes the activity
     */
    @Override
    public void onBackPressed() {
		close();
    }
    

}
