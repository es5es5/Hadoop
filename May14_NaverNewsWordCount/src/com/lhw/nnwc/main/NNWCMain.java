package com.lhw.nnwc.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// �ϵ� 
//		������������ ���ư���
//		��ǻ�� ������� �л�/����ó���ϴ�
//		�ܾ� �� ����
// 		�ڹ����α׷�

// �ǽð�(x)

// HDFS(Hadoop Distributed File System)
	// �ϵ��� ��ϴ� ������ ����
	// �ϵ�������Ʈ�� �����ϴ� ��ǻ�͵��� �ϵ��ũ ��������
	// �ϳ�ó�� ���

// �м��� ������ �ѱ�/Ư������ ó��
	// UTF-8


// 2�ܰ�
//		map

//		reduce















// Windows�� ��Ŭ����
//		�ڵ��ϼ� �ߵǰ�
//		.jar���� ����� ���ϱ⵵ �ϰ�

// �ϵӰ��� .jar���� ������Ʈ�� �ִµ�
//		�ڵ��ϼ��� �ʿ��Ѱ͸�
//		hadoop-common
//		hadoop-mapreduce-client-core

public class NNWCMain {
	public static void main(String[] args) {
		try {
			Configuration c = new Configuration();
			Job j = Job.getInstance(c);
			
			j.setMapperClass(NNWCMapper.class);
// a : 1, a : 1��    a : (1,1)�� �����ִ� Ŭ���� (ReducerŬ����)
			j.setCombinerClass(NNWCReducer.class);
			j.setReducerClass(NNWCReducer.class);
			
			// �����������(Reducer ���� ���ڸ��� ���缭)
			j.setOutputKeyClass(Text.class);
			j.setOutputValueClass(LongWritable.class);
			
			// �����
			FileInputFormat.addInputPath(j, new Path(args[0]));
			FileOutputFormat.setOutputPath(j, new Path(args[1]));
			
			// something.jar  �Է����ϰ��   ��°��
			
			// �ϵ� �۾� ���� ������ �ٸ� �۾� �� �ϰ�
			j.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}







