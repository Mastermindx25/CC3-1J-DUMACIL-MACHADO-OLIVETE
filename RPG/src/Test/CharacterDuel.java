package Test;

import Character.Kikiam;
import Character.Wizard;
import java.util.Scanner;

/**
 *
 * @author m304user
 */
public class CharacterDuel {

    /**
     *
     * @param args
     */
    public static void main(String args[]) {
        //Initialize scanner
        Scanner sc = new Scanner(System.in);
        String action;
        
        //Create character objects
        Wizard char1 = new Wizard("Wizard", 2,1,4);
        Kikiam char2 = new Kikiam("Kikiam", 1,2,1);
        
        int round = 1;  
        //Repeat until either character is dead
        while(char1.getCurrentLife() > 0 && char2.getCurrentLife() > 0) {
            int damage = 0;
            
            //Print round number
            System.out.println("Round " + round);
            
            //Print characters' health
            System.out.println(char1.getName() + ": " + char1.getCurrentLife() + "/" + char1.getMaxLife() + "\tMagic: " + char1.getCurrentMagic() + "/" + char1.getMaxMagic());
            System.out.println(char2.getName() + ": " + char2.getCurrentLife() + "/" + char2.getMaxLife());
            
            //char1 attack char2
            System.out.print("Input Wizard action: (H for Health, LB for Lightning Bolt, and A for Attack): ");
            action = sc.next().toUpperCase();
            if (null != action) switch (action) {
                case "H":
                    //For healing
                    if (char1.getCurrentMagic() >= 8) {
                        int heal = char1.castHeal();
                        System.out.println(char1.getName() + " casts HEAL. " + char1.getName() + " heals " + heal + " health.");
                    } else {
                        damage = char1.attack();
                        System.out.println("Not enough MAGIC to cast HEAL.");
                        System.out.println(char1.getName() + " attacks " + char2.getName() + " for " + damage + " damage.");
                        char2.wound(damage);
                    }   break;
                case "LB":
                    //For lightning bolt spell
                    if (char1.getCurrentMagic() >= 5) {
                        damage = char1.castLightningBolt();
                        System.out.println(char1.getName() + " casts LIGHTNING BOLT. " + char2.getName() + " takes " + damage + " damage.");
                        char2.wound(damage);
                    } else {
                        damage = char1.attack();
                        System.out.println("Not enough MAGIC to cast LIGHTNING BOLT.");
                        System.out.println(char1.getName() + " attacks " + char2.getName() + " for " + damage + " damage.");
                        char2.wound(damage);
                    }   break;
                case "A":
                    //For attacking
                    damage = char1.useWeapon();
                    System.out.println(char1.getName() + " uses their weapon to attack " + char2.getName() + " for " + damage + " damage but");
                    char2.wound(damage);
                    int defense = char2.useArmor();
                    System.out.println(char2.getName() + " uses their armor to deflect " + defense + " damage.");
                    break;
                default:
                    //For default action (invalid input)
                    damage = char1.attack();
                    System.out.println("Invalid input.");
                    System.out.println(char1.getName() + " attacks " + char2.getName() + " for " + damage + " damage.");
                    char2.wound(damage);
                    break;
            }
            
            //char2 attack char1
            if (char2.getCurrentLife() > 0) {
                damage = char2.castDeepFry();
                System.out.println(char2.getName() + " casts DEEP FRY. " + char2.getName() + " heals " + damage + " health and " + char1.getName() + " takes " + damage + " damage.");
                char1.wound(damage);
            }
            
            //Increase round number
            System.out.println("");
            round++;
            
        }
        
        //Print
        System.out.println(char1.getName() + ": " + char1.getCurrentLife() + "/" + char1.getMaxLife());
        System.out.println(char2.getName() + ": " + char2.getCurrentLife() + "/" + char2.getMaxLife());
        System.out.println("");
        
        if (char1.getCurrentLife() == char2.getCurrentLife()) {
            System.out.println("Draw.");
        } else if (char1.getCurrentLife() > char2.getCurrentLife()) {
            System.out.println(char1.getName() + " wins.");
        } else if (char2.getCurrentLife() > char1.getCurrentLife()) {
            System.out.println(char2.getName() + " wins.");
        }
    }
}
