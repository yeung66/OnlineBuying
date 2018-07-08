package segmenter;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
    MADE BY :叶晟柯
 */
public class segmenter {
    public List<String> seg(String s){
        Result result = ToAnalysis.parse(s);
        List<Term> terms = result.getTerms();
        List<String> st = new ArrayList<>();
        for (int i=0;i<terms.size();i++){
            String word = terms.get(i).getName();
            if (terms.get(i).getNatureStr().equals("n"))
                st.add(word);
        }
        return st;
    }
    public String[] engSeg(String s)
    {
        String w= "";
        Pattern p = Pattern.compile("\\s+");
        Matcher m = p.matcher(s);
        w= m.replaceAll(" ");
        String[] st = w.split(" ");
        return st;
    }
}
