package com.flz.demo.entity;

public class JobAndTriggerEntity {
    private String SCHED_NAME;
    private String JOB_NAME;
    private String JOB_GROUP;
    private String JOB_CLASS_NAME;
    private String TRIGGER_NAME;
    private String TRIGGER_GROUP;
    private String CRON_EXPRESSION;
    private String TIME_ZONE_ID;
    private String TRIGGER_STATE;
    private int PRIORITY;
    private String TRIGGER_TYPE;

    public String getSCHED_NAME() {
        return SCHED_NAME;
    }

    public void setSCHED_NAME(String SCHED_NAME) {
        this.SCHED_NAME = SCHED_NAME;
    }

    public String getJOB_NAME() {
        return JOB_NAME;
    }

    public void setJOB_NAME(String JOB_NAME) {
        this.JOB_NAME = JOB_NAME;
    }

    public String getJOB_GROUP() {
        return JOB_GROUP;
    }

    public void setJOB_GROUP(String JOB_GROUP) {
        this.JOB_GROUP = JOB_GROUP;
    }

    public String getJOB_CLASS_NAME() {
        return JOB_CLASS_NAME;
    }

    public void setJOB_CLASS_NAME(String JOB_CLASS_NAME) {
        this.JOB_CLASS_NAME = JOB_CLASS_NAME;
    }

    public String getTRIGGER_NAME() {
        return TRIGGER_NAME;
    }

    public void setTRIGGER_NAME(String TRIGGER_NAME) {
        this.TRIGGER_NAME = TRIGGER_NAME;
    }

    public String getTRIGGER_GROUP() {
        return TRIGGER_GROUP;
    }

    public void setTRIGGER_GROUP(String TRIGGER_GROUP) {
        this.TRIGGER_GROUP = TRIGGER_GROUP;
    }

    public String getCRON_EXPRESSION() {
        return CRON_EXPRESSION;
    }

    public void setCRON_EXPRESSION(String CRON_EXPRESSION) {
        this.CRON_EXPRESSION = CRON_EXPRESSION;
    }

    public String getTIME_ZONE_ID() {
        return TIME_ZONE_ID;
    }

    public void setTIME_ZONE_ID(String TIME_ZONE_ID) {
        this.TIME_ZONE_ID = TIME_ZONE_ID;
    }

    public String getTRIGGER_STATE() {
        return TRIGGER_STATE;
    }

    public void setTRIGGER_STATE(String TRIGGER_STATE) {
        this.TRIGGER_STATE = TRIGGER_STATE;
    }

    public int getPRIORITY() {
        return PRIORITY;
    }

    public void setPRIORITY(int PRIORITY) {
        this.PRIORITY = PRIORITY;
    }

    public String getTRIGGER_TYPE() {
        return TRIGGER_TYPE;
    }

    public void setTRIGGER_TYPE(String TRIGGER_TYPE) {
        this.TRIGGER_TYPE = TRIGGER_TYPE;
    }
}
