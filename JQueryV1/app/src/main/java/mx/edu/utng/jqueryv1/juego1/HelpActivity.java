package mx.edu.utng.jqueryv1.juego1;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebView;

import mx.edu.utng.jqueryv1.R;

public class HelpActivity extends Activity {
	private WebView mWebView;

	/**
	 * @see Activity#onCreate(Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.help);

	    mWebView = (WebView) findViewById(R.id.help);
	    mWebView.getSettings().setJavaScriptEnabled(true);
	    mWebView.loadUrl("http://dl.dropbox.com/u/30081077/index.html");

	}
	

}
