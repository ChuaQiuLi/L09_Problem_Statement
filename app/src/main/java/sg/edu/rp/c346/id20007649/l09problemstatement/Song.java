package sg.edu.rp.c346.id20007649.l09problemstatement;

import java.io.Serializable;

public class Song implements Serializable {

    private int id;
    private String title;
    private String singers;
    private int year;
    private int stars;


    public Song  (String title, String singers,int year, int stars ) {
        this.title = title;
        this.singers = singers;
        this.year = year;
        this.stars = stars;
    }


    public int getId() {
        return id;

    }

    public String getTitle() {
        return title;

    }

    public String getSingers() {
        return singers;

    }

    public int getYear() {
        return  year;

    }

    public int getStars() {
        return stars;

    }

    public void setTitleContent(String title) {

        this.title = title;
    }

    public void setSingersContent(String singers) {

        this.title = singers;
    }

    public void setYearContent(int year) {
        this.year = year;
    }

    public void setSongContent(int stars) {
        this.stars = stars;
    }

    @Override
    public String toString() { return year + "\n" + title + "\n" + singers +  " - " + year + "\n" + stars;

    }





}
