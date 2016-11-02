package com.example.hosneara.project1;

/**
 * Created by hosneara on 11/1/16.
 */

public class issue {
    private String bookname = "";
    private int numberOfBooks = 0;
    issue()
    {

    }
    public String getBookname()
    {
        return bookname;
    }
    public void setBookname(String bookname)
    {
        numberOfBooks++;
        this.bookname = bookname;
    }


}
