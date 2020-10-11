package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;


/**
 * @Auther: wxb
 * @Date: 2018/7/30 10:01
 * @Auto: I AM A CODE MAN -_-!
 * @Description: orderId生成类
 */
@Slf4j
public class OrderWorker {

    // 起始标记点，作为基准
    private static final long snsEpoch = 1330328109047L;
    // 0，并发控制
    private static long sequence = 0L;
    // 机器标识位数
    private static final long workerIdBits = 10L;// 只允许workid的范围为：0-1023
    // 机器ID最大值
    private static final long maxWorkerId = -1L ^ -1L << workerIdBits;// 1023,1111111111,10位
    // 数据中心ID最大值
    private static final long sequenceBits = 12L;// sequence值控制在0-4095
    // 机器ID偏左移12位
    private static final long workerIdShift = sequenceBits;// 12
    // 时间毫秒左移22位
    private static final long timestampLeftShift = sequenceBits + workerIdBits;// 22
    private static final long sequenceMask = -1L ^ -1L << sequenceBits;// 4095,111111111111,12位
    // 数据中心标识位数
    private final static long datacenterIdBits = 4L;
    // 数据中心ID最大值
    private final static long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);
    // 上次生产id时间戳
    private long lastTimestamp = -1L;
    // 机器号
    private final long workerId;
    // 数据标识id部分
    private final long datacenterId;

    public OrderWorker() {
        this.datacenterId = IdWorker.getDatacenterId(maxDatacenterId);
        log.info("this is a datacenterId, datacenterId:{}", datacenterId);
        this.workerId = IdWorker.getMaxWorkerId(datacenterId, maxWorkerId);
        log.info("this is a workerId, workerId:{}", workerId);
    }

    public synchronized long nextId() {
        long timestamp = this.timeGen();
        if (this.lastTimestamp == timestamp) {// 如果上一个timestamp与新产生的相等，则sequence加一(0-4095循环)，下次再使用时sequence是新值
            this.sequence = this.sequence + 1 & this.sequenceMask;
            if (this.sequence == 0) {
                timestamp = this.tilNextMillis(this.lastTimestamp);// 重新生成timestamp
            }
        } else {
            this.sequence = 0;
        }
        if (timestamp < this.lastTimestamp) {
            log.error(String.format("Clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
            throw new RuntimeException(String.format("Clock moved backwards.Refusing to generate id for %d milliseconds", (this.lastTimestamp - timestamp)));
        }

        this.lastTimestamp = timestamp;
        // 生成的timestamp
        return timestamp - this.snsEpoch << this.timestampLeftShift | this.workerId << this.workerIdShift | this.sequence;
    }

    /**
     * 保证返回的毫秒数在参数之后
     * @param lastTimestamp
     * @return
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = this.timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = this.timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     * @return
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) {
        OrderWorker iw1 = new OrderWorker();
        for (int i = 0; i < 100; i++)
            System.out.println(iw1.nextId());
    }
}
