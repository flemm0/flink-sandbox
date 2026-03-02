package com.example.flink.jobs;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.streaming.api.datastream.DataStream;

import com.example.flink.model.DogEvent;
import com.example.flink.util.DogSource;


public class DataStreamJob {

	public static void main(String[] args) throws Exception {

		final StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

		env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
		env.getConfig().setAutoWatermarkInterval(1000L);

		DataStream<DogEvent> dogStream = env
			.addSource(new DogSource())
			.name("Dog Source");
		
		dogStream
			.map((MapFunction<DogEvent, String>) event -> {
				return "Dog Event: " + event.name + " the " + event.breed + " says " + event.sound;
			})
			.print();

		env.execute("Hello Flink Job");
	}
}
