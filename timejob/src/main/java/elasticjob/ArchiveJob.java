package elasticjob;

import com.dangdang.ddframe.job.api.ShardingContext;
import com.dangdang.ddframe.job.api.simple.SimpleJob;
import elasticjob.util.JdbcUtil;

import java.util.List;
import java.util.Map;

/**
 * @author luch
 * @date 2021/10/27 0:03
 */


public class ArchiveJob implements SimpleJob {
    @Override
    public void execute(ShardingContext shardingContext) {
        int shardingItem = shardingContext.getShardingItem();
        System.out.println("========>>>>当前分片：" + shardingItem);

        //获取分片参数
        String shardingParameter = shardingContext.getShardingParameter();


        //1.从resume表中查询出一条记录，未归档
        String selectSql = "select * from resume where state='未归档' and education=? limit 1";
        List<Map<String, Object>> list = JdbcUtil.executeQuery(selectSql,shardingParameter);
        if (list == null || list.size() == 0) {
            System.out.println("数据已经处理完毕！");
            return;
        }

        //2."未归档"改为"归档"
        Map<String, Object> stringObjMap = list.get(0);
        long id = (long) stringObjMap.get("id");
        String name = (String) stringObjMap.get("name");
        String education = (String) stringObjMap.get("education");

        System.out.println("=========>>>>id:" + id + "  name:" + name + "   education:" + education);

        String updateSql = "update resume set state='已归档' where id=?";
        JdbcUtil.executeUpdate(updateSql, id);

        //3.归档这条记录，把这条记录插入到resume_bak表
        String insertSql = "insert into resume_bak select * from resume where id=?";
        JdbcUtil.executeUpdate(insertSql, id);

    }
}
