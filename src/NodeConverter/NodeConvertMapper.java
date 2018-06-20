
/**
 * NodeConverter
 *
 * convert the nodes
 *
 * Created by Nino Lau on 2018.06. Copyright © Nino 2018.
 */


import java.io.IOException;
import java.util.StringTokenizer;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Mapper;

public class NodeConvertMapper extends Mapper<Object, Text, IntWritable, Text> {

    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {

        IntWritable id;
        IntWritable nextId;

        // toker string
        StringTokenizer str = new StringTokenizer(value.toString());

        // read next token
        if(str.hasMoreTokens()) {
            id = new IntWritable(Integer.parseInt(str.nextToken()));
        } else{
            return;
        }

        // &: destination address
        nextId = new IntWritable(Integer.parseInt(str.nextToken()));

        // write text
        context.write(id, new Text("&" + nextId));

    }

}