package segmenter;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.splitWord.analysis.ToAnalysis;

import java.util.ArrayList;
import java.util.List;

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
}
