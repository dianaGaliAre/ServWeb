package utng.edu.mx.dgalido.networking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    ImageView img;
    TextView txtTexo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ImageView img=(ImageView)findViewById(R.id.img);
        TextView.makeText(getBaseContext(), result, )
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //new DownloadImageTask().execute("http://www.mayoff.com/5-01cablecarDCP01934.jpg");
    }
    private String downloadText(String url){

    final int BUFFER_SIZE= 2000;
        InputStream in= null;
        try {
        in= openHttpConnection(url);
        }catch (IOException e){
            Log.d("Networking", e.getLocalizedMessage());
            return "";
        }
        InputStreamReader isr= new InputStreamReader(in);
        int charRead;
        String str="";
        char[] inputBuffer= new char[BUFFER_SIZE];
        try{
            while ((charRead= isr.read(inputBuffer))>0){
                String reaString= String.copyValueOf(inputBuffer, 0, charRead);
                str+= reaString;
                inputBuffer= new char[BUFFER_SIZE];
            }
            in.close();
        }catch (IOException e){
            Log.d("NETWORKING", e.getLocalizedMessage());
            return "";
        }
        return str;

        }
    }


    private InputStream openHttpConnection(String urlString) throws IOException {
        InputStream in = null;
        int response = -1;

        URL url = new URL(urlString);
        URLConnection con = url.openConnection();
        //verificamos a que clase pertenece el objeto de conexion

        if (!(con instanceof HttpURLConnection)) {
            //si no pertenece lanzamos una exepcion
            throw new IOException("Not an HTTP Connection");
            try {
                HttpURLConnection httpCon = (HttpURLConnection) con;
                httpCon.setAllowUserInteraction(false);//Interaccion con el usuario
                httpCon.setInstanceFollowRedirects(true);//redireccionamiento
                httpCon.setRequestMethod("GET");
                httpCon.connect();
                response = httpCon.getResponseCode();//captura la respuesta

                if (response == HttpURLConnection.HTTP_OK) {
                    in = httpCon.getInputStream();
                }
            } catch (Exception e) {
                Log.d("Networking", e.getLocalizedMessage());
                throw new IOException("ERROR CONNECTING");

            }
            return in; //retorno de flujo de entrada
        }
        private Bitmap downloadImage(String url){
        Bitmap bitmap= null;
        InputStream in= null;
        try{
            in=openHttpConnection(url);
            bitmap= BitmapFactory.decodeStream(in);
            in.close();
        }catch (IOException e) {
            Log.d("MainActivity", e.getLocalizedMessage());
        }
            return bitmap;
        }
    private class DownloadText extends AsyncTask<String,Void,String>{
        protected String doInBackground(String... urls){
            return downloadText(url[0]);
        }
        }
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap>{
        protected Bitmap doInBackground(String... urls){
            return downloadImage(urls[0]);
        }
        protected void onPostExecute(Bitmap result){
            ImageView img= (ImageView)findViewById(R.id.img);
            img.setImageBitmap(result);


        }
    }

    }

