package com.defyer;

import com.defyer.config.DruidConfig;
import com.defyer.system.entity.Table1;
import com.defyer.system.mapper.Table1Mapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
class DemoApplicationTests {

    @Test
    void contextLoads() {
    }

    @Autowired
    private Table1Mapper table1Mapper;

    @Autowired
    private DruidConfig druidConfig;

    @Autowired
    private DataSource dataSource;

    @Test
    public void testQuery() throws Exception{

        ExecutorService executorService= Executors.newCachedThreadPool();

//        for(int i=0;i<3;i++){
//            executorService.submit(()->{
//                try {
//                    dataSource.getConnection();
//                    Thread.sleep(2000);
//                } catch ( Exception e) {
//                    e.printStackTrace();
//                }
//            });
//        }

        Table1 table1=table1Mapper.selectById(7);

        System.out.println(table1);

        System.out.println(druidConfig);

        Thread.currentThread().join();

    }



    @Test
    public void testQuery2(){

        Table1 table1=table1Mapper.selectById(7);

        System.out.println(table1);

        System.out.println(druidConfig);

    }

    @Test
    public void test3(){
        int removeCount=2;
        int poolingCount=4;

        int[] arr=new int[]{1,2,3,4,0};

        System.out.println(Arrays.toString(arr));
        System.arraycopy(arr,removeCount,arr,0,poolingCount-removeCount);

        Arrays.fill(arr, poolingCount - removeCount, poolingCount, 0);

        System.out.println(Arrays.toString(arr));
    }




}
