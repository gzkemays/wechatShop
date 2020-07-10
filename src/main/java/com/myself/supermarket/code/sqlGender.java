package com.myself.supermarket.code;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.util.ArrayList;
import java.util.List;

public class sqlGender {
    public static void main(String[] args) {
        AutoGenerator msg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
            String path = System.getProperty("user.dir");
            gc.setAuthor("gzkemays");
            gc.setOutputDir(path+"/src/main/java");
            gc.setServiceName("%sService");
            gc.setDateType(DateType.ONLY_DATE);
            gc.setOpen(false);
            gc.setFileOverride(true);
        msg.setGlobalConfig(gc);


        DataSourceConfig dsc = new DataSourceConfig();
            dsc.setDriverName("com.mysql.cj.jdbc.Driver");
            dsc.setUrl("jdbc:mysql://127.0.0.1:4096/supermarket?serverTimezone=UTC&useSSL=false&useUnicode=true&characterEncoding=UTF-8");
            dsc.setUsername("root");
            dsc.setPassword("gzkemays");
        msg.setDataSource(dsc);

        StrategyConfig sc = new StrategyConfig();
            sc.setInclude("t_product_orders");
            sc.setNaming(NamingStrategy.underline_to_camel);
            sc.setColumnNaming(NamingStrategy.underline_to_camel);
            sc.setEntityLombokModel(true);
            List<TableFill> tableFills = new ArrayList<>();
                TableFill createTime = new TableFill("create_time", FieldFill.INSERT);
                TableFill updateTime = new TableFill("update_time",FieldFill.INSERT_UPDATE);
                tableFills.add(createTime);
                tableFills.add(updateTime);
            sc.setTableFillList(tableFills);
        msg.setStrategy(sc);


        PackageConfig pc = new PackageConfig();
            pc.setParent("com.myself.supermarket");
            pc.setEntity("pojo");
            pc.setMapper("mapper");
            pc.setService("service");
            pc.setController("controller");
        msg.setPackageInfo(pc);

        msg.execute();
    }
}
