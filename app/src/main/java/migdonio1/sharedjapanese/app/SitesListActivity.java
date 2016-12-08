package migdonio1.sharedjapanese.app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import migdonio1.sharedjapanese.R;
import migdonio1.sharedjapanese.endpoints.SitesApiInterface;
import migdonio1.sharedjapanese.models.Site;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static migdonio1.sharedjapanese.data.APIConstants.API_ENDPOINT;
import static migdonio1.sharedjapanese.data.APIConstants.SITE;

public class SitesListActivity extends AppCompatActivity {

    private ListView sitesListView;

    private List<Site> sites;

    private SitesApiInterface apiSites;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sites_list);

        sitesListView = (ListView) findViewById(R.id.siteslistView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiSites = retrofit.create(SitesApiInterface.class);

        getSites();
    }

    public void getSites() {
        intent = new Intent(this, SiteDetailActivity.class);
        Call<List<Site>> call = apiSites.SitesList();
        call.enqueue(new Callback<List<Site>>() {
            @Override
            public void onResponse(Call<List<Site>> call, Response<List<Site>> response) {
                sites = response.body();
                Log.d("HOLIS", String.valueOf(sites.size()));

                sitesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                        Site site = sites.get(i);



                        Log.d("ioloo1", site.getTranslates().get(0));
                        intent.putExtra(SITE.ORIGINAL, site.getOriginal());
                        intent.putExtra(SITE.SYLLABLES, site.getSyllables());
                        intent.putExtra(SITE.TRANSLATES, site.getTranslates().get(0));
                        Log.d("ioloo45",intent.getStringExtra(SITE.TRANSLATES));
                        intent.putExtra(SITE.COUNTRY, site.getCountry());
                        intent.putExtra(SITE.DESCRIPTION, site.getDescription());
                        intent.putExtra(SITE.LATITUDE, site.getPosition().getLatitude());
                        intent.putExtra(SITE.LONGITUDE, site.getPosition().getLongitude());

                        startActivity(intent);
                    }
                });

                List<String> sitesSimpleListItem = new ArrayList<String>();
                for(int i = 0; i < sites.size(); i++) {
                    String siteSimpleListItem = sites.get(i).getTranslates().get(0) + " (" + sites.get(i).getOriginal() + ")";
                    sitesSimpleListItem.add(siteSimpleListItem);
                }

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                        getApplicationContext(),
                        R.layout.site_list_item,
                        sitesSimpleListItem
                );

                sitesListView.setAdapter(arrayAdapter);
            }

            @Override
            public void onFailure(Call<List<Site>> call, Throwable t) {
                Toast.makeText(SitesListActivity.this, "Error: El servidor no responde, intentelo mas tarde", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
