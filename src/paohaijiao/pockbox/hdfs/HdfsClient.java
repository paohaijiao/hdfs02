package paohaijiao.pockbox.hdfs;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsClient {

	
	public static void main(String[] args) throws Exception {
		
		Configuration conf = new Configuration();
		FileSystem fs = FileSystem.get(new URI("hdfs://ns1/"), conf, "hadoop");
		
		fs.copyFromLocalFile(new Path("G:/bigdata-material-data/18-30-hotel.csv"), new Path("/"));
		
		
	}
	
	
	
	
}
