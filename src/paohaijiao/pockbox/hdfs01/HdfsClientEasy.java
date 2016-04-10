package paohaijiao.pockbox.hdfs01;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.LocatedFileStatus;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.RemoteIterator;
import org.junit.Before;
import org.junit.Test;

public class HdfsClientEasy {

	private FileSystem fs = null;

	@Before
	public void getFs() throws Exception {

		// �õ�һ�����ò����ķ�װ���󣬹��캯���оͻ��classpath�µ�xxx-site.xml�ļ����н���
		// ��ʵ����Ŀ�����о�Ӧ�ð�xxx-site.xml�ļ����뵽��������
		Configuration conf = new Configuration();
		// to set a parameter, figure out the filesystem is hdfs
		conf.set("fs.defaultFS", "hdfs://yun12-01:9000/");
		conf.set("dfs.replication", "1");

		// ��ȡ��һ�������ļ�ϵͳ�Ŀͻ���ʵ�����󣬲�����ʵ����������һ���ļ�ϵͳ�Ŀͻ��ˣ��Ǹ���conf�е���ز���������
//		fs = FileSystem.get(conf);
		
		//���ֻ�ȡfs�ķ�������ָ������hdfs�Ŀͻ������
		fs = FileSystem.get(new URI("hdfs://yun12-01:9000/"), conf, "hadoop");

	}

	/**
	 * �ϴ��ļ�
	 * 
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testUpload() throws IllegalArgumentException, IOException {

		fs.copyFromLocalFile(new Path("c:/jdk.win"), new Path("/"));

	}

	/**
	 * ɾ���ļ�
	 * 
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testRmfile() throws IllegalArgumentException, IOException {

		boolean res = fs.delete(new Path("/aa/bb"), true);

		System.out.println(res ? "delete is successfully :)"
				: "it is failed :(");

	}

	/**
	 * �����ļ���
	 * 
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testMkdir() throws IllegalArgumentException, IOException {

		fs.mkdirs(new Path("/aa/bb"));

	}

	/**
	 * �������ļ�
	 * 
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testRename() throws IllegalArgumentException, IOException {

		fs.rename(new Path("/jdk.tgz"), new Path("/jdk.tgz.rename"));

	}

	/**
	 * �г�Ŀ¼�µ��ļ���Ϣ
	 * 
	 * @throws FileNotFoundException
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testListFiles() throws FileNotFoundException,
			IllegalArgumentException, IOException {

		// �ݹ��г��ļ�
		RemoteIterator<LocatedFileStatus> listFiles = fs.listFiles(
				new Path("/"), true);

		while (listFiles.hasNext()) {

			LocatedFileStatus file = listFiles.next();

			System.out.println(file.getPath().getName());

		}

		System.out.println("--------------------------------------------");

		// �г��ļ����ļ���
		FileStatus[] status = fs.listStatus(new Path("/"));
		for (FileStatus file : status) {

			System.out.println(file.getPath().getName() + "   "
					+ (file.isDirectory() ? "d" : "f"));

		}

	}

	/**
	 * ��hdfs���������ݵ�����
	 * 
	 * @throws IllegalArgumentException
	 * @throws IOException
	 */
	@Test
	public void testDownload() throws IllegalArgumentException, IOException {

		fs.copyToLocalFile(new Path("/jdk.tgz.rename"), new Path("c:/jdk.win"));

	}

}
