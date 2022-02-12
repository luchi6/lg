package com.luch.batch;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

/**
 * 单词统计（批数据处理）
 */
public class WordCountBatch {
    public static void main(String[] args) throws Exception {
        // 输入路径和出入路径通过参数传入，约定第一个参数为输入路径，第二个参数为输出路径
//        String inPath = args[0];
        String inPath = "e:\\test\\index.txt";
//        String outPath = args[1];
        String outPath = "e:\\test\\output";
        // 1.获取Flink批处理执行环境
        ExecutionEnvironment executionEnvironment = ExecutionEnvironment.getExecutionEnvironment();
        // 2.获取文件中内容
        DataSet<String> text = executionEnvironment.readTextFile(inPath);
        // 3.对数据进行处理
        DataSet<Tuple2<String, Integer>> dataSet = text
                .flatMap(new LineSplitter()).groupBy(0).sum(1);
        // 4.保存处理结果
//        dataSet.writeAsText(outPath);
        dataSet.writeAsCsv(outPath,"\n"," ").setParallelism(1);
        // 5.触发执行程序
        executionEnvironment.execute("wordcount batch process");
    }


    static class LineSplitter implements FlatMapFunction<String, Tuple2<String,Integer>> {
        @Override
        public void flatMap(String line, Collector<Tuple2<String, Integer>> collector) throws Exception {
            for (String word:line.split(" ")) {
                collector.collect(new Tuple2<>(word,1));
            }
        }
    }
}
