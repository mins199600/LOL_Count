package hello.lol.deck.guide.repository;

public class DeckDto {

    private int deckId;
    private String deckName;
    private String championNames;

    public int getDeckId() {
        return deckId;
    }

    public void setDeckId(int deckId) {
        this.deckId = deckId;
    }

    public String getDeckName() {
        return deckName;
    }

    public void setDeckName(String deckName) {
        this.deckName = deckName;
    }

    public String getChampionNames() {
        return championNames;
    }

    public void setChampionNames(String championNames) {
        this.championNames = championNames;
    }
}