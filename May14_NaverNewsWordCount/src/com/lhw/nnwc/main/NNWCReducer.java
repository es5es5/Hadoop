package com.lhw.nnwc.main;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


// �����°� a : (1, 1)
// ��� 		 a : 2
public class NNWCReducer 
	extends Reducer<Text, LongWritable, // mapper�� �� �ΰ��� ���ƾ�
	Text, LongWritable>{

	private static final LongWritable COUNT
		= new LongWritable();
	
	// �� ��Ʈ ���� �� ����
	@Override
	protected void reduce(
			Text arg0, 		// Ű(a)
			Iterable<LongWritable> arg1, // ��(1, 1)
			Reducer<Text, LongWritable, Text, LongWritable>.Context arg2) // ��� ���� ���
					throws IOException, InterruptedException {
	
		long sum = 0;
		
		for (LongWritable lw : arg1) {
			sum += lw.get();
		}
		COUNT.set(sum);
		arg2.write(arg0, COUNT);
	}
}





