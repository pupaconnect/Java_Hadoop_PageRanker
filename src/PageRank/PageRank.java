

/**
 * PageRank
 *
 * page rank algorithm
 *
 * Created by Nino Lau on 2018.06. Copyright © Nino 2018.
 */

import java.net.URI;
import java.util.Set;
import java.util.HashSet;
import java.io.IOException;


import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.fs.Path;
import java.util.StringTokenizer;
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

public class PageRank {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException, URISyntaxException {
        Configuration conf = new Configuration();

        // input path
        String pathIn = "/dataconvert";

        // outputpath template
		String output = "/dataoutput";
		
        // outputpath
        String pathOut = "/dataoutput";

        // IP
        FileSystem.setDefaultUri(conf, new URI("hdfs://192.168.142.129:9000"));

        // first output
        pathOut = output + "1";

        // pagerank mapreduce
        for (int i = 1; i < 10; i++) {
            Job job = new Job(conf, "MapReduce pagerank");
            job.setJarByClass(PageRank.class);
            
            // firstmapper used to 
            job.setMapperClass(PageRankMapper.class);
            job.setReducerClass(PageRankReducer.class);
            
            // output type of map and reduce class to be same
            job.setOutputKeyClass(IntWritable.class);
            job.setOutputValueClass(Text.class);

            FileInputFormat.addInputPath(job, new Path(pathIn));
            FileOutputFormat.setOutputPath(job, new Path(pathOut));
    
            // input file output file redirection
            pathIn = pathOut;
            pathOut = output + (i + 1);
            
            job.waitForCompletion(true);
        }
    }
}