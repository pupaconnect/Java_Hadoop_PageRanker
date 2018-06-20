
import java.io.IOException;
import java.util.StringTokenizer;
import java.io.DataInput;
import java.io.DataOutput;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.Partitioner;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

public class InvertedIndex {

	public static class Elem implements WritableComparable {
		private Text word;
		private Text docno;

		public Elem(String word, String docno) {
			this.word = new Text(word);
			this.docno = new Text(docno);
		}

		public Elem() {
			word = new Text();
			docno = new Text();
		}

		public void readFields(DataInput in) throws IOException {
			word.readFields(in);
			docno.readFields(in);
		}

		public void write(DataOutput out) throws IOException {
			word.write(out);
			docno.write(out);
		}

		public String getWord() {
			return word.toString();
		}

		public String getDocno() {
			return docno.toString();
		}

		public int compareTo(Object o) {
			Elem e = (Elem)o;
			if (!this.getWord().equals(e.getWord()))
				return this.getWord().compareTo(e.getWord());
			if (!this.getDocno().equals(e.getDocno()))
				return this.getDocno().compareTo(e.getDocno());
			return 0;
		}
	}

	public static class InvertedIndexMapper extends Mapper<LongWritable, Text, Elem, IntWritable> {

		private IntWritable one = new IntWritable(1);
		/* 
		 * input: (line-offset, line)
		 * output: (word docno, 1)
		 */
		public void map(LongWritable lineOffset, Text line, Context context) throws IOException, InterruptedException {
			FileSplit split = (FileSplit)(context.getInputSplit());		
			String fileName = split.getPath().getName();
			StringTokenizer itr = new StringTokenizer(line.toString());
			while (itr.hasMoreTokens()) {
				String token = itr.nextToken().toLowerCase();
				context.write(new Elem(token, fileName), one);
			}
		}
	}
	public static class InvertedIndexCombiner extends Reducer<Elem, IntWritable, Elem, IntWritable> {
		
		IntWritable sum = new IntWritable();
		
		public void reduce(Elem e, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int count = 0;
			for(IntWritable val : values)
				count += val.get();
			sum.set(count);
			context.write(e, sum);			
		}

	}
	public static class InvertedIndexPartitioner extends Partitioner<Elem, IntWritable> {

		/*
		 * partitioned by word
		 */
		public int getPartition(Elem key, IntWritable value, int numPartition) {
			return (key.getWord().hashCode() & 0x7fffffff) % numPartition;
		}
	}
	
	public static class InvertedIndexReducer extends Reducer<Elem, IntWritable, Text, Text> {

		private String word = null;
		private int num = 0;
		private String result = "";

		public void cleanup(Context context) throws IOException, InterruptedException {
			if (word != null)
				output(context);
		}

		private void output(Context context) throws IOException, InterruptedException {
			context.write(new Text(word), new Text(String.format(": %d :%s", num, result)));
			result = "";
			num = 0;
		}

		public void reduce(Elem e, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
			int count = 0;
			for (IntWritable iw : values)
				count += iw.get();
			if (word != null && !e.getWord().equals(word))
				output(context);
			word = e.getWord();
			result += String.format(" (%s, %d)", e.getDocno(), count);
			num++;
		}
	}

	public static void main(String[] args) throws Exception {
		Configuration conf = new Configuration();		
		args = new GenericOptionsParser(conf, args).getRemainingArgs();
		if (args.length != 2) {
			System.err.println("Usage: InvertedIndex <input> <output>");
			System.exit(2);
		}

		String inputDirName = args[0];
		String outputDirName = args[1];

		Job job = new Job(conf, "Inverted Index");
		job.setJarByClass(InvertedIndex.class);
		job.setMapperClass(InvertedIndexMapper.class);
		job.setCombinerClass(InvertedIndexCombiner.class);
		job.setPartitionerClass(InvertedIndexPartitioner.class);
		job.setReducerClass(InvertedIndexReducer.class);
		job.setMapOutputKeyClass(Elem.class);
		job.setMapOutputValueClass(IntWritable.class);
		job.setOutputKeyClass(Text.class);
		job.setOutputValueClass(Text.class);
		job.setInputFormatClass(TextInputFormat.class);
		job.setOutputFormatClass(TextOutputFormat.class);
		FileInputFormat.addInputPath(job, new Path(inputDirName));
		FileOutputFormat.setOutputPath(job, new Path(outputDirName));
		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
