import java.io.Console;
import java.util.HashMap;
import java.util.Map;

public class Simple_assembler_interpreter {
    public static void main(String[] args) {
        String[] program = new String[]{"mov a 5","inc a","dec a","dec a","jnz a -1","inc a"};
        Map<String, Integer> response = SimpleAssembler.interpret(program);
        System.out.println(" OTRZYMANO ");
        response.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
        System.out.println(" POWINNO BYC");
        Map<String, Integer> out = new HashMap<String, Integer>();
        out.put("a", 1);
        out.forEach((k,v) -> System.out.println("Item : " + k + " Count : " + v));
// ------------------------------------------------------------------------------------------
        program = new String[]{"mov a -10","mov b a","inc a","dec b","jnz a -2"};
        response = SimpleAssembler.interpret(program);
        out.clear();
        out.put("a", 0);
        out.put("b", -20);
        System.out.println(" OTRZYMANO ");
        response.forEach((k,v)->System.out.println("Item : " + k + " Count : " + v));
        System.out.println(" POWINNO BYC");
        out.forEach((k,v) -> System.out.println("Item : " + k + " Count : " + v));
    }
}

class SimpleAssembler {
    public static Map<String, Integer> interpret(String[] program){
        // show program
        for (String s : program) {
            System.out.println(s);
        }

        Map<String, Integer> registers = new HashMap<>();
        int index = 0;
        while (index < program.length){
            String instruction = program[index];
            System.out.println(instruction);
            String[] s = instruction.split(" ");
            switch (s[0]){
                case "mov" : {
                    Integer value = 0;
                    try{
                        value = Integer.valueOf(s[2]);
                    }catch (NumberFormatException e){
                        if(registers.containsKey(s[2])){
                            value = registers.get(s[2]);
                        }else {
                            //ERROR - variable is not declared
                            return null;
                        }
                    }
                    registers.put(s[1], value);
                    break;
                }
                case "inc" : {
                    if(registers.containsKey(s[1])){
                        Integer old = registers.get(s[1]);
                        registers.replace(s[1], ++old);
                    }else{
                        //ERROR - variable is not declared
                        return null;
                    }
                    break;
                }
                case "dec" : {
                    if(registers.containsKey(s[1])){
                        Integer old = registers.get(s[1]);
                        registers.replace(s[1], --old);
                    }else{
                        //ERROR - variable is not declared
                        return null;
                    }
                    break;
                }
                case "jnz" : {
                    Integer arg1 = 0;
                    try{
                        arg1 = Integer.valueOf(s[1]);
                    }catch (NumberFormatException e){
                        if(registers.containsKey(s[1])){
                            arg1 = registers.get(s[1]);
                        }else{
                            return null;
                        }
                    }
                    if(arg1 != 0){
                        int jump = Integer.valueOf(s[2]);
                        index = index + jump - 1;
                    }

//                    if(registers.containsKey(s[1])){
//                        Integer value = registers.get(s[1]);
//                        if(value != 0){
//                            int jump = Integer.valueOf(s[2]);
//                            index = index + jump -1;
//                        }
//                    }else{
//                        //ERROR - variable is not declared
//                        Map<String, Integer> out = new HashMap<String, Integer>();
//                        out.put("ERROR in jnz", 1);
//                        return out;
//                    }
                    break;
                }
            }
            index++;
        }
        return registers;
    }
}
