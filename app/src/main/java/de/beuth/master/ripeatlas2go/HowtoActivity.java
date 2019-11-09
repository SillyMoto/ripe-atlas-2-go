package de.beuth.master.ripeatlas2go;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.lang.annotation.Annotation;

import de.beuth.master.classes.Credit;
import de.beuth.master.classes.Measurement;

public class HowtoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howto);
        // clickable API Key
        TextView textView = findViewById(R.id.howto_text2);
        makeSpannableTextView(textView, 9, 17, ApiKeysActivity.class);
        // clickable Credits
        textView = findViewById(R.id.howto_credit_text1);
        makeSpannableTextView(textView, 9, 16, CreditActivity.class);
        // clickable Credits
        textView = findViewById(R.id.howto_msm_text1);
        makeSpannableTextView(textView, 9, 21, MeasurementActivity.class);
    }

    private void makeSpannableTextView(TextView textView, int startChar, int stopChar, final Class intentClass){
        SpannableString spannableString = new SpannableString(textView.getText());
        spannableString.setSpan(new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                Intent i = new Intent(HowtoActivity.this, intentClass);
                startActivity(i);
            }
        }, startChar, stopChar, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(spannableString);
        textView.setMovementMethod(LinkMovementMethod.getInstance());
    }
}
