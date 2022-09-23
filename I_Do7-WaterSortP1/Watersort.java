
/**
 * Write a description of class Watersort here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
import java.util.Random;
import java.util.Scanner;
public class Watersort
{
    static Character red = new Character('r');
    static Character green = new Character('g');
    static Character blue = new Character('b');
    static char empty = ' ';
    static int countLvl = 2;
    public static void main(String [] args)
    {
        StackAsMyArrayList<Character>[] bottles = new StackAsMyArrayList[5];

        for(int i=0; i<5;i++)
        {
            bottles[i] = new StackAsMyArrayList<>();
        }

        Scanner input = new Scanner(System.in); 
        for(int j = 0;j<bottles.length;j++)
        {
            bottles[j].clear();
        }
        System.out.print("Level 1\n" );
        fill(bottles);        
        ShowAll(bottles); 

        boolean flag = false;
        while(!flag)
        {
            System.out.print("Would you like to play? (1-Y/0-N) " );
            int answer = input.nextInt();

            if(answer == 0)
            {
                flag = true;
                break;
            }
            else
            {
                while(!solved(bottles))
                {
                    System.out.print("Enter the source bottle: " );
                    int source1 = input.nextInt();
                    if(source1 < 0 || source1 >4 )
                    {
                        System.out.print("\nNot a valid source" );
                        System.out.print("\nEnter the source bottle: " );
                        source1 = input.nextInt();
                    }
                    System.out.print("Enter the target bottle: " );
                    int target1 = input.nextInt();
                    if(target1 < 0 || target1 >4)
                    {
                        System.out.print("\nNot a valid target" );
                        System.out.print("\nEnter the target bottle: " );
                        target1 = input.nextInt();
                    }

                    if((bottles[source1].peek() == bottles[target1].peek() )|| (bottles[target1].getStackSize() == 0 ))
                    {
                        if(bottles[target1].getStackSize() < 4)
                        {
                            bottles[target1].push(bottles[source1].pop());                    
                            ShowAll(bottles);
                        }
                        else
                        {
                            System.out.println("\nBottle " +target1+ " is full\n");
                            ShowAll(bottles);
                        }

                    }
                    else
                    {
                        System.out.println("Can't put this color here!");
                    }
                }
                System.out.println("\nYou solved the puzzle!\n");
                for(int j = 0;j<bottles.length;j++)
                {
                    bottles[j].clear();
                }
                System.out.print("Level " + countLvl + "\n" );
                fill(bottles);        
                ShowAll(bottles); 
            }
            countLvl++;

        }
        System.out.println("\nSorry to see you go");       

    }

    private static void ShowAll(StackAsMyArrayList<Character>[] bottles)
    {
        for(int i=0;i<5;i++)
        {
            System.out.println("Bottle " +i+": "+ bottles[i].toString());
        }

    }

    private static void fill(StackAsMyArrayList<Character>[] bottles)
    {
        int number, color,cred = 0,cgreen = 0,cblue = 0;
        int counter = 0 ;
        Random random = new Random();

        while( counter <=12)
        {
            for(int x=1;x<bottles.length ;x++)
            {

                number = 4;//(int)Math.floor(Math.random()*(4-2+1)+2);
                int k  = bottles[x].getStackSize();
                for(int y=0;k<number;y++)
                {

                    color = random.nextInt(3);
                    if(color == 0 && cred!=4)
                    {
                        bottles[x].push(red);
                        cred++;
                        //counter++;
                    }
                    else if(color == 1 && cgreen !=4)
                    {
                        bottles[x].push(green);
                        cgreen++;
                        //counter++;
                    }
                    else if(color == 2 && cblue!=4)
                    {
                        bottles[x].push(blue);
                        cblue++;
                        //counter++;
                    }
                    k++;

                }

            }
            counter++;
        }

    }

    public static boolean solved(StackAsMyArrayList<Character>[] bottles)
    {
        int count = 0;
        for(int n = 0;n<bottles.length;n++)
        {
            if (bottles[n].getStackSize() == 4 || bottles[n].getStackSize() == 0)
            {
                if(bottles[n].checkStackUniform())
                {
                    count ++;
                }
            }

        }
        if (count == 5)
        {
            return true;
        }
        else 
        {
            return false;   
        }

    }
}
