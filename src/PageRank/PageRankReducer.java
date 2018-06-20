
/**
 * PageRank
 *
 * page rank algorithm
 *
 * Created by Nino Lau on 2018.06. Copyright © Nino 2018.
 */


import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class PageRankReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

    // alpha and bonus
    private static float alpha = 0.85f;
    private static float bonus = 0.2f;
    
    public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException{
        
        // idList: destination address list
        ArrayList<String> idList = new ArrayList<String>();
        // destinations output
        String output = "  ";
        // departure pagerank
        float pr = 0;

        // $: departure pagerank value
        // &: destination address
        for (Text id : values) {
            String idStr = id.toString();
            if (idStr.substring(0, 1).equals("$")) {
                pr += Float.parseFloat(idStr.substring(1));
            }
            else if (idStr.substring(0, 1).equals("&")) {
                String nextId = idStr.substring(1);
                idList.add(nextId);
            }
        }
        // pagerank calculation
        pr = pr * alpha + bonus;

        // destinations output
        for (int i = 0; i < idList.size(); i++) {
            output = output + idList.get(i) + "  ";
        }
        
        // result string
        String result = pr + output;

        // write to text
        context.write(key, new Text(result));
    }
}