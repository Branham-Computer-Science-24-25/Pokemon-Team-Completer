import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.Arrays;

/**
 * A class that has analyzeResistance, typeMatchuper, numberToTypeConverter, resistanceCounter, and teamCompleter methods
 * @author iprovince
 */
public class PokemonTeam
{
    private boolean[] resistedTypes = new boolean[18];
    private String teamString;
    
    /**
     * Constructs a PokemonTeam object and analyze the resistance of it
     * @param typeList a list of pokemon type combinations in type/type type format
     */
    public PokemonTeam(String typeList){
        this.teamString = typeList;
        analyzeResistance(typeList);
    }
    /**
     * breaks down typeList so that typeMatchuper can set resistedTypes to the correct value
     * @param typeList a list of pokemon type combinations in type/type type format
     * @throws InputMismatchException if typeList is not in the correct format
     */
    public void analyzeResistance(String typeList){
        if (typeList.length()<3 || typeList.substring(0,2).equals(" /")|| typeList.substring(0,2).equals("/ ")){
            throw new InputMismatchException("Bad input");
        }
        Scanner scan = new Scanner(typeList);
        while(scan.hasNext()){
            Scanner read = new Scanner(scan.next());
            read.useDelimiter("/");
            String type1 = read.next();
            String type2 = "";
            if (read.hasNext()){
                type2 = read.next();
            }
            typeMatchuper(type1,type2);
        }
    }
    /**
     * sets values in resistedTypes to be true according the pokemon type chart 
     * @param type1 one of the 18 pokemon types
     * @param type2 one of the 18 pokemon types can be empty
     * @throws InputMismatchException if type is not recognized
     */
    public void typeMatchuper(String type1, String type2){
        //initalizes a new double starting at 1.0 for each pokemon type
        double[] damageMult = new double[18];
        Arrays.fill(damageMult, 1.0);
        for(int i=0; i<2; i++){
            String currentType = type1;
            if (i>0&&!(type2.equals(""))){
                currentType=type2;
            }
            switch(currentType){
                //type chart! multiplies each type's double by the effectiveness against the input
                case "normal": damageMult[13]*=0; damageMult[6]*=2; break;
                case "fire": damageMult[1]*=.5; damageMult[2]*=2; damageMult[3]*=.5; damageMult[5]*=.5; damageMult[8]*=2; damageMult[11]*=.5; damageMult[12]*=2; damageMult[16]*=.5; damageMult[17]*=.5; break;
                case "water": damageMult[1]*=.5; damageMult[2]*=.5; damageMult[3]*=2; damageMult[4]*=2; damageMult[5]*=.5; damageMult[16]*=.5; break;
                case "grass": damageMult[1]*=2; damageMult[2]*=.5; damageMult[3]*=.5; damageMult[4]*=.5; damageMult[5]*=2; damageMult[7]*=2; damageMult[8]*=.5; damageMult[9]*=2; break;
                case "electric": damageMult[4]*=.5; damageMult[8]*=2; damageMult[9]*=.5; damageMult[16]*=.5; break;
                case "ice": damageMult[1]*=2; damageMult[5]*=.5; damageMult[6]*=2; damageMult[12]*=2; damageMult[16]*=2; break;
                case "fighting": damageMult[9]*=2; damageMult[10]*=2; damageMult[11]*=.5; damageMult[12]*=.5; damageMult[15]*=.5; damageMult[17]*=2; break;
                case "poison": damageMult[3]*=.5; damageMult[6]*=.5; damageMult[7]*=.5; damageMult[8]*=2; damageMult[10]*=2; damageMult[11]*=.5; damageMult[17]*=.5; break;
                case "ground": damageMult[2]*=2; damageMult[3]*=2; damageMult[4]*=0; damageMult[5]*=2; damageMult[7]*=.5; damageMult[12]*=.5; break;
                case "flying": damageMult[3]*=.5; damageMult[4]*=2; damageMult[5]*=2; damageMult[6]*=.5; damageMult[8]*=0; damageMult[11]*=.5; damageMult[12]*=2; break;
                case "psychic": damageMult[6]*=.5; damageMult[10]*=.5; damageMult[11]*=2; damageMult[13]*=2; damageMult[15]*=2; break;
                case "bug": damageMult[1]*=2; damageMult[3]*=.5; damageMult[6]*=.5; damageMult[8]*=.5; damageMult[9]*=2; damageMult[12]*=2; break;
                case "rock": damageMult[0]*=.5; damageMult[1]*=.5; damageMult[2]*=2; damageMult[3]*=2; damageMult[6]*=2; damageMult[7]*=.5; damageMult[8]*=2; damageMult[9]*=.5; damageMult[16]*=2; break;
                case "ghost": damageMult[0]*=0; damageMult[6]*=0; damageMult[7]*=.5; damageMult[11]*=.5; damageMult[13]*=2; damageMult[15]*=2; break;
                case "dragon": damageMult[1]*=.5; damageMult[2]*=.5; damageMult[3]*=.5; damageMult[4]*=.5; damageMult[5]*=2; damageMult[14]*=2; damageMult[17]*=2; break;
                case "dark": damageMult[6]*=2; damageMult[10]*=0; damageMult[11]*=2; damageMult[13]*=0; damageMult[15]*=0; damageMult[17]*=2; break;
                case "steel": damageMult[0]*=.5; damageMult[1]*=2; damageMult[3]*=.5; damageMult[5]*=.5; damageMult[6]*=2; damageMult[7]*=0; damageMult[8]*=2; damageMult[9]*=.5; damageMult[10]*=.5;
                /*steel has a lot of resistances*/damageMult[11]*=.5; damageMult[12]*=.5; damageMult[14]*=.5; damageMult[16]*=.5; damageMult[17]*=.5; break;
                case "fairy": damageMult[6]*=.5; damageMult[7]*=2; damageMult[11]*=.5; damageMult[14]*=0; damageMult[15]*=.5; damageMult[16]*=2; break;
                default: throw new InputMismatchException("Thats not a pokemon type!");
            }
        }
        //sets each type that the input resists to be true
        for (int i=0;i<18;i++){
            if (damageMult[i]<1){
                resistedTypes[i]=true;
            }
        }
    }
    /**
     * a static method that returns a pokemon type combination based on the two integers inputted
     * @param type1 a number from 0-17
     * @param type2 a number from 0-17
     * @returns a pokemon type combination based on the two integers inputted
     * @throws InputMismatchException if a type is not a number from 0-17
     */
    public static String numberToTypeConverter(int type1, int type2){
        int currentType=type1;
        String convertedType = " ";
        for (int i=0; i<2;i++){
            if (i>0){
                currentType = type2;
            }
            switch (currentType){
                case 0: convertedType+="normal";break;
                case 1: convertedType+="fire";break;
                case 2: convertedType+="water";break;
                case 3: convertedType+="grass";break;
                case 4: convertedType+="electric";break;
                case 5: convertedType+="ice";break;
                case 6: convertedType+="fighting";break;
                case 7: convertedType+="poison";break;
                case 8: convertedType+="ground";break;
                case 9: convertedType+="flying";break;
                case 10:convertedType+="psychic";break;
                case 11:convertedType+="bug";break;
                case 12:convertedType+="rock";break;
                case 13:convertedType+="ghost";break;
                case 14:convertedType+="dragon";break;
                case 15:convertedType+="dark";break;
                case 16:convertedType+="steel";break;
                case 17:convertedType+="fairy";break;
                default:throw new InputMismatchException("bad number");
            }
            if (i==0){
                convertedType+="/";
            }
        }
        return convertedType;
    }
    /**
     * a method that returns the number of pokemon types the PokemonTeam resists
     * @returns the number of pokemon types the PokemonTeam resists
     */
    public int resistanceCounter(){
        int counter=0;
        for (int i=0;i<18;i++){
            if (resistedTypes[i]){
                counter++;
            }
        }
        return counter;
    }
    /**
     * a method that returns the type combination that when added to the PokemonTeam, minimizes the number of types not resisted
     * @returns the type combination that when added to the PokemonTeam, minimizes the number of types not resisted
     */
    public String teamCompleter(){
        String bestAddition=" normal/normal";
        for (int i=0; i<18; i++){
            for (int j=0;j<18;j++){
                String currentMon=numberToTypeConverter(i,j);
                PokemonTeam testTeam = new PokemonTeam(teamString+currentMon);
                PokemonTeam bestTeam = new PokemonTeam(teamString+bestAddition);
                if(testTeam.resistanceCounter()>=bestTeam.resistanceCounter()){
                    bestAddition=currentMon;
                }
            }
        }
        return bestAddition.substring(1);
    }
    
    public static void main(String[] args){
        System.out.println("what are the type combinations of your Pokemon team in type/type type format? ");
        Scanner in = new Scanner(System.in);
        PokemonTeam team = new PokemonTeam(in.nextLine());
        System.out.println("you should add a(n) "+team.teamCompleter()+" type Pokemon!");
    }
}
