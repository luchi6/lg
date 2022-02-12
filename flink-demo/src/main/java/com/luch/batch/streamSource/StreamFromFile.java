package com.luch.batch.streamSource;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

/**
 * @author luch
 * @date 2022/2/13 1:03
 */
public class StreamFromFile {
    public static void main(String[] args) throws Exception {
        String inPath = "e:\\test\\index.txt";
//        String outPath = args[1];
        String outPath = "e:\\test\\output";
        // 获取Flink流执行环境
        StreamExecutionEnvironment streamExecutionEnvironment =
                StreamExecutionEnvironment.getExecutionEnvironment();
        // 2.获取文件中内容
        DataStreamSource<String> textStream = streamExecutionEnvironment.readTextFile(inPath);
        SingleOutputStreamOperator<Tuple2<String, Long>> tuple2SingleOutputStreamOperator =
                textStream.flatMap(new FlatMapFunction<String, Tuple2<String, Long>>() {
                    @Override
                    public void flatMap(String s, Collector<Tuple2<String, Long>> collector) throws Exception {
                        String[] splits = s.split(" ");
                        for (String word : splits) {
                            collector.collect(Tuple2.of(word, 1l));
                        }
                    }
                });

        SingleOutputStreamOperator<Tuple2<String, Long>> word = tuple2SingleOutputStreamOperator.keyBy(0)
//                .timeWindow(Time.seconds(2), Time.seconds(1))
                .sum(1);
        // 打印数据
        word.print();
        // 触发任务执行
        streamExecutionEnvironment.execute("wordcount stream process");
    }
}
