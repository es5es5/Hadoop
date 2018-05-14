package com.lhw.nnwc.main;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

// 하둡 
//		리눅스에서만 돌아가는
//		컴퓨터 여러대로 분산/병렬처리하는
//		단어 수 세는
// 		자바프로그램

// 실시간(x)

// HDFS(Hadoop Distributed File System)
	// 하둡이 운영하는 논리적인 공간
	// 하둡프로젝트에 참여하는 컴퓨터들의 하드디스크 여러개를
	// 하나처럼 사용

// 분석할 파일의 한글/특수문자 처리
	// UTF-8


// 2단계
//		map

//		reduce















// Windows의 이클립스
//		자동완성 잘되고
//		.jar파일 만들기 편하기도 하고

// 하둡관련 .jar파일 프로젝트에 넣는데
//		자동완성에 필요한것만
//		hadoop-common
//		hadoop-mapreduce-client-core

public class NNWCMain {
	public static void main(String[] args) {
		try {
			Configuration c = new Configuration();
			Job j = Job.getInstance(c);
			
			j.setMapperClass(NNWCMapper.class);
// a : 1, a : 1을    a : (1,1)로 합쳐주는 클래스 (Reducer클래스)
			j.setCombinerClass(NNWCReducer.class);
			j.setReducerClass(NNWCReducer.class);
			
			// 최종결과형식(Reducer 뒤쪽 두자리에 맞춰서)
			j.setOutputKeyClass(Text.class);
			j.setOutputValueClass(LongWritable.class);
			
			// 입출력
			FileInputFormat.addInputPath(j, new Path(args[0]));
			FileOutputFormat.setOutputPath(j, new Path(args[1]));
			
			// something.jar  입력파일경로   출력경로
			
			// 하둡 작업 끝날 때까지 다른 작업 못 하게
			j.waitForCompletion(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}







