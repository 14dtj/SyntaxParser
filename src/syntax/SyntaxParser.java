package syntax;

import lexical.IOHelper;
import lexical.LexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by tjDu on 2016/11/1.
 */
public class SyntaxParser {
    private List<String> tokens;
    private Stack<String> stack;
    private static List<String> output;


    public static void main(String[] args) {
        SyntaxParser parser = new SyntaxParser();
        parser.parse();
        System.out.println("Parse finished..");
        IOHelper.writeFile(output);
    }

    public SyntaxParser() {
        output = new ArrayList<>();
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        tokens = analyzer.getTokens();
        stack = new Stack<>();
        stack.push("$");
        stack.push("S");
    }

    public void parse() {
        int i = 0;
        String inputToken = tokens.get(i);
        while (!inputToken.equals("$")) {
            String stackToken = stack.peek();
            if (CFGHelper.isTerminal(stackToken)) {
                if (stackToken.equals(inputToken)) {
                    stack.pop();
                    i++;
                    inputToken = tokens.get(i);
                } else {
                    System.out.println("ERROR:Can't match!");
                }
            } else {
                int nextCfg = CFGHelper.getCfg(stackToken, inputToken);
                if (nextCfg == -1) {
                    System.out.println("ERROR:Can't find CFG!");
                    return;
                } else {
                    output.add(CFGHelper.cfgs[nextCfg]);
                    stack.pop();
                    String[] splitCfgs = CFGHelper.splitCfgs[nextCfg];
                    for (int j = 0; j < splitCfgs.length; j++) {
                        if (!splitCfgs[j].startsWith("-1")) {
                            stack.push(splitCfgs[j]);
                        }
                    }
                }
            }

        }

    }
}
