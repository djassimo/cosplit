package com.example.djamel.cosplit;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.BackendlessCollection;
import com.backendless.BackendlessUser;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.async.callback.BackendlessCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.persistence.BackendlessDataQuery;
import com.backendless.persistence.BackendlessSerializer;
import com.backendless.persistence.QueryOptions;
import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.TypefaceProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

//password backendless :cosplit2017
public class HomePage extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static final String APP_ID = "96BF9E34-383E-89AC-FFCC-8031E93B2400";
    public static final String SECRET_KEY = "279243A4-BDBD-317E-FFB9-BF9298751000";
    public static final String VERSION = "v1";

    //BootstrapButton button3, button2;
    String nomhouse, idobj, nomuser, idusers, u;
    RelativeLayout layout = null;
    int codeHome, code1, codeall;
    ListView listView;
    TableCodeMaison tableCodeMaison = new TableCodeMaison();
    BackendlessUser backendlessUser = new BackendlessUser();

    protected void onCreate(Bundle savedInstanceState) {
        Backendless.initApp(this, APP_ID, SECRET_KEY, VERSION);
        super.onCreate(savedInstanceState);

        TypefaceProvider.registerDefaultIconSets();
        setContentView(R.layout.home_activity_page);

        AddCostMenuFragment addcost = new AddCostMenuFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.home_activity_page,addcost).commit();

        listView = (ListView) findViewById(android.R.id.list);

        BackendlessUser curentuser = Backendless.UserService.CurrentUser();
        if (curentuser != null) {
            nomuser = curentuser.getProperty("name").toString();
        }

        final String[] names = new String[]{nomuser};
        final int[] images = {R.drawable.user};

        ArrayAdapter<String> adapter = new
                ArrayAdapter<String>(HomePage.this,
                        R.layout.row_layout, names) {

                    @Override
                    public View getView(int position, View convertView, ViewGroup parent) {

                        if (convertView == null) {
                            convertView = LayoutInflater.from(getContext()).
                                    inflate(R.layout.row_layout, parent, false);
                        }

                        ((ImageView) convertView.findViewById(R.id.imageView)).setImageResource(images[position]);

                        ((TextView) convertView.findViewById(R.id.textViewName)).setText(names[position]);

                        return convertView;
                    }
                };
        listView.setAdapter(adapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position,
                                    long id) {
                /*Intent intent = new Intent(HomePage.this, HomePage.class);
                startActivity(intent);
                Toast.makeText(HomePage.this, "le code home aaa !!", Toast.LENGTH_LONG).show();*/

                String cat = (String) parent.getItemAtPosition(position);
            }
        });


        //navigationView
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        RetrieveData();
       // RetrieveUsers();

    }

    // methode de recuperation des donnees des tables
    public void RetrieveData() {

        //recupere id de current user :
        BackendlessUser curentuser = Backendless.UserService.CurrentUser();
        if (curentuser != null) {
            idobj = curentuser.getUserId();
            String idalluserss = backendlessUser.getUserId();

            AsyncCallback<BackendlessCollection<TableCodeMaison>> callback = new AsyncCallback<BackendlessCollection<TableCodeMaison>>() {
                @Override
                public void handleResponse(BackendlessCollection<TableCodeMaison> response) {
                    Iterator<TableCodeMaison> iterator = response.getCurrentPage().iterator();

                    while (iterator.hasNext()) {

                        final TableCodeMaison tableCode = iterator.next();
                        codeall = tableCode.getIdhouse();

                        if (idobj.equals(tableCode.getIduser())) {
                            codeHome = tableCode.getIdhouse();
                        }

                        idusers = tableCode.getIduser();

                        Backendless.Data.of(BackendlessUser.class).find
                                (new AsyncCallback<BackendlessCollection<BackendlessUser>>() {
                                     @Override
                                     public void handleResponse(BackendlessCollection<BackendlessUser> users) {
                                         Iterator<BackendlessUser> userIterator = users.getCurrentPage().iterator();

                                         while (userIterator.hasNext()) {
                                             BackendlessUser user = userIterator.next();

                                             if (codeHome == codeall) {
                                                 String nameuser = user.getProperty("name").toString();
                                             }
                                         }
                                     }

                                     @Override
                                     public void handleFault(BackendlessFault backendlessFault) {
                                         System.out.println("Server reported an error - " + backendlessFault.getMessage());
                                     }
                                 }
                                );
                    }

                    AsyncCallback<BackendlessCollection<TableCode>> callback1 = new AsyncCallback<BackendlessCollection<TableCode>>() {
                        @Override
                        public void handleResponse(BackendlessCollection<TableCode> response) {

                            Iterator<TableCode> iterator = response.getCurrentPage().iterator();

                            while (iterator.hasNext()) {
                                TableCode tableCode = iterator.next();

                                if (codeHome == (tableCode.getCode())) {
                                    code1 = tableCode.getCode();
                                    nomhouse = tableCode.getNomhouse();
                                }
                            }
                            if (nomhouse.toString() != "") {
                                TextView textview = (TextView) findViewById(R.id.textView6);
                                textview.setText(nomhouse);
                            }
                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                        }
                    };
                    Backendless.Data.of(TableCode.class).find(callback1);
                    //Toast.makeText(HomePage.this, "le code home aaa !!"+codeHome, Toast.LENGTH_LONG).show();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                }
            };
            Backendless.Data.of(TableCodeMaison.class).find(callback);
        }

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_ask_for_money) {
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/html");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("Bonjour,\n " +
                    "J'ai besoin de 100 €:" ));
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        } else if (id == R.id.nav_edit_profile) {
            Intent intent = new Intent(HomePage.this, EditProfilePage.class);
            startActivity(intent);

        } else if (id == R.id.nav_transaction) {

        } else if (id == R.id.nav_log_out) {
            Backendless.UserService.logout(new AsyncCallback<Void>() {

                @Override
                public void handleResponse(Void response) {

                    Intent intent = new Intent(HomePage.this, MainPage.class);
                    startActivity(intent);
                    Toast.makeText(HomePage.this, "Déconnecté:", Toast.LENGTH_LONG).show();
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                    Toast.makeText(HomePage.this, "erreur déconnection:", Toast.LENGTH_LONG).show();
                }
            });

        } else if (id == R.id.nav_add_cost) {
            Intent intent = new Intent(HomePage.this, AddCost.class);
            startActivity(intent);

        } else if (id == R.id.nav_share) {
            String codee;
            codee = Integer.toString(codeHome);
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/html");
            sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, Html.fromHtml("Bienvenue sur notre application " +
                    "Co$plit voilà votre code pour rejoindre la maison en colocation" + " " + nomhouse + " votre code est :" + codee));
            startActivity(Intent.createChooser(sharingIntent, "Share using"));

        } else if (id == R.id.nav_send) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void RetrieveUsers() {

        QueryOptions queryOptions = new QueryOptions();
        BackendlessDataQuery query = new BackendlessDataQuery(queryOptions);
        String whereClause = nomhouse;
        BackendlessDataQuery dataQuery = new BackendlessDataQuery();
        dataQuery.setWhereClause(whereClause);


// Asynchronous API:
// ***********************************************************
        Backendless.Persistence.of("TableCodeMaison").find(dataQuery,
                new AsyncCallback<BackendlessCollection<Map>>() {
                    @Override
                    public void handleResponse(BackendlessCollection<Map> foundusers) {

                        int x = foundusers.getData().size();

                        u = tableCodeMaison.getIduser();
                        Toast.makeText(HomePage.this, "la table est  !!" + x, Toast.LENGTH_LONG).show();

                        // every loaded object from the "Contact" table is now an individual java.util.Map
                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {
                        // an error has occurred, the error code can be retrieved with fault.getCode()
                    }
                });

        /*


        BackendlessUser curentuser = Backendless.UserService.CurrentUser();
        if (curentuser != null) {
            idobj = curentuser.getUserId();

            AsyncCallback<BackendlessCollection<TableCodeMaison>> callback = new AsyncCallback<BackendlessCollection<TableCodeMaison>>() {
                @Override
                public void handleResponse(BackendlessCollection<TableCodeMaison> response) {
                    Iterator<TableCodeMaison> iterator = response.getCurrentPage().iterator();

                    while (iterator.hasNext()) {

                        final TableCodeMaison tableCode = iterator.next();
                        //idusers = tableCode.getIduser();
                        codeall = tableCode.getIdhouse();

                        if (idobj.equals(tableCode.getIduser())) {
                            codeHome = tableCode.getIdhouse();
                        }
                        //Toast.makeText(HomePage.this, "iduser!!" + codeHome, Toast.LENGTH_LONG).show();
                    }
                }

                @Override
                public void handleFault(BackendlessFault fault) {
                }
            };
            Backendless.Data.of(TableCodeMaison.class).find(callback);
        }
        Toast.makeText(HomePage.this, "iduser!!" + codeHome, Toast.LENGTH_LONG).show();
return codeHome;
    */
    }


}
