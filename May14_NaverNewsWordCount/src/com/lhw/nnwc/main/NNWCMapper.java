package com.lhw.nnwc.main;

import java.io.IOException;
import java.util.StringTokenizer;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;


// KEY - VALUE

// 하둡의 자료형
//	String -> Text
// 	int -> IntWritable
//	long -> LongWritable
//	double -> DoubleWritable

public class NNWCMapper extends Mapper<Object, Text, 
Text, LongWritable> {

	private static final Text WORD = new Text();
	private static final LongWritable ONE = new LongWritable(1);

	// 컴퓨터로 데이터 하나 들어올때마다 호출됨
	@Override
	protected void map(Object key, // 들어오는 데이터의 키
			Text value, // 들어오는 데이터 하나(문장)
			Mapper<Object, Text, Text, LongWritable>.Context context) // 결과 낼때 사용
			throws IOException, InterruptedException {

		// Text -> String
		String s = value.toString();

		// s를 " "기준으로 분리
		StringTokenizer st = new StringTokenizer(s, " ");

		// 더이상 단어 없을때까지 반복
		while (st.hasMoreElements()) {
			// WORD에 단어 저장
			WORD.set(st.nextToken());
			// 결과 내기
			context.write(WORD, ONE);
		}

	}

}

