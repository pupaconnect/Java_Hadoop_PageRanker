
/**
 * NodeConverter
 *
 * convert the nodes
 *
 * Created by Nino Lau on 2018.06. Copyright © Nino 2018.
 */


import java.net.URI;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.Text;
import java.net.URISyntaxException;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Counters;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.mapred.Counters.Counter;
import org.apache.hadoop.mapreduce.Reducer.Context;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class NodeConverter {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        Configuration conf = new Configuration();

        // input path
        String pathIn = "/dataset";

        // output path
        String pathOut = "/dataconvert";

        // IP
        FileSystem.setDefaultUri(conf, new URI("hdfs://192.168.142.129:9000"));

        // pretreat the data
        Job job = new Job(conf, "MapReduce pretreatment");
        job.setJarByClass(NodeConverter.class);
        
        job.setMapperClass(NodeConvertMapper.class);
        job.setReducerClass(NodeConvertReducer.class);
        
        // the output type of map and reduce class to be same
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        FileInputFormat.addInputPath(job, new Path(pathIn));
        FileOutputFormat.setOutputPath(job, new Path(pathOut));

        System.exit(job.waitForCompletion(true) ? 0:1);
    }
}