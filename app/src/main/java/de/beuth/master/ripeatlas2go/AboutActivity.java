/*
 * Copyright (C) 2019 SillyMoto authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.beuth.master.ripeatlas2go;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;

/**
 * <h1>About Activity!</h1>
 * <p>
 * This activity gives you an overview of the app.
 * It references to the ripe atlas website:
 * <a href="https://atlas.ripe.net/about">https://atlas.ripe.net/about</a>
 * and to the ripe atlas api:
 * <a href="https://atlas.ripe.net/docs/api/v2/manual">https://atlas.ripe.net/docs/api/v2/manual</a>
 *
 * @author  Sarah Kommorovski
 * @version 1.0
 * @since   2019-09-30
 */
public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        TextView view = findViewById(R.id.textView1);
        view.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        view = findViewById(R.id.textView2);
        view.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);

    }
}
