package syntax;

import lexical.LexicalAnalyzer;
import lexical.Token;

import java.util.List;
import java.util.Stack;

/**
 * Created by tjDu on 2016/11/1.
 */
public class SyntaxParser {
    private List<Token> tokens;
    private Stack<Token> stack;
    private List<String> output;


    public static void main(String[] args) {
        SyntaxParser parser = new SyntaxParser();
        parser.parse();
    }

    public SyntaxParser() {
        LexicalAnalyzer analyzer = new LexicalAnalyzer();
        tokens = analyzer.getTokens();
        stack = new Stack<>();
        stack.push(new Token("END", "$"));
        stack.push(new Token("START", "S"));
    }

    public void parse() {
        System.out.println(CFGHelper.getCfg("S", "62"));
    }
}
