package com.lhw.nnwc.main;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;


// 들어오는거 a : (1, 1)
// 결과 		 a : 2
public class NNWCReducer 
	extends Reducer<Text, LongWritable, // mapper쪽 뒤 두개랑 같아야
	Text, LongWritable>{

	private static final LongWritable COUNT
		= new LongWritable();
	
	// 한 세트 들어올 때 마다
	@Override
	protected void reduce(
			Text arg0, 		// 키(a)
			Iterable<LongWritable> arg1, // 값(1, 1)
			Reducer<Text, LongWritable, Text, LongWritable>.Context arg2) // 결과 낼때 사용
					throws IOException, InterruptedException {
	
		long sum = 0;
		
		for (LongWritable lw : arg1) {
			sum += lw.get();
		}
		COUNT.set(sum);
		arg2.write(arg0, COUNT);
	}
}





