package comkoreannick.github.pknu_libstat;

import android.app.Activity;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        new T().execute(null, null, null);

    }
}

class T extends AsyncTask<Void, Void, Void> {

    String[] ids = new String[4000];
    String[] ids_info= new String[32000];
    @Override
    protected Void doInBackground(Void... params) {

        try {
            int room_number =0;
            int i = 0;
            int j = 0;
            Document document = Jsoup.connect("http://210.125.122.79/new/roomview5.asp?room_no="+room_number).get();

            Elements elements = document.select("th.thl");
            Elements elements2 = document.select("td").not("td.tdl");
            add_schedule.subjectAdapter.dateRemove();
            for(Element e2 : elements2) {
                ids_info[j] = e2.text();
                j++;
            }
            for(Element e : elements) {
                ids[i] = e.text();
                add_schedule.subjectAdapter.addItem(ids[i],ids_info[i*8],ids_info[i*8+1],ids_info[i*8+2],ids_info[i*8+3],ids_info[i*8+4],ids_info[i*8+5],ids_info[i*8+6],ids_info[i*8+7]);
                i++;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void result) {
        super.onPostExecute(result);
        add_schedule.subjectAdapter.dataChange();
    }


}