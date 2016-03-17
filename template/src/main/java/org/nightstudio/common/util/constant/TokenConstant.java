package org.nightstudio.common.util.constant;

/**
 * Created by caoxuezhu01 on 14-9-22.
 */
public interface TokenConstant {
    int ALIVE_TIME = 30 * 24 * 60 * 60;
    int TIME_PART_LENGTH = 12;
    long TIME_PART_MOD = (long) Math.pow(10, TIME_PART_LENGTH);
    int RANDOM_PART_LENGTH = 6;
    long RANDOM_PART_MOD = (long) Math.pow(10, RANDOM_PART_LENGTH);
}
