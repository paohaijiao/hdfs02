package paohaijiao.pockbox.hdfs01;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class HdfsSourceTrace {

	public static void main(String[] args) throws Exception {
		//��������������һ�־����ļ�ϵͳ�Ŀͻ���ʵ������
		//���������ļ�����Ӧ����������ֵ�����������õ��ļ�ϵͳuri������ȡ�������ļ�ϵͳ�Ŀͻ�����class���������ʵ��
		//����Ϊfsʵ���еĳ�Ա��ʼ����ֵ
		//hdfs�Ŀͻ���ʵ������Ӧ�þ߱���Щ��Ա��
		Configuration conf = new Configuration();
		conf.set("fs.defaultFS", "hdfs://yun12-01:9000/");
		FileSystem fs = FileSystem.get(conf);
		
		FSDataInputStream is = fs.open(new Path("/jdk.tgz.rename"));
		
		FileOutputStream os = new FileOutputStream("d:/jdk.tgz.rename");
		
		IOUtils.copy(is, os);
		
	}
	
	
}
