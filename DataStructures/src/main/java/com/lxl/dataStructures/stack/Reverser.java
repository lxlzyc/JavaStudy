package com.lxl.dataStructures.stack;

/**
 * 逆序类，调用ArrayStack
 *
 * @author Administrator
 */
public class Reverser {

    private String input;
    private String output;

    public Reverser(String in) {
        input = in;
    }

    public String doRev() {
        int stackSize = input.length();
        ArrayStack aStack = new ArrayStack(stackSize);

        for (int j = 0; j < stackSize; j++) {
            char ch = input.charAt(j);
            aStack.push(ch);
        }
        output = "";
        while (!aStack.isEmpty()) {
            char ch = (char) aStack.pop();
            output = output + ch;
        }
        return output;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Reverser re = new Reverser("123333333");
        System.out.println(re.doRev());
    }

}
