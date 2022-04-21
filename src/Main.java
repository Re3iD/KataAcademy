
import java.util.Scanner;

public class Main {
    public static Scanner scan = new Scanner(System.in);
    public static Boolean isRomul = false;
    public static void main(String[] args) {
        String input="";
        Boolean check=false;
        try {
            input = getInput();
            check = validate(input);
        }catch (Exception e){
            System.err.printf(e.getMessage());
        }
        String[] arr;
        if(check){

            arr = stringToArr(input);
            String out = calculate(arr,input);
            if(isRomul){
                try {
                    System.out.printf(input+"="+romulOut(out));
                }catch (Exception e){
                    System.err.printf(e.toString());
                }
            }else{
                System.out.printf(input+"="+out);
            }
        }

    }
    public static String getInput() throws Exception{

        System.out.print("Insert expression: ");
        String input = scan.next();
        if(input.length()>6){
            throw new Exception("Строка не проходит по параметрам");
        }
        return input;
    }
    public static Boolean validate (String str)throws Exception{
        if(!(str.contains("+")||str.contains("-")||str.contains("*")||str.contains("/"))){
            throw new Exception("Не является математической операцией");
        }
        String romulStr = "I II III IV V VI VII VIII IX X";
        String[] output = str.split("[+-/*]",2);

        Boolean check = false;
        for (String it:output) {

            if(!(romulStr.contains(it)&&(romulStr.contains(output[0])|romulStr.contains(output[1])))) {
                try {
                    int num = Integer.parseInt(it);
                    if(num<10&&num>0){
                        check = true;
                        isRomul = false;
                    }

                    else throw new NumberFormatException();
                } catch (NumberFormatException e) {
                    System.err.println("Неправильный формат строки!");
                }
            } else if (romulStr.contains(it)) {
                check=true;
                isRomul = true;
            }else{
                throw new Exception("Выражение имеет ошибку");
            }
        }

        return check;
    }
    public static  String[] stringToArr(String str){
        String[] exchangeArr = str.split("[+-/*]",2);
        for (int i=0;i<exchangeArr.length;i++) {
            if(isRomul) {
                String log = Rome.valueOf(exchangeArr[i]).getNum();
                exchangeArr[i] = log;
            }
        }
        return exchangeArr;
    }
    public static String calculate(String[] arr, String input){
        if(input.contains("/")){
            int op = Operation.DIVIDE.action(arr[0],arr[1]);
            return Integer.toString(op);
        } else if (input.contains("*")) {
            int op = Operation.MULT.action(arr[0],arr[1]);
            return Integer.toString(op);
        } else if (input.contains("+")) {
            int op = Operation.SUM.action(arr[0],arr[1]);
            return Integer.toString(op);
        } else if (input.contains("-")) {
            int op = Operation.MINUS.action(arr[0],arr[1]);
            return Integer.toString(op);
        }
        return "Что-то пошло не так";
    }
    public static String romulOut(String num) throws Exception{
        int lock = Integer.parseInt(num);
        if(lock<0) throw new Exception("Римские цифры не могут быть отрицательными");
        String[] romanNumbers = { "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int[] arab = { 100, 90, 50, 40, 10, 9, 5, 4, 1};
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (lock > 0 || arab.length == (i - 1)) {
            while ((lock - arab[i]) >= 0) {
                lock -= arab[i];
                result.append(romanNumbers[i]);
            }
            i++;
        }
        return result.toString();
    }
}