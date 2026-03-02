package com.example.flink.jobs;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import com.example.flink.util.simulator.Dog;
import com.example.flink.util.DogSource;


public class DataStreamJob {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.getConfig().setAutoWatermarkInterval(1000L);

		DataStream<Dog> dogStream = env
			.addSource(new DogSource())
			.name("Dog Source")
			.uid("dog-source");
		
		dogStream
			.map((MapFunction<Dog, String>) dog -> "Dog: " + dog.getName() + ", says: " + dog.makeSound())
			.print();

		env.execute("Hello Flink Job");
	}
}
