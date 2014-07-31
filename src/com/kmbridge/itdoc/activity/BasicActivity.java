package com.kmbridge.itdoc.activity;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.kmbridge.itdoc.R;

public class BasicActivity extends ActionBarActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_basic);
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		
	}
	
	//onCreateOptionsMenu는 말그대로 actionBar에서 옵션메뉴를 추가할때  MenuInflater를 이용해서 생성하는 부분
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_basic_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

    
    /* Called whenever we call invalidateOptionsMenu() */
    // This is called right before the menu is shown, every time it is shown.
    //menu:  The options menu as last shown or first initialized by onCreateOptionsMenu(). 
    //onCreateOptionsMenu가 수행되고 바로 실행되는 메서드라 생각하자
    //이 메서드는 drawer화면이 전환될 때마다 수행됨 -> 그 이유가 토글에서 invalidateOptionsMenu()를 실행했기 때문임.
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        // If the nav drawer is open, hide action items related to the content view
        //boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);	//안에 들어있는 listview가 보여지는지로 판단하네
        //menu.findItem(R.id.action_search).setVisible(!drawerOpen);	//drawer가 닫혀있으면 안보이지 
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch(item.getItemId()) {
        case android.R.id.home:
        	NavUtils.navigateUpFromSameTask(this);
            return true;
        	
    	}
         // The action bar home/up action should open or close the drawer.
         // ActionBarDrawerToggle will take care of this.
        /*if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        
        // Handle action buttons
        //아이템 아이디를 받아서 select가 되었을때 코드를 넣네
        switch(item.getItemId()) {
        case R.id.action_search:
            // create intent to perform web search for this planet
            
        	Intent intent = new Intent(Intent.ACTION_WEB_SEARCH);
            intent.putExtra(SearchManager.QUERY, getActionBar().getTitle());
            // catch event that there's no activity to handle intent
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            } else {
                Toast.makeText(this, R.string.app_not_available, Toast.LENGTH_LONG).show();
            }
            
            return true;
        default:
            return super.onOptionsItemSelected(item);
        }*/
    return super.onOptionsItemSelected(item);
    }


}
