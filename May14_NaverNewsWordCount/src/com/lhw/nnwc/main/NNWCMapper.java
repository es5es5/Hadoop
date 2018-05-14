package com.lhw.nnwc.main;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


// KEY - VALUE

// �ϵ��� �ڷ���
//	String -> Text
// 	int -> IntWritable
//	long -> LongWritable
//	double -> DoubleWritable

public class NNWCMapper extends Mapper<Object, Text, 
Text, LongWritable> {

	private static final Text WORD = new Text();
	private static final LongWritable ONE = new LongWritable(1);

	// ��ǻ�ͷ� ������ �ϳ� ���ö����� ȣ���
	@Override
	protected void map(Object key, // ������ �������� Ű
			Text value, // ������ ������ �ϳ�(����)
			Mapper<Object, Text, Text, LongWritable>.Context context) // ��� ���� ���
			throws IOException, InterruptedException {

		// Text -> String
		String s = value.toString();

		// s�� " "�������� �и�
		StringTokenizer st = new StringTokenizer(s, " ");

		// ���̻� �ܾ� ���������� �ݺ�
		while (st.hasMoreElements()) {
			// WORD�� �ܾ� ����
			WORD.set(st.nextToken());
			// ��� ����
			context.write(WORD, ONE);
		}

	}

}

