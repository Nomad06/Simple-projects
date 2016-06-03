import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


//This is just a simple project for collections learning. This project does not claim to a normal project.
// I did it for myself and just for fun.

public class Collector
{
    LinkedList<Player> players = new LinkedList<Player>();

    abstract class PropertiesCompare implements Comparator<Player>
    {
        abstract public int compare(Player one, Player two);
    }

    class NameCompare extends PropertiesCompare
    {
        @Override
        public int compare(Player one, Player two)
        {
            return one.getName().compareTo(two.getName());
        }

    }

    class ScoreCompare extends PropertiesCompare
    {
        public int compare(Player one, Player two)
        {
            return (two.getScore() - one.getScore());
        }
    }
    public static void main(String[] args)
    {
        new Collector().start();
    }

    public void start()
    {
        players.add(new Player("John", 150));
        players.add(new Player("Meredit", 250));
        players.add(new Player("Mediv", 50));
        players.add(new Player("Anduin", 550));
        players.add(new Player("Lein", 1250));
        players.add(new Player("Durotan", 350));

        System.out.println(players);

        NameCompare nameCompare = new NameCompare();
        listPrint(nameCompare);

        ScoreCompare scoreCompare = new ScoreCompare();
        listPrint(scoreCompare);



    }

    public void listPrint(PropertiesCompare compare)
    {

        Collections.sort(players, compare);
        System.out.println(players);
    }

}

class Player
{
    private String name;
    private int score;

    Player(String n, int s)
    {
        name = n;
        score = s;
    }

    public String toString()
    {
        return name + " " + score;
    }

    public int getScore()
    {
        return score;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String n)
    {
        this.name = n;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}

