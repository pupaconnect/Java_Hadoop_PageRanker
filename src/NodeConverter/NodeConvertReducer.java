
/**
 * NodeConverter
 *
 * convert the nodes
 *
 * Created by Nino Lau on 2018.06. Copyright © Nino 2018.
 */


import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.FloatWritable;
import org.apache.hadoop.mapreduce.Reducer;

public class NodeConvertReducer extends Reducer<IntWritable, Text, IntWritable, Text> {

    public void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {

        // idList: destination address list
        ArrayList<String> idList = new ArrayList<String>();
        
        // idString: destination address
        for(Text id : values) {
            String idStr = id.toString();
            if (idStr.substring(0,1).equals("&")) {
                idList.add(idStr.substring(1));
            }
        }
        // departure pagerank
        float pr = 1.0f;
        // result string
        String result = String.valueOf(pr) + " "; 
        for(int i = 0; i< idList.size();i++){
            result += idList.get(i) + " ";
        }

        // write text
        context.write(key, new Text(result));
    }

}