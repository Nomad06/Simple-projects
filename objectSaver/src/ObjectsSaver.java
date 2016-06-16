import java.io.*;


public class ObjectsSaver {
    public static void main(String[] args)
    {
        new ObjectsSaver().go();
    }

    public void go()
    {
        GameCharacter one   = new GameCharacter(50, "Elf", new String[]{"elbow", "sword", "kastet"});
        GameCharacter two   = new GameCharacter(300, "Throll", new String[]{"naked hands", "axe"});
        GameCharacter three = new GameCharacter(120, "Mage", new String[]{"spells", "invisibility"});



        try
        {

            FileWriter fileWriter = new FileWriter("file.txt");
            fileWriter.write("Hi, dude!");
            fileWriter.close();

            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("file.cer"));
            os.writeObject(one);
            os.writeObject(two);
            os.writeObject(three);
            os.close();
        }
        catch (Exception ex){ex.printStackTrace();}

        one   = null;
        two   = null;
        three = null;

        try
        {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("file.cer"));
            GameCharacter oneR   = (GameCharacter) is.readObject();
            GameCharacter twoR   = (GameCharacter) is.readObject();
            GameCharacter threeR = (GameCharacter) is.readObject();

            System.out.print(oneR.getPower() + "  |  " + oneR.getType() + "  |  " + oneR.getWeapon());
            System.out.print(twoR.getPower() + "  |  " + twoR.getType() + "  |  " + twoR.getWeapon());
            System.out.print(threeR.getPower() + "  |  " + threeR.getType() + "  |  " + threeR.getWeapon());

        }
        catch (Exception ex){ex.printStackTrace();}


    }
}

class GameCharacter implements Serializable{
    int power;
    String type;
    String[] weapons;

    GameCharacter(int p, String t, String[] w)
    {
        power = p;
        type  = t;
        weapons = w;
    }

    public int getPower()
    {
        return power;
    }

    public String getType()
    {
        return type;
    }

    public String getWeapon()
    {
        String weaponsList = "";
        for (int i = 0; i < weapons.length; i++)
        {
            weaponsList+= weapons[i] + " ";
        }
        return weaponsList;
    }
}

