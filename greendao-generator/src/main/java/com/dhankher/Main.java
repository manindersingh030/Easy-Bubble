package com.dhankher;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class Main {

    public static void main(String[] args) throws Exception {
        Schema schema = new Schema(1, "com.eworl.easybubble.db");
        Entity program = schema.addEntity("Program");
        program.addIdProperty();
        program.addStringProperty("appName");
        program.addStringProperty("appIcon");
        program.addStringProperty("packageName").unique();
        program.addBooleanProperty("isSelected");
        DaoGenerator dg = new DaoGenerator();
        dg.generateAll(schema, "./app/src/main/java");
    }
}
