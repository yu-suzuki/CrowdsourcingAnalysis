import com.orangesignal.csv.Csv;
import com.orangesignal.csv.CsvConfig;
import com.orangesignal.csv.handlers.StringArrayListHandler;
import element.Evaluation;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        Set<Evaluation> evaluationSet = getEvaluations();
        Map<Integer, Set<Evaluation>> QevaluationSet = getEvaluationSet(evaluationSet,"qid");
        Map<Integer, Set<Evaluation>> UevaluationSet = getEvaluationSet(evaluationSet, "uid");
        System.out.println(QevaluationSet.size());
        for(int e: QevaluationSet.keySet()){
            System.out.println(e+":"+QevaluationSet.get(e).size());
        }
        for(int e: UevaluationSet.keySet()){
            System.out.println(e+":"+UevaluationSet.get(e).size());
        }
    }

    private static Map<Integer,Set<Evaluation>> getEvaluationSet(Set<Evaluation> esets, String target) {
        Map<Integer,Set<Evaluation>> r = new HashMap<>();
        for(Evaluation e: esets){
            int id;
            switch (target) {
                case "qid":
                    id = e.getQid();
                    break;
                case "uid":
                    id = e.getUid();
                    break;
                default:
                    id = -1;
                    break;
            }
            Set<Evaluation> value;
            if(r.containsKey(id)){
                value = r.get(id);
            } else {
                value = new HashSet<>();
            }
            value.add(e);
            r.put(id, value);
        }
        return r;
    }

    private static Set<Evaluation> getEvaluations() {
        Set<Evaluation> e = new HashSet<>();
        try {
            List<String[]> list = Csv.load(new File("data/evaluations.csv"), new CsvConfig(), new StringArrayListHandler());
            for(String[] l: list){
                if(l.length < 6) continue;
                e.add(new Evaluation(Integer.parseInt(l[1].trim())
                        ,Integer.parseInt(l[2].trim())
                        ,Integer.parseInt(l[5].trim())
                        ,Integer.parseInt(l[6].trim())));
            }
        } catch (IOException | ArrayIndexOutOfBoundsException ex) {
            ex.printStackTrace();
        }
        return e;
    }
}
