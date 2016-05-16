package element;

/**
 * Created by ysuzuki on 2016/05/14.
 * Evaluation Object
 */
public class Evaluation {
    private int qid;
    private int evaluation;
    private int uid;
    private int genre_id;
    public Evaluation(int uid, int evaluation, int qid, int genre_id) {
        this.qid = qid;
        this.evaluation = evaluation;
        this.uid = uid;
        this.genre_id = genre_id;
    }

    public void print() {
        System.out.println(qid+":"+evaluation+":"+uid+":"+genre_id);
    }

    public int getQid() {
        return qid;
    }

    public int getUid() {
        return uid;
    }
}
