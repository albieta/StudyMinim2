package edu.upc.dsa.andoroid_dsa.models;

public class Message {
    String player;
    String text;

    public Message(){}

    public Message(String player, String text) {
        this.player = player;
        this.text = text;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


}
