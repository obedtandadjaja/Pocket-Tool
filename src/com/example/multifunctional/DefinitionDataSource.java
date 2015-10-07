package com.example.multifunctional;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class DefinitionDataSource {

    private SQLiteDatabase database;
    private DataBaseHelper helper;
    private final String DB_NAME;
    private Context context;

    /**
     * CategoryDataSource constructor
     * initialize database
     * @param context
     */
    public DefinitionDataSource(Context context)
    {
        this.DB_NAME = "dictionary.sqlite";
        this.helper = new DataBaseHelper(context, this.DB_NAME);
        this.database = helper.openDataBase();
        this.context = context;
    }

    /**
     * Get a dialect based on its native name.
     * @param name
     * @return
     */
    public ArrayList<Definition> getDefinition(String word)
    {
        Cursor cursor = database.rawQuery("select type, definition from DEFINITIONS where wordID = (select wordID from WORDS where lower(word) = \""+word.toLowerCase()+"\");", null);
        cursor.moveToFirst();
        ArrayList<Definition> def = cursorToCategory(cursor);
        return def;
    }
    
    public ArrayList<String> getRelatedWords(String word)
    {
    	Cursor cursor = database.rawQuery("select word from WORDS where lower(word) like (\"%"+word+"%\");", null);
    	cursor.moveToFirst();
    	ArrayList<String> word_array = new ArrayList<String>();
    	for(int i = 0; i < cursor.getCount(); i++)
    	{
    		String w = cursor.getString(0);
    		word_array.add(w);
    		cursor.moveToNext();
    	}
    	return word_array;
    }

    /**
     * private method to get the query data into its format
     * @param cursor
     * @param dialect
     * @return
     */
    private ArrayList<Definition> cursorToCategory(Cursor cursor)
    {
    	ArrayList<Definition> def_array = new ArrayList<Definition>();
    	for(int i = 0; i < cursor.getCount(); i++)
    	{
    		String type = cursor.getString(0);
        	String def = cursor.getString(1);
        	def_array.add(new Definition(type, def));
        	cursor.moveToNext();
    	}
    	return def_array;
    }
}
