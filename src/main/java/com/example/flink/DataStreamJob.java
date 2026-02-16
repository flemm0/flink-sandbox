package com.example.flink;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.api.common.functions.MapFunction;


public class DataStreamJob {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.fromElements("Hello", "Flink", "from", "Docker")
			.map((MapFunction<String, String>) value -> "👉" + value)
			.print();

		env.execute("Hello Flink Job");
	}
}
