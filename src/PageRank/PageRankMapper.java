
/**
 * PageRank
 *
 * page rank algorithm
 *
 * Created by Nino Lau on 2018.06. Copyright © Nino 2018.
 */


import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import java.util.StringTokenizer;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class PageRankMapper extends Mapper<Object, Text, IntWritable, Text> {
    
    private IntWritable id;
    private String nextId;
    private int count;
    private float averagePr;
  
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException{
        
        // toker string
        StringTokenizer str = new StringTokenizer(value.toString());
        
        // read next token
        if (str.hasMoreTokens()) {
            id = new IntWritable(Integer.parseInt(str.nextToken()));
        } else {
            return;
        }

        // get the destinations
        nextId = str.nextToken();
        
        // get the count of destinations
        count = str.countTokens();

        // each destination average reward
        averagePr = Float.parseFloat(nextId) / count;
        
        // $: departure pagerank value
        // &: destination address
        while (str.hasMoreTokens()) {

            String nextId = str.nextToken();
            
            Text tmpText = new Text("$" + averagePr);
            context.write(new IntWritable(Integer.parseInt(nextId)), tmpText);

            tmpText = new Text("&" + nextId);
            context.write(id, tmpText);
        }
    }
}