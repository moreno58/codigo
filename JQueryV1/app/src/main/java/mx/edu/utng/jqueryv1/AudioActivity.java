package mx.edu.utng.jqueryv1;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Jeanette Moreno on 03/04/2016.
 */
public class AudioActivity extends ActionBarActivity {
    private WebView view; // Crear una vista de variables para ganar método de acceso onKeyDown

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.audio_activity);

        view = (WebView) this.findViewById(R.id.webViewAudio);
        view.getSettings().setJavaScriptEnabled(true);
        view.setWebViewClient(new MyBrowser());
        view.loadUrl("https://soundcloud.com/search?q=jquery%20mobile"); //try js alert
        view.setWebChromeClient(new WebChromeClient()); // adding js alert support
    }

    private class MyBrowser extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //ketika disentuh tombol back
        if ((keyCode == KeyEvent.KEYCODE_BACK) && view.canGoBack()) {
            view.goBack(); //metodo goback() ejecutado para volver a la página anterior
            return true;
        }
        // Si no hay antecedentes (página abierta anteriormente)
        // Se saldrá de la actividad
        return super.onKeyDown(keyCode, event);
    }

}
