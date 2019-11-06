package com.loterh.robert.notekeeper;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.List;

public class NoteActivity extends AppCompatActivity {
    public static final String NOTE_INFO = "com.loterh.robert.NoteTaker.NOTE_INFO";
    private NoteInfo mNoteInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Spinner spinnerTopics = (Spinner) findViewById(R.id.spinner_topics);
        List<TopicInfo> topics = DataManager.getInstance().getTopics();
        ArrayAdapter<TopicInfo> adapterTopics = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, topics);
        spinnerTopics.setAdapter(adapterTopics);

        readDisplayStateValues();
        EditText textNoteTitle = findViewById(R.id.text_note_title);
        EditText textNoteText = findViewById(R.id.text_note_text);

        displayNote(spinnerTopics, textNoteTitle, textNoteText);


    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        mNoteInfo = intent.getParcelableExtra(NOTE_INFO);
    }

    private void displayNote(Spinner spinnerTopics, EditText textNoteTitle, EditText textNoteText) {
        List<TopicInfo> topics = DataManager.getInstance().getTopics();
        int topicIndex = topics.indexOf(mNoteInfo.getTopic());
        spinnerTopics.setSelection(topicIndex);
        textNoteTitle.setText(mNoteInfo.getTitle());
        textNoteText.setText(mNoteInfo.getText());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
